package persistence.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("telefone")
    private int telefone;
    @SerializedName("cep")
    private int cep;
    @SerializedName("plano_padrao")
    private String plano_padrao;
    @SerializedName("role")
    private Role role;
    @SerializedName("url_image")
    private String url_image;
    @SerializedName("cnpj")
    private String cnpj;


    public UserModel(String nome, String login, String password, String email, int telefone, int cep, String plano_padrao, Role role, String cnpj, String url_image) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.plano_padrao = plano_padrao;
        this.role = role;
        this.url_image = url_image;
        this.cnpj = cnpj;
    }

    public List<String> getAuthorities() {
        if (this.role == Role.PLAN) {
            return Arrays.asList("PLAN", "MANAGER", "CASHIER");
        } else {
            return Collections.singletonList("MANAGER");
        }
    }

    public boolean hasPermission(Role permission) {
        return this.role == permission;
    }

    public String getUsername() {
        return this.login;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getCompany() {
        return cnpj;
    }
}
