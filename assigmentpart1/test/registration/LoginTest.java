package registration;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoginTest {

    private List<Registration> users;
    private Login login;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        users.add(new Registration("Alice", "Smith", "al_1", "Password1!", "+27821234567"));
        users.add(new Registration("Bob", "Brown", "bo_2", "Secure2@", "+27829876543"));
        login = new Login();
    }

    @Test
    public void testSuccessfulLogin() {
        Registration user = login.authenticate(users, "al_1", "Password1!");
        assertNotNull(user);
        assertEquals("Alice", user.getFirstName());
    }

    @Test
    public void testLoginWithWrongPassword() {
        Registration user = login.authenticate(users, "al_1", "WrongPass!");
        assertNull(user);
    }

    @Test
    public void testLoginWithUnknownUsername() {
        Registration user = login.authenticate(users, "unknown", "Password1!");
        assertNull(user);
    }
}
