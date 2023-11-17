package persistence.models;

public enum Role {

    SERVICE("ROLE_SERVICE"),
    MANAGER("ROLE_MANAGER"),
    CASHIER("ROLE_CASHIER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
