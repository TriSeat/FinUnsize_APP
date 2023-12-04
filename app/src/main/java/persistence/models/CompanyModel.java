package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CompanyModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nome;

    private String slogan;

    private String segmento;

    private int cep;

    private BigDecimal renda_media;

    private BigDecimal balanco_atual;

    private BigDecimal despesa_media;

    private String razao;

    private String url_image;

    public CompanyModel(String cnpj) {
        this.cnpj = cnpj;
    }

    public CompanyModel(String cnpj, String nome, String slogan, String segmento, int cep, BigDecimal renda_media,
                        BigDecimal balanco_atual, BigDecimal despesa_media,
                        String razao) {
        this.cep = cep;
        this.nome = nome;
        this.slogan = slogan;
        this.segmento = segmento;
        this.renda_media = renda_media;
        this.balanco_atual = balanco_atual;
        this.despesa_media = despesa_media;
        this.razao = razao;
        this.url_image = url_image;
        this.cnpj = cnpj;
    }

    // Getters e setters

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public BigDecimal getRenda_media() {
        return renda_media;
    }

    public void setRenda_media(BigDecimal renda_media) {
        this.renda_media = renda_media;
    }

    public BigDecimal getBalanco_atual() {
        return balanco_atual;
    }

    public void setBalanco_atual(BigDecimal balanco_atual) {
        this.balanco_atual = balanco_atual;
    }

    public BigDecimal getDespesa_media() {
        return despesa_media;
    }

    public void setDespesa_media(BigDecimal despesa_media) {
        this.despesa_media = despesa_media;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getUrl_image() {
        return url_image;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
