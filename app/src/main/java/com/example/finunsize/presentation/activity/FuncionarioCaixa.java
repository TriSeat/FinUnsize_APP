package com.example.finunsize.presentation.activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finunsize.R;

import async.EmployeeCashierFetcher;
import exception.EmployeeCashierFetchException;
import listener.OnEmployeeCashierFetchListener;
import persistence.models.CashierModel;
import persistence.models.EmployeeCashierModel;
import persistence.models.EmployeeModel;

public class FuncionarioCaixa extends AppCompatActivity implements OnEmployeeCashierFetchListener {

    private TextView textViewIdCaixa;
    private TextView textViewNomeCaixa;
    private TextView textViewStatusCaixa;
    private TextView textViewIdFuncionario;
    private TextView textViewNomeFuncionario;
    private TextView textViewCargo;
    private TextView textViewTurno;
    private TextView textViewSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionarios);

        textViewIdCaixa = findViewById(R.id.textViewIdCaixa);
        textViewNomeCaixa = findViewById(R.id.textViewNomeCaixa);
        textViewStatusCaixa = findViewById(R.id.textViewStatusCaixa);
        textViewIdFuncionario = findViewById(R.id.textViewIdFuncionario);
        textViewNomeFuncionario = findViewById(R.id.textViewNomeFuncionario);
        textViewCargo = findViewById(R.id.textViewCargo);
        textViewTurno = findViewById(R.id.textViewTurno);
        textViewSalario = findViewById(R.id.textViewSalario);

        fetchEmployeeCashierData(1); // Substitua 123 pelo ID correto do funcionário/caixa desejado
    }

    private void fetchEmployeeCashierData(int idFuncionarioCaixa) {
        Handler handler = new Handler(Looper.getMainLooper());
        EmployeeCashierFetcher employeeCashierFetcher = new EmployeeCashierFetcher(handler, this);

        try {
            employeeCashierFetcher.fetchEmployeeCashier(idFuncionarioCaixa);
        } catch (EmployeeCashierFetchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEmployeeCashierFetchSuccess(EmployeeCashierModel employeeCashier) {
        CashierModel idCaixa = employeeCashier.getId_caixa();
        EmployeeModel idFuncionario = employeeCashier.getId_funcionario();

        textViewIdCaixa.setText("ID Caixa: " + idCaixa.getIdcaixa());
        textViewNomeCaixa.setText("Nome Caixa: " + idCaixa.getNome());
        textViewStatusCaixa.setText("Status Caixa: " + idCaixa.getStatus());

        textViewIdFuncionario.setText("ID Funcionário: " + idFuncionario.getId_funcionario());
        textViewNomeFuncionario.setText("Nome Funcionário: " + idFuncionario.getNome());
        textViewCargo.setText("Cargo: " + idFuncionario.getCargo());
        textViewTurno.setText("Turno: " + idFuncionario.getTurno());
        textViewSalario.setText("Salário: " + idFuncionario.getSalario());
    }

    @Override
    public void onEmployeeCashierFetchError() {

    }
}