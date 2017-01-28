package com.brainacad.airport.view;

import com.brainacad.airport.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by User on 13.12.2016.
 */
public class AuthorizationForm {
    JFrame authorizationFrame;

    public void initialize() {
        authorizationFrame = new JFrame("Airport Terminal");
        //authorizationFrame.setPreferredSize(new Dimension(300, 200));

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(0x4D84F8));

        authorizationFrame.setContentPane(contentPane);
        contentPane.add(this.createContent());

        authorizationFrame.pack();
        authorizationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authorizationFrame.setLocationRelativeTo(null);
        authorizationFrame.setVisible(true);
    }

    private JComponent createContent() {
        Dimension labelSize = new Dimension(80, 20);
        Color labelColor = new Color(0x151515);

        Box box = Box.createVerticalBox();
        //Name
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setPreferredSize(labelSize);
        nameLabel.setForeground(labelColor);

        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(16);

        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setPreferredSize(labelSize);
        passwordLabel.setForeground(labelColor);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(16);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        //Guest checkbox
        JCheckBox isGuest = new JCheckBox("Enter as guest");
        isGuest.setOpaque(false);
        isGuest.setForeground(labelColor);

        JPanel guestPanel = new JPanel();
        guestPanel.setOpaque(false);
        guestPanel.add(isGuest);
        //Log in button
        JButton confirmationButton = new JButton("Log In");
        confirmationButton.setOpaque(true);
        confirmationButton.setBackground(new Color(0x071A64));
        confirmationButton.setForeground(new Color(0xFFFFFF));
        confirmationButton.setFont(new Font("Georgia", Font.BOLD, 14));
        confirmationButton.setMargin(new Insets(0, 0, 0, 0));

        //Checkbox listener
        isGuest.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                nameTextField.setEditable(false);
                passwordField.setEditable(false);
            } else {
                nameTextField.setEditable(true);
                passwordField.setEditable(true);
            }
        });
        //Login button listener
        confirmationButton.addActionListener(e -> {
            if (isGuest.isSelected()) {
                authorizationFrame.dispose();
                new MainForm(true).initialize();
            } else {
                if (new UserController().confirmData(nameTextField.getText(), passwordField.getPassword())) {
                    authorizationFrame.dispose();
                    new MainForm(false).initialize();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "User name or password are incorrect",
                            "Authorization error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel confirmPanel = new JPanel();
        confirmPanel.setOpaque(false);
        confirmPanel.add(confirmationButton);
        //Put everything inside the box
        box.add(namePanel);
        box.add(passwordPanel);
        box.add(guestPanel);
        box.add(confirmPanel);

        return box;
    }
}
