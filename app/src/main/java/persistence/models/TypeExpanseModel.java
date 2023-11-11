package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class TypeExpanseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_despesa;
    private String nome;
    private String descicao;
    private String cnpj;

    // Construtores
    public TypeExpanseModel(UUID idTypeExpanse, String nome, String descricao) {
    }

    public TypeExpanseModel(UUID id_despesa, String nome, String descicao, String cnpj) {
        this.id_despesa = id_despesa;
        this.nome = nome;
        this.descicao = descicao;
        this.cnpj = cnpj;
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

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
