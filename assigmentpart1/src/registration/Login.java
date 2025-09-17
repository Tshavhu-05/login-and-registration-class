package registration;

import javax.swing.*;
import java.util.List;

public class Login {

    // GUI-based login process
    public void beginLogin(List<Registration> users) {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No users registered yet. Please register first.");
            return;
        }

        String username = JOptionPane.showInputDialog("Enter your username:");
        if (username == null) return;

        // Masked password input
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(
                null, passwordField, "Enter your password", JOptionPane.OK_CANCEL_OPTION);
        if (option != JOptionPane.OK_OPTION) return;
        String password = new String(passwordField.getPassword());

        // Use authenticate logic
        Registration user = authenticate(users, username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(null,
                    "Login successful! Welcome " + user.getFirstName() + " 🎉");
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Invalid username or password.");
        }
    }

    // Pure logic method for unit testing
    public Registration authenticate(List<Registration> users, String username, String password) {
        for (Registration user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
