package com.perisic.tomato.peripherals;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = -6921462126880570161L;

    // Main method to launch the application
    public static void main(String[] args) {
        new LoginGUI(); // Create an instance of LoginGUI without assigning it to a variable
    }

    // GUI components
    JButton blogin = new JButton("Login");
    JButton bregister = new JButton("Register");  
    JPanel panel = new JPanel();
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);
    JPasswordField confirmPass = new JPasswordField(15); // Added field for confirming password
    JLabel userLabel = new JLabel("Username:");
    JLabel passLabel = new JLabel("Password:");
    JLabel confirmPassLabel = new JLabel("Confirm Password:"); // Added label for confirming password

    // Data model for login information
    LoginData ldata = new LoginData();

    // Constructor for the LoginGUI
    LoginGUI() {
        super("Login");
        setSize(300, 220); // Increased height to accommodate the new field
        setLocation(500, 280);
        panel.setLayout(null);

        // Set the positions and sizes of GUI components
        userLabel.setBounds(10, 30, 80, 20);
        passLabel.setBounds(10, 65, 80, 20);
        confirmPassLabel.setBounds(10, 100, 120, 20); // Added position and size for confirming password label
        txuser.setBounds(130, 30, 150, 20); 
        pass.setBounds(130, 65, 150, 20); 
        confirmPass.setBounds(130, 100, 150, 20); // Added position and size for confirming password field
        blogin.setBounds(20, 140, 80, 20);
        bregister.setBounds(170, 140, 100, 20);  

        // Add components to the panel
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(confirmPassLabel); // Added confirming password label
        panel.add(blogin);
        panel.add(bregister);  
        panel.add(txuser);
        panel.add(pass);
        panel.add(confirmPass); // Added confirming password field

        // Add the panel to the JFrame
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Attach action listeners to buttons
        actionlogin();
        actionregister();  
    }

    // Method to handle login button action
    public void actionlogin() {
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Retrieve entered username and password
                String puname = txuser.getText();
                char[] passwordChars = pass.getPassword();
                String ppaswd = String.valueOf(passwordChars);

                // Check if the login is successful
                if (ldata.checkPassword(puname, ppaswd)) {
                    // Open the main game window
                    GameGUI theGame = new GameGUI(puname);
                    theGame.setVisible(true);

                    // Dispose of the login window
                    dispose();
                } else {
                    // Display an error message for unsuccessful login
                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    txuser.setText("");
                    pass.setText("");
                    txuser.requestFocus();
                }

                // Clear the password characters from memory
                java.util.Arrays.fill(passwordChars, ' ');
            }
        });
    }

    // Method to handle register button action
    public void actionregister() {
        bregister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Create a panel for registration input
                JPanel registerPanel = new JPanel();
                JLabel usernameLabel = new JLabel("Username:");
                JLabel passwordLabel = new JLabel("Password:");
                JLabel confirmPasswordLabel = new JLabel("Confirm Password:"); // Added label for confirming password
                JTextField usernameField = new JTextField(15);
                JPasswordField passwordField = new JPasswordField(15);
                JPasswordField confirmPasswordField = new JPasswordField(15); // Added field for confirming password

                // Set the layout for the register panel
                registerPanel.setLayout(new GridLayout(3, 2)); // Adjusted layout to accommodate the new field

                // Add components to the register panel
                registerPanel.add(usernameLabel);
                registerPanel.add(usernameField);
                registerPanel.add(passwordLabel);
                registerPanel.add(passwordField);
                registerPanel.add(confirmPasswordLabel); // Added confirming password label
                registerPanel.add(confirmPasswordField); // Added confirming password field

                // Show a dialog for user registration
                int result = JOptionPane.showConfirmDialog(null, registerPanel, "Register", JOptionPane.OK_CANCEL_OPTION);

                // Check if the user clicked the OK button
                if (result == JOptionPane.OK_OPTION) {
                    // Retrieve registered username and password
                    String registeredUsername = usernameField.getText();
                    char[] registeredPasswordChars = passwordField.getPassword();
                    char[] confirmedPasswordChars = confirmPasswordField.getPassword();

                    // Convert password characters to secure strings
                    String registeredPassword = String.valueOf(registeredPasswordChars);
                    String confirmedPassword = String.valueOf(confirmedPasswordChars);

                    // Check if the passwords match
                    if (registeredPassword.equals(confirmedPassword)) {
                        // Store registration data in a file
                        try {
                            FileWriter writer = new FileWriter("so_called_database.txt", true);
                            writer.write(registeredUsername + "," + registeredPassword + "\n");
                            writer.close();

                            // Show registration successful message
                            JOptionPane.showMessageDialog(null, "Successfully registered, please Log in!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Display an error message for password mismatch
                        JOptionPane.showMessageDialog(null, "Password doesn't match!");
                    }

                    // Clear the password characters from memory
                    java.util.Arrays.fill(registeredPasswordChars, ' ');
                    java.util.Arrays.fill(confirmedPasswordChars, ' ');
                }
            }
        });
    }

    
    }
