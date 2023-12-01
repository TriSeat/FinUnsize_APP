package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.PurchaseItemFetchException;
import listener.OnPurchaseItemFetchListener;
import persistence.models.PurchaseItemModel;
import request.Connection;

import java.io.IOException;
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
                try {
                    String result = Connection.connectHttp("purchase/" + purchaseId + "/item");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<PurchaseItemModel> purchaseItems = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject purchaseItemJson = jsonArray.getJSONObject(i);

                            UUID idPurchaseItem = UUID.fromString(purchaseItemJson.getString("idPurchaseItem"));
                            // Parse other fields as needed from the JSON object

                            PurchaseItemModel purchaseItem = new PurchaseItemModel(idPurchaseItem);
                            purchaseItems.add(purchaseItem);
                        }

                        if (listener != null) {
                            listener.onPurchaseItemFetchSuccess(purchaseItems);
                        }

                    } else {
                        throw new PurchaseItemFetchException("Error fetching purchase items");
                    }

                } catch (JSONException | PurchaseItemFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onPurchaseItemFetchError();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
