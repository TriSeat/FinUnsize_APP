package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import persistence.models.ProductModel;

public class SaleItemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_item_venda;
    private SaleModel venda;
    private ProductModel cod_barras;
    private BigDecimal valor_unitario;
    private int quantidade;

    // Construtores
    public SaleItemModel() {
    }

    public SaleItemModel(UUID id_item_venda, SaleModel venda, ProductModel cod_barras,
                         BigDecimal valor_unitario, int quantidade) {
        this.id_item_venda = id_item_venda;
        this.venda = venda;
        this.cod_barras = cod_barras;
        this.valor_unitario = valor_unitario;
        this.quantidade = quantidade;
    }

    // Getters e setters
    public UUID getId_item_venda() {
        return id_item_venda;
    }

    public void setId_item_venda(UUID id_item_venda) {
        this.id_item_venda = id_item_venda;
    }

    public SaleModel getVenda() {
        return venda;
    }

    public void setVenda(SaleModel venda) {
        this.venda = venda;
    }

    public ProductModel getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(ProductModel cod_barras) {
        this.cod_barras = cod_barras;
    }

    public BigDecimal getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(BigDecimal valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
