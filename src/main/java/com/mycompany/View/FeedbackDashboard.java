package com.mycompany.View;

import com.mycompany.Controller.FeedbackController;
import com.mycompany.Model.Feedback;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FeedbackDashboard extends JFrame {

    private final FeedbackController feedbackController;
    private JTextField searchField;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel totalFeedbackLabel;
    private JLabel averageRatingLabel;
    private JTable feedbackTable;
    private DefaultTableModel tableModel;

    public FeedbackDashboard() {
        feedbackController = new FeedbackController(); // Initialize FeedbackController
        initComponents();
        setLocationRelativeTo(null); // Center the frame
        loadDashboardData(); // Load initial data
    }

    private void initComponents() {
        JLabel searchLabel = new JLabel("Search by Customer:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        searchButton.addActionListener((ActionEvent e) -> {
            searchFeedbacks();
        });

        updateButton.addActionListener((ActionEvent e) -> {
            updateFeedback();
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            deleteFeedback();
        });

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(updateButton);
        searchPanel.add(deleteButton);

        totalFeedbackLabel = new JLabel("Total Feedback: 0");
        averageRatingLabel = new JLabel("Average Rating: 0");

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 2));
        statsPanel.add(totalFeedbackLabel);
        statsPanel.add(averageRatingLabel);

        feedbackTable = new JTable();
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Customer Review", "Feedback Date", "Feedback Text", "Rating"}, 0);
        feedbackTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(feedbackTable);

        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        setTitle("Feedback Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void loadDashboardData() {
        List<Feedback> feedbackList = feedbackController.getAllFeedbacks();
        updateTable(feedbackList);
        updateStats(feedbackList);
    }

    private void searchFeedbacks() {
        String customerName = searchField.getText();
        List<Feedback> feedbackList = feedbackController.searchFeedbacksByCustomerName(customerName);
        updateTable(feedbackList);
        updateStats(feedbackList);
    }

    private void updateFeedback() {
        int selectedRow = feedbackTable.getSelectedRow();
        if (selectedRow >= 0) {
            Long feedbackId = (Long) tableModel.getValueAt(selectedRow, 0);
            Feedback feedback = feedbackController.getFeedbackById(feedbackId);
            
            String newFeedbackText = JOptionPane.showInputDialog(this, "Enter new feedback text:", feedback.getFeedbackText());
            if (newFeedbackText != null && !newFeedbackText.trim().isEmpty()) {
                feedback.setFeedbackText(newFeedbackText);
                feedbackController.updateFeedback(feedback);
                loadDashboardData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a feedback to update.");
        }
    }

    private void deleteFeedback() {
        int selectedRow = feedbackTable.getSelectedRow();
        if (selectedRow >= 0) {
            Long feedbackId = (Long) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this feedback?", "Delete Feedback", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                feedbackController.deleteFeedback(feedbackId);
                loadDashboardData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a feedback to delete.");
        }
    }

    private void updateTable(List<Feedback> feedbackList) {
        tableModel.setRowCount(0); // Clear existing rows

        for (Feedback feedback : feedbackList) {
            tableModel.addRow(new Object[]{
                    feedback.getId(),
                    feedback.getCustomerName(),
                    feedback.getFeedbackDate(),
                    feedback.getFeedbackText(),
                    feedback.getRating()
            });
        }
    }

    private void updateStats(List<Feedback> feedbackList) {
        int totalFeedback = feedbackList.size();
        double averageRating = feedbackList.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0);

        totalFeedbackLabel.setText("Total Feedback: " + totalFeedback);
        averageRatingLabel.setText("Average Rating: " + String.format("%.2f", averageRating));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FeedbackDashboard().setVisible(true);
        });
    }
}
