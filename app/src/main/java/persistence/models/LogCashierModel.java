package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import persistence.models.CashierModel;

public class LogCashierModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_log_caixa;
    private CashierModel id_caixa;
    private LocalDateTime data_funcionamento;
    private BigDecimal valor_inicial;
    private BigDecimal valor_final;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private String cnpj;

    // Construtores
    public LogCashierModel() {
    }

    public LogCashierModel(UUID id_log_caixa, CashierModel id_caixa, LocalDateTime data_funcionamento,
                           BigDecimal valor_inicial, BigDecimal valor_final, LocalDateTime abertura,
                           LocalDateTime fechamento, String cnpj) {
        this.id_log_caixa = id_log_caixa;
        this.id_caixa = id_caixa;
        this.data_funcionamento = data_funcionamento;
        this.valor_inicial = valor_inicial;
        this.valor_final = valor_final;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_log_caixa() {
        return id_log_caixa;
    }

    public void setId_log_caixa(UUID id_log_caixa) {
        this.id_log_caixa = id_log_caixa;
    }

    public CashierModel getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(CashierModel id_caixa) {
        this.id_caixa = id_caixa;
    }

    public LocalDateTime getData_funcionamento() {
        return data_funcionamento;
    }

    public void setData_funcionamento(LocalDateTime data_funcionamento) {
        this.data_funcionamento = data_funcionamento;
    }

    public BigDecimal getValor_inicial() {
        return valor_inicial;
    }

    public void setValor_inicial(BigDecimal valor_inicial) {
        this.valor_inicial = valor_inicial;
    }

    public BigDecimal getValor_final() {
        return valor_final;
    }

    public void setValor_final(BigDecimal valor_final) {
        this.valor_final = valor_final;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
