package com.mycompany.View;

import com.mycompany.Controller.UserController;
import com.mycompany.Model.User;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;

public class LoginForm extends javax.swing.JFrame {

    private final UserController userController;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton signUpButton;
    private javax.swing.JButton feedbackButton; // New button for "Go to Feedback"
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel signInLabel;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel signUpLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel logoLabel; // New label for logo
    private int welcomeLabelX = 0; // Initial x position
    private int welcomeLabelDirection = 1; // 1 for right, -1 for left

    public LoginForm() {
        userController = new UserController(); // Initialize UserController
        initComponents();
        setLocationRelativeTo(null); // Center the frame
        startAnimation(); // Start the animation
    }

    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        signUpPanel = new javax.swing.JPanel();
        signInLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        feedbackButton = new javax.swing.JButton(); // Initialize new button
        welcomeLabel = new javax.swing.JLabel();
        signUpLabel = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel(); // Initialize new label for logo

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));

        signInLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 24)); // NOI18N
        signInLabel.setText("Sign In");

        usernameLabel.setText("USERNAME");

        passwordLabel.setText("PASSWORD");

        loginButton.setText("Sign In");
        loginButton.setBackground(new java.awt.Color(255, 102, 102));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = userController.authenticate(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(LoginForm.this, "Login successful!");
                // Open the dashboard and close the login form
                new FeedbackDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        feedbackButton.setText("Go to Feedback"); // Set text for new button
        feedbackButton.setBackground(new java.awt.Color(0, 123, 255));
        feedbackButton.setForeground(new java.awt.Color(255, 255, 255));
        feedbackButton.addActionListener((ActionEvent e) -> {
            new CreateFeedbackForm().setVisible(true);
            dispose();
        });

        // Load the logo image
        try {
            InputStream imageStream = getClass().getResourceAsStream("/images/bg_nasgor_transparent (1).png");
            if (imageStream != null) {
                ImageIcon logoIcon = new ImageIcon(ImageIO.read(imageStream));
                logoLabel.setIcon(logoIcon);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(signInLabel)
                                        .addComponent(usernameLabel)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(feedbackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)) // Add feedback button to layout
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(signInLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usernameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(feedbackButton) // Add feedback button to layout
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        signUpPanel.setBackground(new java.awt.Color(255, 102, 102));

        welcomeLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 24)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setText("Welcome to login");

        signUpLabel.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        signUpLabel.setForeground(new java.awt.Color(255, 255, 255));
        signUpLabel.setText("Don't have an account?");

        signUpButton.setText("Sign Up");
        signUpButton.setBackground(new java.awt.Color(255, 255, 255));
        signUpButton.setForeground(new java.awt.Color(255, 102, 102));
        signUpButton.addActionListener((ActionEvent e) -> {
            new SignUpForm(userController).setVisible(true);
            dispose();
        });

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
                signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(logoLabel) // Add logoLabel to layout
                                        .addComponent(welcomeLabel)
                                        .addComponent(signUpLabel)
                                        .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        signUpPanelLayout.setVerticalGroup(
                signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(logoLabel) // Add logoLabel to layout
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(welcomeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signUpLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signUpButton)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signUpPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(signUpPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void startAnimation() {
        Timer timer = new Timer(10, (ActionEvent e) -> {
            // Update the x position of the welcomeLabel
            welcomeLabelX += welcomeLabelDirection;
            if (welcomeLabelX + welcomeLabel.getWidth() > signUpPanel.getWidth() || welcomeLabelX < 0) {
                welcomeLabelDirection *= -1;
            }
            welcomeLabel.setLocation(welcomeLabelX, welcomeLabel.getY());
        });
        timer.start();
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
