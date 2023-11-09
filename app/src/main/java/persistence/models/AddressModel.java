package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class AddressModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id_logradouro;
    private int cep;
    private String rua;
    private String numero;
    private String complemento;
    private String referencia;
    private String cidade;

    // Construtores
    public AddressModel() {
    }

    public AddressModel(UUID id_logradouro, int cep, String rua, String numero,
                        String complemento, String referencia, String cidade) {
        this.id_logradouro = id_logradouro;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
        this.cidade = cidade;
    }

    // Getters e setters
    public UUID getId_logradouro() {
        return id_logradouro;
    }

    public void setId_logradouro(UUID id_logradouro) {
        this.id_logradouro = id_logradouro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
