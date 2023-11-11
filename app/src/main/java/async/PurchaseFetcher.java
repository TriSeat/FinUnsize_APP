package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import listener.OnPurchaseFetchListener;
import persistence.models.PurchaseModel;
import request.Connection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PurchaseFetcher {

    private Handler handler;
    private OnPurchaseFetchListener listener;

    public PurchaseFetcher(Handler handler, OnPurchaseFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchPurchases() {
        HandlerThread handlerThread = new HandlerThread("PurchaseFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("purchases");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<PurchaseModel> purchases = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject purchaseJson = jsonArray.getJSONObject(i);

                            UUID idPurchase = UUID.fromString(purchaseJson.getString("idPurchase"));
                            String name = purchaseJson.getString("name");
                            BigDecimal totalPrice = new BigDecimal(purchaseJson.getString("totalPrice"));
                            LocalDateTime date = LocalDateTime.parse(purchaseJson.getString("date"));

                            PurchaseModel purchase = new PurchaseModel(idPurchase, name, totalPrice, date);
                            purchases.add(purchase);
                        }

                        if (listener != null) {
                            listener.onPurchaseFetchSuccess(purchases);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onPurchaseFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onPurchaseFetchError();
                    }
                }
            }
        });
    }
}
