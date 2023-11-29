package async;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.EmployeeFetchException;
import listener.OnEmployeeFetchListener;
import persistence.models.AddressModel;
import persistence.models.EmployeeModel;
import persistence.models.OfficeModel;
import request.Connection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeFetcher {

    private Looper handler;
    private OnEmployeeFetchListener listener;

    public EmployeeFetcher(Looper handler, OnEmployeeFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchEmployees() throws EmployeeFetchException {
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
                            String turno = employeeJson.getString("turno");
                            BigDecimal salario = (BigDecimal) employeeJson.get("salario");

                            OfficeModel cargo = parseOfficeModel(employeeJson.getJSONObject("cargo"));
                            AddressModel idLogradouro = parseAddressModel(employeeJson.getJSONObject("idLogradouro"));

                            EmployeeModel employee = new EmployeeModel(idFuncionario, cpf, nome, turno, salario);
                            employees.add(employee);
                        }
                        if (listener != null) {
                            listener.onEmployeeFetchSuccess(employees);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        try {
                            throw new EmployeeFetchException("Error fetching employees data", e);
                        } catch (EmployeeFetchException ex) {
                            throw new RuntimeException(ex);
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
