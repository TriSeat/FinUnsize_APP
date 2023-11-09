package persistence.models;

import java.io.Serializable;
import java.util.UUID;

import persistence.models.AddressModel;

public class SupplierModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_fornecedor;
    private String nome;
    private AddressModel id_endereco;
    private String descricao;
    private String url_image;
    private String cnpj;

    // Construtores
    public SupplierModel() {
    }

    public SupplierModel(UUID id_fornecedor, String nome, AddressModel id_endereco,
                         String descricao, String url_image, String cnpj) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
        this.id_endereco = id_endereco;
        this.descricao = descricao;
        this.url_image = url_image;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(UUID id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AddressModel getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(AddressModel id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
