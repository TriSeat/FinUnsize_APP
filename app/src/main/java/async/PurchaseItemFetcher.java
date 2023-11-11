package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnPurchaseItemFetchListener;

import persistence.models.PurchaseItemModel;
import request.Connection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PurchaseItemFetcher {

    private Handler handler;
    private OnPurchaseItemFetchListener listener;

    public PurchaseItemFetcher(Handler handler, OnPurchaseItemFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchPurchaseItems(UUID purchaseId) {
        HandlerThread handlerThread = new HandlerThread("PurchaseItemFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("purchases/" + purchaseId + "/items");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<PurchaseItemModel> purchaseItems = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject purchaseItemJson = jsonArray.getJSONObject(i);

                            UUID idPurchaseItem = UUID.fromString(purchaseItemJson.getString("idPurchaseItem"));



                            PurchaseItemModel purchaseItem = new PurchaseItemModel(
                                    idPurchaseItem);
                            purchaseItems.add(purchaseItem);
                        }

                        if (listener != null) {
                            listener.onPurchaseItemFetchSuccess(purchaseItems);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onPurchaseItemFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onPurchaseItemFetchError();
                    }
                }
            }
        });
    }
}
