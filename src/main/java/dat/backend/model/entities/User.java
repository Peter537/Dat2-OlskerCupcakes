package dat.backend.model.entities;

import java.util.Objects;

public class User {

    private String email;
    private String password;
    private Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail(), this.getPassword(), this.getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + this.email + '\'' +
                ", password='" + this.password + '\'' +
                ", role=" + this.role +
                '}';
    }
}