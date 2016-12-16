package com.brainacad.airport.gui.controller.authorization;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by User on 16.12.2016.
 */
public class GuestBoxListener implements ItemListener {
    private JTextField nameTextField;
    private JPasswordField passwordField;

    public GuestBoxListener(JTextField nameTextField, JPasswordField passwordField) {
        this.nameTextField = nameTextField;
        this.passwordField = passwordField;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            nameTextField.setEditable(false);
            passwordField.setEditable(false);
        } else {
            nameTextField.setEditable(true);
            passwordField.setEditable(true);
        }
    }
}
