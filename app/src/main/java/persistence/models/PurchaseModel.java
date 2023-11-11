package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PurchaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_compra;
    private String nome;
    private PaymentModel id_pagamento;
    private BigDecimal preco_total;
    private LocalDateTime data;
    private SupplierModel id_fornecedor;
    private List<PurchaseItemModel> purchase_item = new ArrayList<>();
    private String cnpj;

    // Construtores
    public PurchaseModel(UUID idPurchase, String name, BigDecimal totalPrice, LocalDateTime date) {
    }

    public PurchaseModel(UUID id_compra, String nome, PaymentModel id_pagamento, BigDecimal preco_total,
                         LocalDateTime data, SupplierModel id_fornecedor, List<PurchaseItemModel> purchase_item,
                         String cnpj) {
        this.id_compra = id_compra;
        this.nome = nome;
        this.id_pagamento = id_pagamento;
        this.preco_total = preco_total;
        this.data = data;
        this.id_fornecedor = id_fornecedor;
        this.purchase_item = purchase_item;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_compra() {
        return id_compra;
    }

    public void setId_compra(UUID id_compra) {
        this.id_compra = id_compra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PaymentModel getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(PaymentModel id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public BigDecimal getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(BigDecimal preco_total) {
        this.preco_total = preco_total;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public SupplierModel getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(SupplierModel id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public List<PurchaseItemModel> getPurchase_item() {
        return purchase_item;
    }

    public void setPurchase_item(List<PurchaseItemModel> purchase_item) {
        this.purchase_item = purchase_item;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
