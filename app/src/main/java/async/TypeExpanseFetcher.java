/*package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnTypeExpanseFetchListener;
import persistence.models.TypeExpanseModel;
import request.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                String result = Connection.connectHttp("expanse/type");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<TypeExpanseModel> typeExpanses = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject typeExpanseJson = jsonArray.getJSONObject(i);

                            UUID idTypeExpanse = UUID.fromString(typeExpanseJson.getString("idTypeExpanse"));
                            // TODO: Adicione o restante dos campos da TypeExpanseModel

                            // Crie a instância de TypeExpanseModel e adicione à lista
                            TypeExpanseModel typeExpanse = new TypeExpanseModel(
                                    idTypeExpanse, nome, descricao);
                            typeExpanses.add(typeExpanse);
                        }

                        if (listener != null) {
                            listener.onTypeExpanseFetchSuccess(typeExpanses);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onTypeExpanseFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onTypeExpanseFetchError();
                    }
                }
            }
        });
    }
}
*/