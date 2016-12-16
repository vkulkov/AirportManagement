package com.brainacad.airport.gui.controller.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 16.12.2016.
 */
public class FrameDisposal implements ActionListener {
    private JFrame childFrame;

    public FrameDisposal(JFrame childFrame) {
        this.childFrame = childFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        childFrame.dispose();
    }
}
