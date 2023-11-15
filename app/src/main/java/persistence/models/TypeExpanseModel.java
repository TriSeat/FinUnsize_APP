package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class TypeExpanseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_despesa;
    private String nome;
    private String descricao;
    private String cnpj;


    public TypeExpanseModel(UUID idTypeExpanse, String nome, String descricao, String cnpj) {
        this.id_despesa = idTypeExpanse;
        this.nome = this.nome;
        this.descricao = this.descricao;
        this.cnpj = this.cnpj;
    }


    // Getters e setters
    public UUID getId_despesa() {
        return id_despesa;
    }

    public void setId_despesa(UUID id_despesa) {
        this.id_despesa = id_despesa;
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
