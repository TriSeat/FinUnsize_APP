package integration;

import java.util.UUID;

import javax.inject.Inject;

import persistence.models.UserModel;

public class UserSession {

    private final CompanyRepository companyRepository;

    @Inject
    public UserSession(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            return context.getAuthentication();
        }
        return null;
    }

    public String getCurrentUsername() {
        Authentication auth = getAuthentication();
        return auth.getName();
    }

    public String getCurrentLogin() {
        Authentication auth = getAuthentication();
        UserModel userModel = (UserModel) auth.getPrincipal();
        return userModel.getLogin();
    }

    public String getSessionCnpj() {
        Authentication auth = getAuthentication();

        if (auth.getPrincipal() instanceof UserModel) {
            UserModel planUserModel = (UserModel) auth.getPrincipal();
            return planUserModel.getCompany().getCnpj();
        }

        return null;
    }

    public UUID getUUID() {
        Authentication auth = getAuthentication();

        if (auth.getPrincipal() instanceof UserModel) {
            UserModel userModel = (UserModel) auth.getPrincipal();
            return userModel.getId();
        }
        return null;
    }
}

