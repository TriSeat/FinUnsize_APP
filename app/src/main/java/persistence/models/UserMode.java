package persistence.models;

import java.io.Serializable;
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
    private boolean plano_padrao;
    private Role role;
    private String url_image;
    private CompanyModel cnpj;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.SERVICE) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_SERVICE"),
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_CASHIER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"));
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

    public CompanyModel getCompany() {
        return cnpj;
    }
}
