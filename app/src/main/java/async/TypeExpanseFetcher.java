package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import exception.TypeExpanseFetchException;
import listener.OnTypeExpanseFetchListener;
import persistence.models.TypeExpanseModel;
import request.Connection;

public class TypeExpanseFetcher {

    private Handler handler;
    private OnTypeExpanseFetchListener listener;

    public TypeExpanseFetcher(Handler handler, OnTypeExpanseFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchTypeExpanses() {
        HandlerThread handlerThread = new HandlerThread("TypeExpanseFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("expanse/type");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<TypeExpanseModel> typeExpanses = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject typeExpanseJson = jsonArray.getJSONObject(i);

                            UUID idTypeExpanse = UUID.fromString(typeExpanseJson.getString("idTypeExpanse"));
                            String nome = typeExpanseJson.getString("nome");
                            String descricao = typeExpanseJson.getString("descricao");
                            String cnpj = typeExpanseJson.optString("cnpj", null);

                            TypeExpanseModel typeExpanse = new TypeExpanseModel(idTypeExpanse, nome, descricao, cnpj);
                            typeExpanses.add(typeExpanse);
                        }

                        if (listener != null) {
                            listener.onTypeExpanseFetchSuccess(typeExpanses);
                        }

                    } else {
                        throw new TypeExpanseFetchException("Error fetching type expanses");
                    }

                } catch (JSONException | TypeExpanseFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onTypeExpanseFetchError();
                    }
                }
            }
        });
    }
}
