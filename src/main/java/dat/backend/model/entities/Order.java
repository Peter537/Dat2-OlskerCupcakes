package dat.backend.model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private int id;
    private User user;
    private LocalDateTime readyTime;
    private OrderStatus status;
    private ShoppingCart shoppingCart;

    public Order(User user) {
        this.user = user;
        this.status = OrderStatus.IN_SHOPPING_CART;
        this.shoppingCart = new ShoppingCart();
    }

    public Order(User user, LocalDateTime readyTime) {
        this.user = user;
        this.readyTime = readyTime;
        this.status = OrderStatus.IN_SHOPPING_CART;
        this.shoppingCart = new ShoppingCart();
    }

    public Order(int id, User user, LocalDateTime readyTime) {
        this.id = id;
        this.user = user;
        this.readyTime = readyTime;
        this.status = OrderStatus.IN_SHOPPING_CART;
        this.shoppingCart = new ShoppingCart();
    }

    public Order(int id, User user, LocalDateTime readyTime, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.readyTime = readyTime;
        this.status = status;
        this.shoppingCart = new ShoppingCart();
    }

    public Order(int id, User user, LocalDateTime readyTime, ShoppingCart shoppingCart, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.readyTime = readyTime;
        this.status = status;
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

    public String getFormattedReadyTime() {
        return readyTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public void setReadyTime(LocalDateTime readyTime) {
        this.readyTime = readyTime;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public float getPrice() {
        float totalPrice = 0;
        for (Cupcake cupcake : this.shoppingCart.getCupcakeList()) {
            totalPrice += cupcake.getPrice();
        }
        return totalPrice;
    }

    public int getCupcakeCount() {
        return this.shoppingCart.getCupcakeList().size();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.getId() +
                ", user_email=" + this.getUser().getEmail() +
                ", readyTime=" + this.getReadyTime() +
                ", status=" + this.getStatus() +
                ", shoppingCart=" + this.getShoppingCart() +
                '}';
    }
}