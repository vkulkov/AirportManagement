package com.brainacad.airport.gui.controller.authorization;

import com.brainacad.airport.dao.models.User;
import com.brainacad.airport.gui.view.MainForm;
import com.brainacad.airport.service.business.Acceptor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by User on 16.12.2016.
 */
public class LoginController implements ActionListener {
    private JTextField textField;
    private JPasswordField passwordField;
    private JCheckBox guestCheckBox;
    private JFrame authorizationFrame;

    public LoginController(JTextField textField, JPasswordField passwordField, JCheckBox guestCheckBox, JFrame authorizationFrame) {
        this.textField = textField;
        this.passwordField = passwordField;
        this.guestCheckBox = guestCheckBox;
        this.authorizationFrame = authorizationFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (guestCheckBox.isSelected()) {
            authorizationFrame.dispose();
            new MainForm(true).initialize();
        } else {
            User user = new Acceptor().getData(textField.getText());
            if (user != null) {
                if (Arrays.equals(user.getPassword().toCharArray(), passwordField.getPassword())) {
                    authorizationFrame.dispose();
                    new MainForm(false).initialize();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "User name or password are incorrect",
                        "Authorization error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
