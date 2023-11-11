package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnOfficeFetchListener;
import persistence.models.OfficeModel;
import request.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OfficeFetcher {

    private Handler handler;
    private OnOfficeFetchListener listener;

    public OfficeFetcher(Handler handler, OnOfficeFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchOffices() {
        HandlerThread handlerThread = new HandlerThread("OfficeFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("offices");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<OfficeModel> offices = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject officeJson = jsonArray.getJSONObject(i);

                            UUID idCargo = UUID.fromString(officeJson.getString("idCargo"));
                            String nome = officeJson.getString("nome");
                            String descricao = officeJson.getString("descricao");
                           // String cnpj = officeJson.getString("cnpj");


                            OfficeModel office = new OfficeModel(idCargo, nome, descricao);
                            offices.add(office);
                        }

                        if (listener != null) {
                            listener.onOfficeFetchSuccess(offices);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onOfficeFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onOfficeFetchError();
                    }
                }
            }
        });
    }
}
