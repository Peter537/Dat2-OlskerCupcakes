package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrderFacade;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class User {

    private String email;
    private String password;
    private Role role;
    private ShoppingCart shoppingCart;
    private float balance;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.shoppingCart = new ShoppingCart();
        this.balance = 0;
    }

    public User(String email, String password, Role role, float balance) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.shoppingCart = new ShoppingCart();
        this.balance = balance;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public float getBalance() {
        return this.balance;
    }

    public void addBalance(float amount) {
        this.balance += amount;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Order> getOrders() {
        try {
            return OrderFacade.getAllOrdersByUser(this, ApplicationStart.getConnectionPool().getConnection());
        } catch (DatabaseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderStatus getLastOrderStatus() {
        return OrderStatus.READY; // TODO: Implement this
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getEmail().equals(user.getEmail()) &&
                this.getPassword().equals(user.getPassword()) &&
                this.getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail(), this.getPassword(), this.getRole(), this.getShoppingCart());
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", role=" + this.getRole() +
                ", shoppingCart=" + this.getShoppingCart() +
                ", balance=" + this.getBalance() +
                '}';
    }
}