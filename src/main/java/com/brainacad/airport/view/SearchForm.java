package com.brainacad.airport.view;

import com.brainacad.airport.controller.AirportController;
import com.brainacad.airport.controller.FlightController;
import com.brainacad.airport.controller.PassengerController;
import com.brainacad.airport.service.MetaSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by User on 18.01.2017.
 */
public class SearchForm {

    private boolean isGuest;
    private JFrame searchFrame;

    public SearchForm(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public void initialize() {
        searchFrame = new JFrame("Search...");

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(0x4D84F8));

        JComponent content = this.createContent();

        searchFrame.setContentPane(contentPane);
        contentPane.add(content);

        searchFrame.pack();
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setVisible(true);
    }

    private JComponent createContent() {

        Box box = Box.createVerticalBox();

        JLabel inputLabel = new JLabel("Enter your query");
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputLabel.setForeground(new Color(0x151515));

        JTextField inputField = new JTextField();
        inputField.setColumns(30);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JLabel tableNameLabel = new JLabel("Search from:");
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tableNameLabel.setForeground(new Color(0x151515));

        JComboBox<String> tables = new JComboBox<String>(new MetaSelector().getTables());

        JPanel tablesBoxPanel = new JPanel();
        tablesBoxPanel.setOpaque(false);
        tablesBoxPanel.add(tableNameLabel);
        tablesBoxPanel.add(tables);

        JLabel columnNameLabel = new JLabel("Selected column");
        columnNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        columnNameLabel.setForeground(new Color(0x151515));

        String tableName = "flight";
        if (!isGuest) {
            tableName = String.valueOf(tables.getSelectedItem());
        }
        JComboBox<String> columns = new JComboBox<String>(new MetaSelector().getColumns(tableName));

        JPanel columnBoxPanel = new JPanel();
        columnBoxPanel.setOpaque(false);
        columnBoxPanel.add(columnNameLabel);
        columnBoxPanel.add(columns);

        JPanel resultPanel = new JPanel();
        resultPanel.setOpaque(false);

        JButton searchButton = new JButton("Search");
        searchButton.setOpaque(true);
        searchButton.setBackground(new Color(0x071A64));
        searchButton.setForeground(new Color(0xFFFFFF));
        searchButton.setFont(new Font("Georgia", Font.BOLD, 14));
        searchButton.setMargin(new Insets(0, 0, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(searchButton);

        tables.addItemListener(e ->
                columns.setModel(new DefaultComboBoxModel<>(new MetaSelector().getColumns(String.valueOf(tables.getSelectedItem())))));

        searchButton.addActionListener(e -> {
            String item = "flight";
            if (!isGuest) {
                item = String.valueOf(tables.getSelectedItem());
            }
            String column = String.valueOf(columns.getSelectedItem());
            JTable resultTable = new JTable();
            switch (item) {
                case "flight":
                    resultTable = new JTable(new FlightController(isGuest).searchInDB(column, inputField.getText()));
                    break;
                case "airport":
                    resultTable = new JTable(new AirportController(isGuest).searchInDB(column, inputField.getText()));
                    break;
                case "passenger":
                    resultTable = new JTable(new PassengerController(isGuest).searchInDB(column, inputField.getText()));
                    break;
            }
            resultPanel.removeAll();
            if (resultTable.getRowCount() > 0) {
                resultPanel.add(new JScrollPane(resultTable));
            } else {
                resultPanel.add(new JLabel("Sorry, but nothing was found"));
            }
            box.revalidate();
            box.repaint();
            searchFrame.pack();
            searchFrame.setLocationRelativeTo(null);
        });

        box.add(inputPanel);
        if (!isGuest) {
            box.add(tablesBoxPanel);
        }
        box.add(columnBoxPanel);
        box.add(resultPanel);
        box.add(buttonPanel);

        return box;
    }
}
