package com.perisic.tomato.peripherals;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = -6921462126880570161L;

    public static void main(String[] args) {
        new LoginGUI(); // Create an instance of LoginGUI without assigning it to a variable
    }

    JButton blogin = new JButton("Login");
    JButton bregister = new JButton("Register");  
    JPanel panel = new JPanel();
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);
    JLabel userLabel = new JLabel("Username:");
    JLabel passLabel = new JLabel("Password:");

    LoginData ldata = new LoginData();

    LoginGUI() {
        super("Login Authentication");
        setSize(300, 200);
        setLocation(500, 280);
        panel.setLayout(null);

        userLabel.setBounds(10, 30, 80, 20);
        passLabel.setBounds(10, 65, 80, 20);
        txuser.setBounds(90, 30, 150, 20);
        pass.setBounds(90, 65, 150, 20);
        blogin.setBounds(20, 100, 80, 20);
        bregister.setBounds(170, 100, 100, 20);  

        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(blogin);
        panel.add(bregister);  
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
        actionregister();  
    }

    public void actionlogin() {
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = String.valueOf(pass.getPassword());
                if (ldata.checkPassword(puname, ppaswd)) {
                    GameGUI theGame = new GameGUI(puname);
                    theGame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    txuser.setText("");
                    pass.setText("");
                    txuser.requestFocus();
                }
            }
        });
    }

    // New method for Register button action - 
    public void actionregister() {
        bregister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Implement registration logic here
                
                // Create the panel for name and surname
                JPanel registerPanel = new JPanel();
                JLabel nameLabel = new JLabel("Name:");
                JLabel surnameLabel = new JLabel("Surname:");
                JTextField nameField = new JTextField(15);
                JTextField surnameField = new JTextField(15);
                
                // Set the layout for the register panel
                registerPanel.setLayout(new GridLayout(2, 2));
                
                // Add the labels and input fields to the register panel
                registerPanel.add(nameLabel);
                registerPanel.add(nameField);
                registerPanel.add(surnameLabel);
                registerPanel.add(surnameField);
                
                // Show a dialog with the register panel
                int result = JOptionPane.showConfirmDialog(null, registerPanel, "Register", JOptionPane.OK_CANCEL_OPTION);
                
                // Check if the user clicked the OK button
                if (result == JOptionPane.OK_OPTION) {
                    String registeredName = nameField.getText();
                    String registeredUsurname = surnameField.getText();
                    
                    // Perform registration logic with the name and surname values
                    // ...
                }
            }
        });
    }
}
