package dat.backend.model.entities;

public enum OrderStatus {
    NO_ORDERS("Ingen Ordre", 0),
    CANCELLED("Annulleret", 1),
    IN_SHOPPING_CART("I Indkøbskurv", 2),
    PENDING("Afventer", 4),
    READY("Klar", 5),
    DELIVERED("Leveret", 3);

    private final String name;
    private final int value;

    OrderStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}