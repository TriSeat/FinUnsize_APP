package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID;

import exception.SupplierFetchException;
import listener.OnSupplierFetchListener;
import persistence.models.SupplierModel;
import request.Connection;

public class SupplierFetcher {

    private Handler handler;
    private OnSupplierFetchListener listener;

    public SupplierFetcher(Handler handler, OnSupplierFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchSupplier(UUID idFornecedor) {
        HandlerThread handlerThread = new HandlerThread("SupplierFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("suppliers/" + idFornecedor);

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject supplierJson = jsonObject.getJSONObject("data");

                        String nome = supplierJson.getString("nome");
                        String descricao = supplierJson.getString("descricao");
                        String urlImage = supplierJson.optString("url_image", null);
                        // String cnpj = supplierJson.getString("cnpj");

                        SupplierModel supplier = new SupplierModel(idFornecedor, nome, descricao, urlImage);

                        if (listener != null) {
                            listener.onSupplierFetchSuccess(supplier);
                        }

                    } else {
                        throw new SupplierFetchException("Error fetching supplier");
                    }

                } catch (JSONException | SupplierFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onSupplierFetchError();
                    }
                }
            }
        });
    }
}
