package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ProjectionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_projecao;
    private BigDecimal saldo_liquido;
    private BigDecimal saldo_bruto;
    private int numero_venda;
    private BigDecimal valor_despesa;
    private String descricao;
    private LocalDate data;
    private String cnpj;

    // Construtores
    public ProjectionModel() {
    }

    public ProjectionModel(UUID id_projecao, BigDecimal saldo_liquido, BigDecimal saldo_bruto,
                           int numero_venda, BigDecimal valor_despesa, String descricao, LocalDate data,
                           String cnpj) {
        this.id_projecao = id_projecao;
        this.saldo_liquido = saldo_liquido;
        this.saldo_bruto = saldo_bruto;
        this.numero_venda = numero_venda;
        this.valor_despesa = valor_despesa;
        this.descricao = descricao;
        this.data = data;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_projecao() {
        return id_projecao;
    }

    public void setId_projecao(UUID id_projecao) {
        this.id_projecao = id_projecao;
    }

    public BigDecimal getSaldo_liquido() {
        return saldo_liquido;
    }

    public void setSaldo_liquido(BigDecimal saldo_liquido) {
        this.saldo_liquido = saldo_liquido;
    }

    public BigDecimal getSaldo_bruto() {
        return saldo_bruto;
    }

    public void setSaldo_bruto(BigDecimal saldo_bruto) {
        this.saldo_bruto = saldo_bruto;
    }

    public int getNumero_venda() {
        return numero_venda;
    }

    public void setNumero_venda(int numero_venda) {
        this.numero_venda = numero_venda;
    }

    public BigDecimal getValor_despesa() {
        return valor_despesa;
    }

    public void setValor_despesa(BigDecimal valor_despesa) {
        this.valor_despesa = valor_despesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
