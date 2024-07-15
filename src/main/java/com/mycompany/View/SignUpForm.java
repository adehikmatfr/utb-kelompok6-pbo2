package com.mycompany.View;

import com.mycompany.Controller.UserController;
import com.mycompany.Model.User;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignUpForm extends javax.swing.JFrame {

    private final UserController userController;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JLabel signUpLabel;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField usernameField;

    public SignUpForm(UserController userController) {
        this.userController = userController; // Initialize UserController
        initComponents();
        setLocationRelativeTo(null); // Center the frame
    }

    private void initComponents() {

        signUpPanel = new javax.swing.JPanel();
        signUpLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        registerButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        signUpPanel.setBackground(new java.awt.Color(255, 255, 255));

        signUpLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 24)); // NOI18N
        signUpLabel.setText("Sign Up");

        usernameLabel.setText("USERNAME");

        passwordLabel.setText("PASSWORD");

        confirmPasswordLabel.setText("CONFIRM PASSWORD");

        registerButton.setText("Register");
        registerButton.setBackground(new java.awt.Color(255, 102, 102));
        registerButton.setForeground(new java.awt.Color(255, 255, 255));
        registerButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            // Validasi input
            if (username.length() < 3 || username.length() > 20) {
                JOptionPane.showMessageDialog(SignUpForm.this, "Username must be between 3 and 20 characters", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(SignUpForm.this, "Password must be at least 6 characters", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(SignUpForm.this, "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Periksa apakah username sudah ada
            if (userController.getUserByUsername(username) != null) {
                JOptionPane.showMessageDialog(SignUpForm.this, "Username already exists", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            
            userController.saveUser(user);
            JOptionPane.showMessageDialog(SignUpForm.this, "Registration successful!");
            new LoginForm().setVisible(true);
            dispose();
        });

        backButton.setText("Back");
        backButton.setForeground(new java.awt.Color(255, 102, 102));
        backButton.setBorder(null);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener((ActionEvent e) -> {
            new LoginForm().setVisible(true);
            dispose();
        });

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signUpLabel)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(signUpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signUpPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new SignUpForm(new UserController()).setVisible(true);
        });
    }
}
