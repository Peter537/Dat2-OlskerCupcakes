package dat.backend.model.entities;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private User user;
    private LocalDateTime readyTime;
    private ShoppingCart shoppingCart;
    private OrderStatus status;

    public Order(User user, LocalDateTime readyTime, ShoppingCart shoppingCart) {
        this.user = user;
        this.readyTime = readyTime;
        this.shoppingCart = shoppingCart;
        this.status = OrderStatus.IN_SHOPPING_CART;
    }

    public Order(int id, User user, LocalDateTime readyTime, ShoppingCart shoppingCart) {
        this.id = id;
        this.user = user;
        this.readyTime = readyTime;
        this.shoppingCart = shoppingCart;
        this.status = OrderStatus.IN_SHOPPING_CART;
    }

    public Order(int id, User user, LocalDateTime readyTime, ShoppingCart shoppingCart, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.readyTime = readyTime;
        this.shoppingCart = shoppingCart;
        this.status = status;
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

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.id +
                ", user=" + this.user +
                ", readyTime=" + this.readyTime +
                ", shoppingCart=" + this.shoppingCart +
                ", status=" + this.status +
                '}';
    }
}