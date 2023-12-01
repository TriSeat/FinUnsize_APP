package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.ProjectionFetchException;
import listener.OnProjectionFetchListener;
import persistence.models.ProjectionModel;
import request.Connection;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectionFetcher {

    private Handler handler;
    private OnProjectionFetchListener listener;

    public ProjectionFetcher(Handler handler, OnProjectionFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchProjections() {
        HandlerThread handlerThread = new HandlerThread("ProjectionFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("finance");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<ProjectionModel> projections = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject projectionJson = jsonArray.getJSONObject(i);

                            UUID idProjection = UUID.fromString(projectionJson.getString("idProjection"));
                            BigDecimal saldoLiquido = new BigDecimal(projectionJson.getString("saldoLiquido"));
                            BigDecimal saldoBruto = new BigDecimal(projectionJson.getString("saldoBruto"));
                            int numeroVenda = projectionJson.getInt("numeroVenda");
                            BigDecimal valorDespesa = new BigDecimal(projectionJson.getString("valorDespesa"));
                            String descricao = projectionJson.getString("descricao");
                            LocalDate data = LocalDate.parse(projectionJson.getString("data"));
                            String cnpj = projectionJson.getString("cnpj");

                            ProjectionModel projection = new ProjectionModel(
                                    idProjection, saldoLiquido, saldoBruto, numeroVenda, valorDespesa, descricao, data, cnpj
                            );
                            projections.add(projection);
                        }

                        if (listener != null) {
                            listener.onProjectionFetchSuccess(projections);
                        }

                    } else {
                        throw new ProjectionFetchException("Error fetching projections");
                    }

                } catch (JSONException | ProjectionFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onProjectionFetchError();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
