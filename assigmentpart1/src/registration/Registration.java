package registration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Registration {
    // user data fields
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String phoneNumber;

    // Constructor to create a new user
    public Registration(String firstName, String lastName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Getters (used in the login class)
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }

    // Main Registration process
    public static Registration startRegistration() {
        String firstName = JOptionPane.showInputDialog("Please enter your first name");
        if (firstName == null) return null;

        String lastName = JOptionPane.showInputDialog("Please enter your last name");
        if (lastName == null) return null;

        String username = getValidUsername();
        if (username == null) return null;

        String password = getValidPassword();
        if (password == null) return null;

        String phoneNumber = getValidPhoneNumber();
        if (phoneNumber == null) return null;

        JOptionPane.showMessageDialog(null,
                "Registration Successful!\n" +
                        "First Name: " + firstName + "\n" +
                        "Last Name: " + lastName + "\n" +
                        "Username: " + username + "\n" +
                        "Phone (+27 format): " + phoneNumber);

        return new Registration(firstName, lastName, username, password, phoneNumber);
    }

    // Validation helpers 
    private static String getValidUsername() {
        String input;
        do {
            input = JOptionPane.showInputDialog("Enter username (≤5 characters and must contain '_'):");
            if (input == null) return null;
        } while (!isValidUsername(input));
        return input;
    }

    // Now PUBLIC for testing
    public static boolean isValidUsername(String username) {
        if (username.length() > 5 || !username.contains("_")) {
            JOptionPane.showMessageDialog(null, "Username must be ≤5 characters and contain '_'");
            return false;
        }
        return true;
    }

    private static String getValidPassword() {
        JPasswordField passwordField = new JPasswordField();
        int option;
        String input;
        do {
            option = JOptionPane.showConfirmDialog(null, passwordField,
                    "Enter password (≥8 chars, must include:\n- 1 capital letter\n- 1 number\n- 1 special character)",
                    JOptionPane.OK_CANCEL_OPTION);
            if (option != JOptionPane.OK_OPTION) return null;
            input = new String(passwordField.getPassword());
        } while (!isValidPassword(input));
        return input;
    }

    // Now PUBLIC for testing
    public static boolean isValidPassword(String password) {
        boolean hasNumber = false, hasCapital = false, hasSpecial = false;
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null,
                    "Password must be ≥8 characters, include 1 capital letter, 1 number, and 1 special character.");
            return false;
        }
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasNumber = true;
            if (Character.isUpperCase(c)) hasCapital = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        if (!(hasNumber && hasCapital && hasSpecial)) {
            JOptionPane.showMessageDialog(null,
                    "Password must be ≥8 characters, include 1 capital letter, 1 number, and 1 special character.");
            return false;
        }
        return true;
    }

    private static String getValidPhoneNumber() {
        String input;
        do {
            input = JOptionPane.showInputDialog(
                    "Enter phone number (+27 followed by 9 digits):\nExample: +27821234567");
            if (input == null) return null;
        } while (!isValidPhoneNumber(input));
        return input;
    }

    // Now PUBLIC for testing
    public static boolean isValidPhoneNumber(String phone) {
        if (!phone.matches("\\+27\\d{9}")) {
            JOptionPane.showMessageDialog(null,
                    "Phone number must start with +27 and contain exactly 9 digits after it.\nExample: +27821234567");
            return false;
        }
        return true;
    }

    // Main menu 
    public static void main(String[] args) {
        List<Registration> users = new ArrayList<>();
        Login login = new Login();

        while (true) {
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Choose an option:",
                    "User System",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) { // Register
                Registration newUser = Registration.startRegistration();
                if (newUser != null) {
                    users.add(newUser);
                }
            } else if (choice == 1) { // Login
                login.beginLogin(users);
            } else { // Exit
                JOptionPane.showMessageDialog(null, "Goodbye!");
                break;
            }
        }
    }
}
