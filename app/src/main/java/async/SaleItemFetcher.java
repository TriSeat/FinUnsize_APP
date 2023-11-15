package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnSaleItemFetchListener;
import persistence.models.SaleItemModel;
import request.Connection;
import exception.SaleItemFetchException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaleItemFetcher {

    private Handler handler;
    private OnSaleItemFetchListener listener;

    public SaleItemFetcher(Handler handler, OnSaleItemFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchSaleItems(UUID saleId) {
        HandlerThread handlerThread = new HandlerThread("SaleItemFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("sales/" + saleId + "/items");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<SaleItemModel> saleItems = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject saleItemJson = jsonArray.getJSONObject(i);

                            UUID idSaleItem = UUID.fromString(saleItemJson.getString("idSaleItem"));

                            SaleItemModel saleItem = new SaleItemModel(idSaleItem);
                            saleItems.add(saleItem);
                        }

                        if (listener != null) {
                            listener.onSaleItemFetchSuccess(saleItems);
                        }

                    } else {
                        throw new SaleItemFetchException("Error fetching sale items");
                    }
                } catch (JSONException | SaleItemFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onSaleItemFetchError();
                    }
                }
            }
        });
    }
}
