package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductModel implements Serializable {
        private static final long serialVersionUID = 1L;

        private String cod_barras;
        private String nome;
        private int quantidade;
        private InfoProductModel informacoes;
        private LocalDate validade;
        private String descricao;
        private BigDecimal varejo;
        private BigDecimal atacado;
        private LocalDate data_cadastro;
        private String url_image;
        private String cnpj;
        private SupplierModel fornecedor;

        // Construtores
        public ProductModel(String cod_barras, String nome, int quantidade, InfoProductModel informacoes, LocalDate validade, String descricao, BigDecimal varejo, BigDecimal atacado, SupplierModel fornecedor, LocalDate data_cadastro, String url_image) {
        }

        public ProductModel(String cod_barras, String nome, int quantidade, InfoProductModel informacoes,
                            LocalDate validade, String descricao, BigDecimal varejo, BigDecimal atacado,
                            SupplierModel fornecedor, LocalDate data_cadastro, String url_image, String cnpj) {
                this.cod_barras = cod_barras;
                this.nome = nome;
                this.quantidade = quantidade;
                this.informacoes = informacoes;
                this.validade = validade;
                this.descricao = descricao;
                this.varejo = varejo;
                this.atacado = atacado;
                this.fornecedor = fornecedor;
                this.data_cadastro = data_cadastro;
                this.url_image = url_image;
                this.cnpj = cnpj;
        }

        // Getters e setters
        public String getCod_barras() {
                return cod_barras;
        }

        public void setCod_barras(String cod_barras) {
                this.cod_barras = cod_barras;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public int getQuantidade() {
                return quantidade;
        }

        public void setQuantidade(int quantidade) {
                this.quantidade = quantidade;
        }

        public InfoProductModel getInformacoes() {
                return informacoes;
        }

        public void setInformacoes(InfoProductModel informacoes) {
                this.informacoes = informacoes;
        }

        public SupplierModel getFornecedor() {
                return fornecedor;
        }

        public void setFornecedor(SupplierModel fornecedor) {
                this.fornecedor = fornecedor;
        }

        public LocalDate getValidade() {
                return validade;
        }

        public void setValidade(LocalDate validade) {
                this.validade = validade;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public BigDecimal getVarejo() {
                return varejo;
        }

        public void setVarejo(BigDecimal varejo) {
                this.varejo = varejo;
        }

        public BigDecimal getAtacado() {
                return atacado;
        }

        public void setAtacado(BigDecimal atacado) {
                this.atacado = atacado;
        }

        public LocalDate getData_cadastro() {
                return data_cadastro;
        }

        public void setData_cadastro(LocalDate data_cadastro) {
                this.data_cadastro = data_cadastro;
        }

        public String getUrl_image() {
                return url_image;
        }

        public void setUrl_image(String url_image) {
                this.url_image = url_image;
        }

        public String getCnpj() {
                return cnpj;
        }

        public void setCnpj(String cnpj) {
                this.cnpj = cnpj;
        }
}
