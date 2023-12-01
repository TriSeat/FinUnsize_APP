package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import listener.OnLogCashierFetchListener;
import persistence.models.LogCashierModel;
import request.Connection;
import exception.LogCashierFetchException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogCashierFetcher {

    private Handler handler;
    private OnLogCashierFetchListener listener;

    public LogCashierFetcher(Handler handler, OnLogCashierFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchLogCashiers() {
        HandlerThread handlerThread = new HandlerThread("LogCashierFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("cashier");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<LogCashierModel> logCashiers = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject logCashierJson = jsonArray.getJSONObject(i);

                            UUID idLogCaixa = UUID.fromString(logCashierJson.getString("idLogCaixa"));
                            UUID idCaixa = UUID.fromString(logCashierJson.getString("idCaixa"));
                            LocalDateTime dataFuncionamento = LocalDateTime.parse(logCashierJson.getString("dataFuncionamento"));
                            BigDecimal valorInicial = new BigDecimal(logCashierJson.getString("valorInicial"));
                            BigDecimal valorFinal = new BigDecimal(logCashierJson.getString("valorFinal"));
                            LocalDateTime abertura = LocalDateTime.parse(logCashierJson.getString("abertura"));
                            LocalDateTime fechamento = LocalDateTime.parse(logCashierJson.getString("fechamento"));
                            String cnpj = logCashierJson.getString("cnpj");

                            LogCashierModel logCashier = new LogCashierModel(idLogCaixa, idCaixa, dataFuncionamento,
                                    valorInicial, valorFinal, abertura, fechamento, cnpj);
                            logCashiers.add(logCashier);
                        }

                        if (listener != null) {
                            listener.onLogCashierFetchSuccess(logCashiers);
                        }

                    } else {
                        if (listener != null) {
                            throw new LogCashierFetchException("Failed to fetch log cashiers - result is null");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onLogCashierFetchError();
                    }
                } catch (LogCashierFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onLogCashierFetchError();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
