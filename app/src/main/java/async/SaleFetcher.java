package async;


import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnSaleFetchListener;

import persistence.models.SaleModel;
import request.Connection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaleFetcher {

    private Handler handler;
    private OnSaleFetchListener listener;

    public SaleFetcher(Handler handler, OnSaleFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchSales(UUID cashierId) {
        HandlerThread handlerThread = new HandlerThread("SaleFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("cashiers/" + cashierId + "/sales");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<SaleModel> sales = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject saleJson = jsonArray.getJSONObject(i);

                            UUID idSale = UUID.fromString(saleJson.getString("idSale"));
                            // TODO: Adicione o restante dos campos da SaleModel

                            // Crie a instância de SaleModel e adicione à lista
                            SaleModel sale = new SaleModel(
                                    idSale);
                            sales.add(sale);
                        }

                        if (listener != null) {
                            listener.onSaleFetchSuccess(sales);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onSaleFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onSaleFetchError();
                    }
                }
            }
        });
    }
}
