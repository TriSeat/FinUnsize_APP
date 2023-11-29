package async;

import android.os.Handler;
import android.os.HandlerThread;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import exception.EmployeeCashierFetchException;
import listener.OnEmployeeCashierFetchListener;
import persistence.models.CashierModel;
import persistence.models.EmployeeCashierModel;
import persistence.models.EmployeeModel;
import request.Connection;

public class EmployeeCashierFetcher {

    private Handler handler;
    private OnEmployeeCashierFetchListener listener;

    public EmployeeCashierFetcher(Handler handler, OnEmployeeCashierFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchEmployeeCashier(int idFuncionarioCaixa) throws EmployeeCashierFetchException {
        HandlerThread handlerThread = new HandlerThread("EmployeeCashierFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("employeecashiers/" + idFuncionarioCaixa);

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject employeeCashierJson = jsonObject.getJSONObject("data");

                        CashierModel idCaixa = parseCashierModel(employeeCashierJson.getJSONObject("idCaixa"));
                        EmployeeModel idFuncionario = parseEmployeeModel(employeeCashierJson.getJSONObject("idFuncionario"));

                        String cnpj = employeeCashierJson.getString("cnpj");

                        EmployeeCashierModel employeeCashier = new EmployeeCashierModel(idFuncionarioCaixa, idCaixa, idFuncionario, cnpj);

                        if (listener != null) {
                            listener.onEmployeeCashierFetchSuccess(employeeCashier);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        throw new RuntimeException(new EmployeeCashierFetchException("Error fetching employee cashier data", e));
                    }

                } else {
                    if (listener != null) {
                        listener.onEmployeeCashierFetchError();
                    }
                }
            }
        });
    }

    private CashierModel parseCashierModel(JSONObject cashierJson) throws JSONException {
        int idCaixa = cashierJson.getInt("idCaixa");
        String nome = cashierJson.getString("nome");
        String status = cashierJson.getString("status");

        return new CashierModel(idCaixa, nome, status);
    }

    private EmployeeModel parseEmployeeModel(JSONObject employeeJson) throws JSONException {
        int idFuncionario = employeeJson.getInt("idFuncionario");
        String nome = employeeJson.getString("nome");
        String cargo = employeeJson.getString("cargo");
        String turno = employeeJson.getString("turno");
        BigDecimal salario = (BigDecimal)employeeJson.get("salario");

        return new EmployeeModel(idFuncionario, nome, cargo, turno, salario);
    }
}