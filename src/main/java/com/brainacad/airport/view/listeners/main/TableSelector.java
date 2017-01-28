package com.brainacad.airport.view.listeners.main;

import com.brainacad.airport.view.TableSelectorForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 16.12.2016.
 */
public class TableSelector implements ActionListener{
    private JTable table;
    private JPanel mainPanel;

    public TableSelector(JTable table, JPanel mainPanel) {
        this.table = table;
        this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new TableSelectorForm(table, mainPanel).initialize();
    }
}
