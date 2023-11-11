// EmployeeFetcher.java
package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import listener.OnEmployeeFetchListener;
import persistence.models.AddressModel;
import persistence.models.EmployeeModel;
import persistence.models.OfficeModel;
import request.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeFetcher {

    private Handler handler;
    private OnEmployeeFetchListener listener;

    public EmployeeFetcher(Handler handler, OnEmployeeFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchEmployees() {
        HandlerThread handlerThread = new HandlerThread("EmployeeFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("employees");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<EmployeeModel> employees = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject employeeJson = jsonArray.getJSONObject(i);

                            int idFuncionario = employeeJson.getInt("idFuncionario");
                            String cpf = employeeJson.getString("cpf");
                            String nome = employeeJson.getString("nome");


                            OfficeModel cargo = parseOfficeModel(employeeJson.getJSONObject("cargo"));
                            AddressModel idLogradouro = parseAddressModel(employeeJson.getJSONObject("idLogradouro"));


                            EmployeeModel employee = new EmployeeModel(idFuncionario, cpf);
                            employees.add(employee);
                        }

                        if (listener != null) {
                            listener.onEmployeeFetchSuccess(employees);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onEmployeeFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.onEmployeeFetchError();
                    }
                }
            }
        });
    }

    private OfficeModel parseOfficeModel(JSONObject officeJson) throws JSONException {
        UUID idCargo = UUID.fromString(officeJson.getString("idCargo"));
        String nome = officeJson.getString("nome");
        String descricao = officeJson.getString("descricao");


        return new OfficeModel(idCargo, nome, descricao);
    }

    private AddressModel parseAddressModel(JSONObject addressJson) throws JSONException {
        UUID idLogradouro = UUID.fromString(addressJson.getString("idLogradouro"));

        return new AddressModel(idLogradouro);
    }
}
