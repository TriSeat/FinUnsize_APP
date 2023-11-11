package async;


import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnExpanseFetchListener;
import persistence.models.ExpanseModel;
import persistence.models.TypeExpanseModel;
import request.Connection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpanseFetcher {

    private Handler handler;
    private OnExpanseFetchListener listener;

    public ExpanseFetcher(Handler handler, OnExpanseFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchExpanses() {
        HandlerThread handlerThread = new HandlerThread("ExpanseFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("expanses");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<ExpanseModel> expanses = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject expanseJson = jsonArray.getJSONObject(i);

                            UUID idDespesa = UUID.fromString(expanseJson.getString("idDespesa"));
                            String nome = expanseJson.getString("nome");
                            BigDecimal valor = new BigDecimal(expanseJson.getString("valor"));
                            LocalDate dataVencimento = LocalDate.parse(expanseJson.getString("dataVencimento"));
                            LocalDate dataPagamento = LocalDate.parse(expanseJson.optString("dataPagamento", "null"));
                            TypeExpanseModel tipoDespesa = parseTypeExpanseModel(expanseJson.getJSONObject("tipoDespesa"));
                            String observacao = expanseJson.optString("observacao", null);
                            boolean aberto = expanseJson.getBoolean("aberto");
                          //  String cnpj = expanseJson.getString("cnpj");


                            ExpanseModel expanse = new ExpanseModel(idDespesa, nome, valor, dataVencimento, dataPagamento, tipoDespesa, observacao, aberto);
                            expanses.add(expanse);
                        }

                        if (listener != null) {
                            listener.onExpanseFetchSuccess(expanses);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onExpanseFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onExpanseFetchError();
                    }
                }
            }
        });
    }

    private TypeExpanseModel parseTypeExpanseModel(JSONObject typeExpanseJson) throws JSONException {
        UUID idDespesa = UUID.fromString(typeExpanseJson.getString("idDespesa"));
        String nome = typeExpanseJson.getString("nome");
        String descricao = typeExpanseJson.getString("descricao");
       // String cnpj = typeExpanseJson.getString("cnpj");

        return new TypeExpanseModel(idDespesa, nome, descricao);
    }
}
