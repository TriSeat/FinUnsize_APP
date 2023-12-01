package persistence.models;

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
    private Role role;
    private String url_image;
    private CompanyModel cnpj;


    public UserModel(UUID id, String nome, String login, String password, String email, int telefone, int cep, Role role, String url_image, CompanyModel cnpj) {

    }

    public List<String> getAuthorities() {
        if (this.role == Role.SERVICE) {
            return Arrays.asList("ROLE_SERVICE", "ROLE_MANAGER", "ROLE_CASHIER");
        } else {
            return Collections.singletonList("ROLE_MANAGER");
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

    public CompanyModel getCompany() {
        return cnpj;
    }
}
