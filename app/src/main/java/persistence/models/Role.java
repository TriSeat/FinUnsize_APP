package persistence.models;

public enum Role {

    PLAN("PLAN"),
    MANAGER("MANAGER"),
    CASHIER("CASHIER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
