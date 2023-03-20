package dat.backend.model.entities;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private User user;
    private LocalDateTime readyTime;
    private ShoppingCart shoppingCart;

    public Order(User user, LocalDateTime readyTime, ShoppingCart shoppingCart) {
        this.user = user;
        this.readyTime = readyTime;
        this.shoppingCart = shoppingCart;
    }

    public Order(int id, User user, LocalDateTime readyTime, ShoppingCart shoppingCart) {
        this.id = id;
        this.user = user;
        this.readyTime = readyTime;
        this.shoppingCart = shoppingCart;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getReadyTime() {
        return this.readyTime;
    }

    public void setReadyTime(LocalDateTime readyTime) {
        this.readyTime = readyTime;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + this.getId() + "'" +
            ", user='" + this.getUser() + "'" +
            ", readyTime='" + this.getReadyTime() + "'" +
            ", shoppingCart='" + this.getShoppingCart() + "'" +
            "}";
    }
}