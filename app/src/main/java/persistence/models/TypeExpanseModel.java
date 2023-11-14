package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class TypeExpanseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_despesa;
    private String nome;
    private String descricao;
    private String cnpj;

<<<<<<< HEAD
    public TypeExpanseModel(UUID idTypeExpanse, String nome, String descricao) {
        this.id_despesa = idTypeExpanse;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Outro construtor com o campo adicional "cnpj"
=======
    // Construtores
    public TypeExpanseModel(UUID idTypeExpanse) {
    }

>>>>>>> 1f3b5081a5bea2e13a30673e489782573354de5b
    public TypeExpanseModel(UUID id_despesa, String nome, String descricao, String cnpj) {
        this.id_despesa = id_despesa;
        this.nome = nome;
        this.descricao = descricao;
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
