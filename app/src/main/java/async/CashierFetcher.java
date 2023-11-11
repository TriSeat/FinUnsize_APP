package async;


import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONException;
import org.json.JSONObject;

import listener.OnCashierFetchListener;
import persistence.models.CashierModel;
import request.Connection;

public class CashierFetcher {

    private Handler handler;
    private OnCashierFetchListener listener;

    public CashierFetcher(Handler handler, OnCashierFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchCashier(int idCaixa) {
        HandlerThread handlerThread = new HandlerThread("CashierFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("cashiers/" + idCaixa);

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject cashierJson = jsonObject.getJSONObject("data");

                        String nome = cashierJson.getString("nome");
                        String status = cashierJson.getString("status");
                        String cnpj = cashierJson.getString("cnpj");

                        CashierModel cashier = new CashierModel(idCaixa, nome, status, cnpj);

                        if (listener != null) {
                            listener.onCashierFetchSuccess(cashier);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onCashierFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onCashierFetchError();
                    }
                }
            }
        });
    }
}
