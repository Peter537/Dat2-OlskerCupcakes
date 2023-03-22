package dat.backend.model.entities;

public enum OrderStatus {
    CANCELLED(1),
    IN_SHOPPING_CART(2),
    PENDING(4),
    READY(5),
    DELIVERED(3);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}