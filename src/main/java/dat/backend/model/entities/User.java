package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrderFacade;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class User {

    private String email;
    private String password;
    private Role role;
    private Order currentOrder;
    private float balance;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = 0;
        this.currentOrder = new Order(this);
    }

    public User(String email, String password, Role role, float balance) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.currentOrder = new Order(this);
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

    public Order getCurrentOrder() {
        return this.currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
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
        try {
            return this.getOrders().get(this.getOrders().size() - 1).getStatus();
        } catch (IndexOutOfBoundsException e) {
            return OrderStatus.NO_ORDERS;
        }
    }

    public String getFormattedBalance() {
        return NumberFormat.getNumberInstance(Locale.GERMAN).format(this.balance);
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
    public String toString() {
        return "User{" +
                "email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", role=" + this.getRole() +
                ", currentOrder=" + this.getCurrentOrder() +
                ", balance=" + this.getBalance() +
                '}';
    }
}