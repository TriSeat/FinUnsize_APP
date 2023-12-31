package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import exception.CashierFetchException;
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
                try {
                    String result = Connection.connectHttp("cashier/" + idCaixa);

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject cashierJson = jsonObject.getJSONObject("data");

                        String nome = cashierJson.getString("nome");
                        String status = cashierJson.getString("status");
                        String cnpj = cashierJson.getString("cnpj");

                        CashierModel cashier = new CashierModel(idCaixa, nome, status, cnpj);

                        if (listener != null) {
                            listener.onCashierFetchSuccess(cashier);
                        }
                    } else {
                        if (listener != null) {
                            listener.onCashierFetchError();
                        }
                    }
                } catch (JSONException e) {
                    if (listener != null) {
                        listener.onCashierFetchError();
                    }

                    try {
                        throw new CashierFetchException("Error fetching cashier", e);
                    } catch (CashierFetchException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
