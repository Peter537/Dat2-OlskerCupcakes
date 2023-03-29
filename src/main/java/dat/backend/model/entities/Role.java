package dat.backend.model.entities;

public enum Role {
    ADMIN("Administrator"),
    CUSTOMER("Kunde");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}