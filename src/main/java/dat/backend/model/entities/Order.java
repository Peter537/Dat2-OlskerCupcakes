package dat.backend.model.entities;

import java.time.LocalDateTime;

public class Order {

    private User user;
    private LocalDateTime readyTime;
    private ShoppingCart shoppingCart;

    public Order(User user, LocalDateTime readyTime, ShoppingCart shoppingCart) {
        this.user = user;
        this.readyTime = readyTime;
        this.shoppingCart = shoppingCart;
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
        return "Order{" +
                "user=" + this.user +
                ", readyTime=" + this.readyTime +
                ", shoppingCart=" + this.shoppingCart +
                '}';
    }
}