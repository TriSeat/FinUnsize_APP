package persistence.models;

import java.io.Serializable;

public class EmployeeCashierModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id_funcionario_caixa;
    private CashierModel id_caixa;
    private EmployeeModel id_funcionario;
    private String cnpj;

    public EmployeeCashierModel(int id_funcionario_caixa, CashierModel id_caixa,
                                EmployeeModel id_funcionario, String cnpj) {
        this.id_funcionario_caixa = id_funcionario_caixa;
        this.id_caixa = id_caixa;
        this.id_funcionario = id_funcionario;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public int getId_funcionario_caixa() {
        return id_funcionario_caixa;
    }

    public void setId_funcionario_caixa(int id_funcionario_caixa) {
        this.id_funcionario_caixa = id_funcionario_caixa;
    }

    public CashierModel getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(CashierModel id_caixa) {
        this.id_caixa = id_caixa;
    }

    public EmployeeModel getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(EmployeeModel id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
