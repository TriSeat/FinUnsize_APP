package persistence.models;

import java.io.Serializable;

public class CashierModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_caixa;
    private String nome;
    private String status;
    private String cnpj;

    // Construtores
    public CashierModel(int idCaixa, String nome, String status) {
    }

    public CashierModel(int id_caixa, String nome, String status, String cnpj) {
        this.id_caixa = id_caixa;
        this.nome = nome;
        this.status = status;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public int getIdcaixa() {
        return id_caixa;
    }

    public void setId_caixa(int id_caixa) {
        this.id_caixa = id_caixa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIdCaixa() {
        return null;
    }
}
