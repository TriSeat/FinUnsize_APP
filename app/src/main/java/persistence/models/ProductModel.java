package persistence.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductModel {

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
}
