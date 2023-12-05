package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import listener.OnOfficeFetchListener;
import persistence.models.OfficeModel;
import request.Connection;
import exception.OfficeFetchException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OfficeFetcher {

    private Handler handler;
    private OnOfficeFetchListener listener;

    public OfficeFetcher(Handler handler, OnOfficeFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchOffices() {
        HandlerThread handlerThread = new HandlerThread("OfficeFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Connection.connectHttp("employee/office");

                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<OfficeModel> offices = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject officeJson = jsonArray.getJSONObject(i);

                            int idCargo = officeJson.getInt(officeJson.getString("idCargo"));
                            String nome = officeJson.getString("nome");
                            String descricao = officeJson.getString("descricao");

                            OfficeModel office = new OfficeModel(nome);
                            offices.add(office);
                        }

                        if (listener != null) {
                            listener.onOfficeFetchSuccess(offices);
                        }

                    } else {
                        if (listener != null) {
                            throw new OfficeFetchException("Failed to fetch offices - result is null");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onOfficeFetchError();
                    }
                } catch (OfficeFetchException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onOfficeFetchError();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
