package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ExpanseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_despesa;
    private String nome;
    private BigDecimal valor;
    private LocalDate data_vencimento;
    private LocalDate data_pagamento;
    private TypeExpanseModel tipo_despesa;
    private String observacao;
    private boolean aberto;
    private String cnpj;

    // Construtores
    public ExpanseModel(UUID idDespesa, String nome, BigDecimal valor, LocalDate dataVencimento, LocalDate dataPagamento, TypeExpanseModel tipoDespesa, String observacao, boolean aberto) {
    }

    public ExpanseModel(UUID id_despesa, String nome, BigDecimal valor, LocalDate data_vencimento,
                        LocalDate data_pagamento, TypeExpanseModel tipo_despesa, String observacao,
                        boolean aberto, String cnpj) {
        this.id_despesa = id_despesa;
        this.nome = nome;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.tipo_despesa = tipo_despesa;
        this.observacao = observacao;
        this.aberto = aberto;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_despesa() {
        return id_despesa;
    }

    public void setId_despesa(UUID id_despesa) {
        this.id_despesa = id_despesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(LocalDate data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public LocalDate getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(LocalDate data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public TypeExpanseModel getTipo_despesa() {
        return tipo_despesa;
    }

    public void setTipo_despesa(TypeExpanseModel tipo_despesa) {
        this.tipo_despesa = tipo_despesa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
