package registration;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationTest {

    // ---------- Username Tests ----------
    @Test
    public void testValidUsername() {
        assertTrue(Registration.isValidUsername("kyl_1"));
    }

    @Test
    public void testUsernameTooLong() {
        assertFalse(Registration.isValidUsername("kyle!!!!!!!")); // > 5 chars
    }

    // ---------- Password Tests ----------
    @Test
    public void testValidPassword() {
        assertTrue(Registration.isValidPassword("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(Registration.isValidPassword("password")); // < 8 chars
    }

    // ---------- Phone Number Tests ----------
    @Test
    public void testValidPhoneNumber() {
        assertTrue(Registration.isValidPhoneNumber("+27838968976"));
    }

    @Test
    public void testPhoneNumberWrongPrefix() {
        assertFalse(Registration.isValidPhoneNumber("08966553")); // no +27
    }
}
