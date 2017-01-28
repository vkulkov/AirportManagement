package com.brainacad.airport.view.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 16.12.2016.
 */
public class FrameDisposer implements ActionListener {
    private JFrame childFrame;

    public FrameDisposer(JFrame childFrame) {
        this.childFrame = childFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        childFrame.dispose();
    }
}
