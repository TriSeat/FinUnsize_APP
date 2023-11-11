package persistence.models;

import java.io.Serializable;
import java.util.UUID;

public class PaymentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id_pagamento;
    private String nome;
    private String cnpj;

    // Construtores
    public PaymentModel(UUID idPayment, String nome) {
    }

    public PaymentModel(UUID id_pagamento, String nome, String cnpj) {
        this.id_pagamento = id_pagamento;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public UUID getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(UUID id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
