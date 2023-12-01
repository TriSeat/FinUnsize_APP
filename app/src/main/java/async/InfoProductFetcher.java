package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.InfoProductFetchException;
import listener.OnInfoProductFetchListener;
import persistence.models.InfoProductModel;
import request.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class  InfoProductFetcher {

    private Handler handler;
    private OnInfoProductFetchListener listener;

    public InfoProductFetcher(Handler handler, OnInfoProductFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchInfoProducts() {
        HandlerThread handlerThread = new HandlerThread("InfoProductFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("product");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<InfoProductModel> infoProducts = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject infoProductJson = jsonArray.getJSONObject(i);

                            UUID idItemProduto = UUID.fromString(infoProductJson.getString("idItemProduto"));
                            String marca = infoProductJson.getString("marca");
                            String categoria = infoProductJson.getString("categoria");
                            String tipo = infoProductJson.getString("tipo");

                            InfoProductModel infoProduct = new InfoProductModel(idItemProduto, marca, categoria, tipo);
                            infoProducts.add(infoProduct);
                        }

                        if (listener != null) {
                            listener.onInfoProductFetchSuccess(infoProducts);
                        }

                    } else {
                        if (listener != null) {
                            throw new InfoProductFetchException("Failed to fetch info products - result is null");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onInfoProductFetchError();
                    }
                } catch (InfoProductFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onInfoProductFetchError();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }}
