package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    private JLabel title;
    private JLabel attendanceSection;
    private JLabel usersSection;
    private JButton markAttendanceBtn, viewAttendanceBtn, addUserBtn, manageUsersBtn;

    public HomePage() {
        // Set frame properties
        setTitle("Attendance Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400); // Initial size
        setLocationRelativeTo(null); // Center the window

        // Main panel using GridBagLayout for flexibility
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK); // Dark background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        title = new JLabel("Attendance Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.CYAN); // Neon color for title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10); // Padding
        mainPanel.add(title, gbc);

        // Attendance Section
        attendanceSection = new JLabel("Attendance Section", SwingConstants.CENTER);
        attendanceSection.setFont(new Font("Arial", Font.BOLD, 18));
        attendanceSection.setForeground(Color.MAGENTA); // Neon color for section labels
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(attendanceSection, gbc);

        // Attendance Section Buttons
        markAttendanceBtn = new JButton("Mark Attendance");
        viewAttendanceBtn = new JButton("View Attendance Records");
        styleButton(markAttendanceBtn, new Color(57, 255, 20)); // Neon green
        styleButton(viewAttendanceBtn, new Color(255, 89, 255)); // Neon pink

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between buttons
        mainPanel.add(markAttendanceBtn, gbc);

        gbc.gridx = 1;
        mainPanel.add(viewAttendanceBtn, gbc);

        // Users Section
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        usersSection = new JLabel("User Management Section", SwingConstants.CENTER);
        usersSection.setFont(new Font("Arial", Font.BOLD, 18));
        usersSection.setForeground(Color.ORANGE); // Neon orange
        mainPanel.add(usersSection, gbc);

        // Users Section Buttons
        addUserBtn = new JButton("Add New User");
        manageUsersBtn = new JButton("Manage Users");
        styleButton(addUserBtn, new Color(0, 255, 255)); // Neon cyan
        styleButton(manageUsersBtn, new Color(255, 255, 0)); // Neon yellow

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        mainPanel.add(addUserBtn, gbc);

        gbc.gridx = 1;
        mainPanel.add(manageUsersBtn, gbc);

        // Add main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners to buttons
        markAttendanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarkAttendanceFrame(); // Opens Mark Attendance Form
            }
        });

        viewAttendanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAttendanceFrame(); // Opens View Attendance Records Form
            }
        });

        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserFrame(); // Opens Add User Form
            }
        });

        manageUsersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageUsersFrame(); // Opens Manage Users Form
            }
        });

        setVisible(true);
    }

    // Method to style the buttons (color, size, font)
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.BLACK); // Black text on neon background
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
    }
}
