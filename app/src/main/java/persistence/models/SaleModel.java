package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import persistence.models.CashierModel;
import persistence.models.PaymentModel;
import persistence.models.SaleItemModel;

public class SaleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_venda;
    private CashierModel id_caixa;
    private BigDecimal valor_total;
    private int quantidade;
    private PaymentModel pagamento;
    private LocalDateTime data;
    private List<SaleItemModel> saleItemModels = new ArrayList<>();
    private String cnpj;

    // Construtores
    public SaleModel() {
    }

    public SaleModel(UUID id_venda, CashierModel id_caixa, BigDecimal valor_total, int quantidade,
                     PaymentModel pagamento, LocalDateTime data, List<SaleItemModel> saleItemModels,
                     String cnpj) {
        this.id_venda = id_venda;
        this.id_caixa = id_caixa;
        this.valor_total = valor_total;
        this.quantidade = quantidade;
        this.pagamento = pagamento;
        this.data = data;
        this.saleItemModels = saleItemModels;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_venda() {
        return id_venda;
    }

    public void setId_venda(UUID id_venda) {
        this.id_venda = id_venda;
    }

    public CashierModel getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(CashierModel id_caixa) {
        this.id_caixa = id_caixa;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PaymentModel getPagamento() {
        return pagamento;
    }

    public void setPagamento(PaymentModel pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<SaleItemModel> getSaleItemModels() {
        return saleItemModels;
    }

    public void setSaleItemModels(List<SaleItemModel> saleItemModels) {
        this.saleItemModels = saleItemModels;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
