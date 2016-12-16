package com.brainacad.airport.gui.controller.main;

import com.brainacad.airport.gui.view.TableSelectorForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 16.12.2016.
 */
public class TableSelector implements ActionListener{
    private JTable table;

    public TableSelector(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new TableSelectorForm(table).initialize();
    }
}
