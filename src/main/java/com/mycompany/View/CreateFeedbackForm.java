package com.mycompany.View;

import com.mycompany.Controller.FeedbackController;
import com.mycompany.Model.Feedback;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

public class CreateFeedbackForm extends JFrame {

    private final FeedbackController feedbackController;
    private JLabel questionLabel;
    private JRadioButton sadButton;
    private JRadioButton neutralButton;
    private JRadioButton happyButton;
    private JRadioButton loveButton; // New love emoji button
    private JTextArea feedbackTextArea;
    private JButton submitButton;
    private ButtonGroup emojiGroup;
    private final String placeholderText = "Write your message here";

    public CreateFeedbackForm() {
        feedbackController = new FeedbackController();
        initComponents();
        setLocationRelativeTo(null); // Center the frame
    }

    private void initComponents() {
        // Initialize components
        questionLabel = new JLabel("How likely are you to answer to this question?");
        sadButton = new JRadioButton(createScaledIcon("/images/sad_emoji.png", 50, 50));
        neutralButton = new JRadioButton(createScaledIcon("/images/neutral_emoji.png", 50, 50));
        happyButton = new JRadioButton(createScaledIcon("/images/happy_emoji.png", 50, 50));
        loveButton = new JRadioButton(createScaledIcon("/images/love_emoji.png", 50, 50)); // New love emoji button
        feedbackTextArea = new JTextArea(placeholderText, 5, 20);
        feedbackTextArea.setForeground(Color.GRAY);
        feedbackTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (feedbackTextArea.getText().equals(placeholderText)) {
                    feedbackTextArea.setText("");
                    feedbackTextArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (feedbackTextArea.getText().isEmpty()) {
                    feedbackTextArea.setForeground(Color.GRAY);
                    feedbackTextArea.setText(placeholderText);
                }
            }
        });
        submitButton = new JButton("Submit");
        emojiGroup = new ButtonGroup();

        // Group emoji buttons
        emojiGroup.add(sadButton);
        emojiGroup.add(neutralButton);
        emojiGroup.add(happyButton);
        emojiGroup.add(loveButton); // Add love button to group

        // Set layout and add components
        setLayout(new BorderLayout(10, 10));

        JPanel emojiPanel = new JPanel(new FlowLayout());
        emojiPanel.add(sadButton);
        emojiPanel.add(neutralButton);
        emojiPanel.add(happyButton);
        emojiPanel.add(loveButton); // Add love button to panel

        JPanel feedbackPanel = new JPanel(new BorderLayout(5, 5));
        feedbackPanel.add(questionLabel, BorderLayout.NORTH);
        feedbackPanel.add(emojiPanel, BorderLayout.CENTER);
        feedbackPanel.add(new JScrollPane(feedbackTextArea), BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);

        add(feedbackPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configure buttons
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
            }
        });

        sadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio("/audio/sad_sound.mp3");
            }
        });

        neutralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio("/audio/neutral_sound.mp3");
            }
        });

        happyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio("/audio/happy_sound.mp3");
            }
        });

        loveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio("/audio/love_sound.mp3");
            }
        });

        setTitle("Create Feedback");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    private void submitFeedback() {
        String customerName;
        if (sadButton.isSelected()) {
            customerName = "Buruk";
        } else if (neutralButton.isSelected()) {
            customerName = "Tidak Suka";
        } else if (happyButton.isSelected()) {
            customerName = "Suka";
        } else if (loveButton.isSelected()) { // Check if love button is selected
            customerName = "Sangat Suka";
        } else {
            JOptionPane.showMessageDialog(this, "Please select an emoji.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String feedbackText = feedbackTextArea.getText();
        if (feedbackText.equals(placeholderText)) {
            feedbackText = ""; // Reset placeholder text to empty
        }
        Feedback feedback = new Feedback();
        feedback.setCustomerName(customerName);
        feedback.setFeedbackText(feedbackText);
        feedback.setFeedbackDate(LocalDateTime.now());
        feedback.setRating(loveButton.isSelected() ? 6 : happyButton.isSelected() ? 5 : neutralButton.isSelected() ? 3 : 1); // Rating for love button

        feedbackController.saveFeedback(feedback);
        JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear the form for new feedback
        emojiGroup.clearSelection();
        feedbackTextArea.setForeground(Color.GRAY);
        feedbackTextArea.setText(placeholderText);
    }

    private ImageIcon createScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private void playAudio(String filePath) {
        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is)) {
            Player player = new Player(bis);
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CreateFeedbackForm().setVisible(true);
        });
    }
}
