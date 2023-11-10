package persistence.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import persistence.models.AddressModel;
import persistence.models.OfficeModel;

public class EmployeeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_funcionario;
    private String cpf;
    private String nome;
    private OfficeModel cargo;
    private String turno;
    private int telefone;
    private LocalDate admissao;
    private AddressModel id_logradouro;
    private BigDecimal salario;
    private String observacao;
    private boolean demitido;
    private String url_image;
    private String cnpj;

    // Construtores
    public EmployeeModel() {
    }

    public EmployeeModel(int id_funcionario, String cpf, String nome, OfficeModel cargo,
                         String turno, int telefone, LocalDate admissao, AddressModel id_logradouro,
                         BigDecimal salario, String observacao, boolean demitido, String url_image,
                         String cnpj) {
        this.id_funcionario = id_funcionario;
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.turno = turno;
        this.telefone = telefone;
        this.admissao = admissao;
        this.id_logradouro = id_logradouro;
        this.salario = salario;
        this.observacao = observacao;
        this.demitido = demitido;
        this.url_image = url_image;
        this.cnpj = cnpj;
    }

    // Getters e setters
    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public OfficeModel getCargo() {
        return cargo;
    }

    public void setCargo(OfficeModel cargo) {
        this.cargo = cargo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public AddressModel getId_logradouro() {
        return id_logradouro;
    }

    public void setId_logradouro(AddressModel id_logradouro) {
        this.id_logradouro = id_logradouro;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isDemitido() {
        return demitido;
    }

    public void setDemitido(boolean demitido) {
        this.demitido = demitido;
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
