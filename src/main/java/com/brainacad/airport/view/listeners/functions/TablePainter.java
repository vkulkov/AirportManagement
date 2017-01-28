package com.brainacad.airport.view.listeners.functions;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.dbaccess.AirportDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 16.12.2016.
 */
public class TablePainter implements ActionListener {
    private JTable table;
    private JPanel mainPanel;

    public TablePainter(JTable table, JPanel mainPanel) {
        this.table = table;
        this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        AirportDAO air = dao.getAirportDAO(dao.getConnection());
        String[] headers = null;

        table = new JTable(10, 4);
        mainPanel = new JPanel();
        mainPanel.add(table);
    }
}
