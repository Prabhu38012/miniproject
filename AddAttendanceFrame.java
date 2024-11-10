package views;

import dbconnect.dbconnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAttendanceFrame extends JFrame {
    private JTextField studentIdField, attendanceDateField, statusField;
    private JButton submitButton, cancelButton;

    public AddAttendanceFrame() {
        setTitle("Add Attendance Record");
        setSize(600, 400); // Adjust as needed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        
        // Set layout for the frame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Student ID Label and Field
        JLabel studentIdLabel = new JLabel("Student ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(studentIdLabel, gbc);

        studentIdField = new JTextField(15); // Smaller input field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(studentIdField, gbc);

        // Attendance Date Label and Field
        JLabel attendanceDateLabel = new JLabel("Attendance Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(attendanceDateLabel, gbc);

        attendanceDateField = new JTextField(15);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(attendanceDateField, gbc);

        // Status Label and Field
        JLabel statusLabel = new JLabel("Status (Present/Absent):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(statusLabel, gbc);

        statusField = new JTextField(15);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(statusField, gbc);

        // Submit Button
        submitButton = new JButton("Add Attendance");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(submitButton, gbc);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(cancelButton, gbc);

        // Action listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttendanceToDatabase();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });

        setVisible(true);
    }

    // Method to add attendance data to the database
    private void addAttendanceToDatabase() {
        String studentId = studentIdField.getText();
        String attendanceDate = attendanceDateField.getText();
        String status = statusField.getText();

        if (studentId.isEmpty() || attendanceDate.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        Connection conn = dbconnect.connect();
        if (conn != null) {
            String sql = "INSERT INTO Attendance (student_id, attendance_date, status) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, studentId);
                pstmt.setString(2, attendanceDate);
                pstmt.setString(3, status);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Attendance record added successfully!");
                    // Clear the form fields after successful insertion
                    studentIdField.setText("");
                    attendanceDateField.setText("");
                    statusField.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding attendance record: " + ex.getMessage());
            }
        }
    }
}
