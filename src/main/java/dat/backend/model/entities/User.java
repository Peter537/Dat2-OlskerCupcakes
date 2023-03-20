package dat.backend.model.entities;

import java.util.Objects;

public class User {

    private int id;
    private String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getId() == user.getId() &&
                this.getUsername().equals(user.getUsername()) &&
                this.getPassword().equals(user.getPassword()) &&
                this.getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getUsername(), this.getPassword(), this.getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", role='" + this.role + '\'' +
                '}';
    }
}