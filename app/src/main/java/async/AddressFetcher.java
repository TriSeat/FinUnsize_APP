package async;


import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID;

import listener.OnAddressFetchListener;
import persistence.models.AddressModel;
import request.Connection;

public class AddressFetcher {

    private Handler handler;
    private OnAddressFetchListener listener;

    public AddressFetcher(Handler handler, OnAddressFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchAddress(UUID idLogradouro) {
        HandlerThread handlerThread = new HandlerThread("AddressFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("addresses/" + idLogradouro);

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject addressJson = jsonObject.getJSONObject("data");

                        int cep = addressJson.getInt("cep");
                        String rua = addressJson.getString("rua");
                        String numero = addressJson.getString("numero");
                        String complemento = addressJson.optString("complemento", null);
                        String referencia = addressJson.optString("referencia", null);
                        String cidade = addressJson.getString("cidade");

                        AddressModel address = new AddressModel(idLogradouro, cep, rua, numero, complemento, referencia, cidade);

                        if (listener != null) {
                            listener.onAddressFetchSuccess(address);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onAddressFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onAddressFetchError();
                    }
                }
            }
        });
    }
}
