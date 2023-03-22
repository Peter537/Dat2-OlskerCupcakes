package dat.backend.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.services.Authentication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationTest {

    private final User userAdmin = new User("admin@test.dk", "123", Role.ADMIN);
    private final User userCustomer = new User("customer@test.dk", "123", Role.CUSTOMER);

    @Test
    public void testIsAdmin_admin() {
        assertTrue(Authentication.isAdmin(userAdmin));
    }

    @Test
    public void testIsAdmin_customer() {
        assertFalse(Authentication.isAdmin(userCustomer));
    }

    @Test
    public void testIsRoleAllowed_adminRole_admin() {
        assertTrue(Authentication.isRoleAllowed(Role.ADMIN, userAdmin));
    }

    @Test
    public void testIsRoleAllowed_adminRole_customer() {
        assertFalse(Authentication.isRoleAllowed(Role.ADMIN, userCustomer));
    }
}