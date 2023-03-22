package dat.backend.model.services;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication {

    public static boolean isRoleAllowed(Role role, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return isRoleAllowed(role, user);
        }
        return false;
    }

    public static boolean isRoleAllowed(Role role, User user) {
        return user.getRole().equals(role);
    }

    public static boolean isAdmin(HttpServletRequest request) {
        return isRoleAllowed(Role.ADMIN, request);
    }

    public static boolean isAdmin(User user) {
        return isRoleAllowed(Role.ADMIN, user);
    }
}