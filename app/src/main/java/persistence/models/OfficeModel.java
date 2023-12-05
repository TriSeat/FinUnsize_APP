package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class OfficeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id_cargo;
    private String nome;
    private String descricao;
    private String cnpj;

    // Construtores
    public OfficeModel(String nome) {
    }

    public OfficeModel(int id_cargo, String nome, String descricao, String cnpj) {
        this.id_cargo = id_cargo;
        this.nome = nome;
        this.descricao = descricao;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
