package persistence.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String nome;
    private String login;
    private String password;
    private String email;
    private int telefone;
    private int cep;
    private int plano_padrao;
    private Role role;
    private String url_image;
    private String cnpj;


    public UserModel(String nome, String login, String password, String email, int telefone, int cep, int plano_padrao, Role role, String cnpj, String url_image) {
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

    public String toJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nome", nome);
            jsonObject.put("login", login);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            jsonObject.put("telefone", telefone);
            jsonObject.put("cep", cep);
            jsonObject.put("plano_padrao", plano_padrao);
            jsonObject.put("role", role.toString()); // assumindo que Role é uma enumeração
            jsonObject.put("url_image", url_image);
            jsonObject.put("cnpj", cnpj);

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "{}"; // ou outra representação padrão em caso de erro
        }
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
