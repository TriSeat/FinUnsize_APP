package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class InfoProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_item_produto;
    private String marca;
    private String categoria;
    private String tipo;

    // Construtores
    public InfoProductModel() {
    }

    public InfoProductModel(UUID id_item_produto, String marca, String categoria, String tipo) {
        this.id_item_produto = id_item_produto;
        this.marca = marca;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    // Getters e setters
    public UUID getId_item_produto() {
        return id_item_produto;
    }

    public void setId_item_produto(UUID id_item_produto) {
        this.id_item_produto = id_item_produto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
