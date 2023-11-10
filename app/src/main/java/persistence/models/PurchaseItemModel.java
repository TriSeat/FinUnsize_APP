package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import persistence.models.ProductModel;
import persistence.models.PurchaseModel;

public class PurchaseItemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_item_compra;
    private ProductModel cod_barras;
    private BigDecimal valor_unitario;
    private int quantidade;
    private PurchaseModel purchase;

    // Construtores
    public PurchaseItemModel() {
    }

    public PurchaseItemModel(UUID id_item_compra, ProductModel cod_barras, BigDecimal valor_unitario,
                             int quantidade, PurchaseModel purchase) {
        this.id_item_compra = id_item_compra;
        this.cod_barras = cod_barras;
        this.valor_unitario = valor_unitario;
        this.quantidade = quantidade;
        this.purchase = purchase;
    }

    // Getters e setters
    public UUID getId_item_compra() {
        return id_item_compra;
    }

    public void setId_item_compra(UUID id_item_compra) {
        this.id_item_compra = id_item_compra;
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

    public PurchaseModel getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseModel purchase) {
        this.purchase = purchase;
    }
}
