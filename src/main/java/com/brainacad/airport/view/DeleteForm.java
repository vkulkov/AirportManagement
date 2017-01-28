package com.brainacad.airport.view;

import com.brainacad.airport.controller.AirportController;
import com.brainacad.airport.controller.FlightController;
import com.brainacad.airport.controller.PassengerController;
import com.brainacad.airport.service.MetaSelector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 20.01.2017.
 */
public class DeleteForm {
    private boolean isGuest;
    private JTable flight;
    private JTable airport;
    private JTable passenger;
    private JFrame deleteFrame;

    public DeleteForm(boolean isGuest, JTable flight, JTable airport, JTable passenger) {
        this.isGuest = isGuest;
        this.flight = flight;
        this.airport = airport;
        this.passenger = passenger;
    }

    public void initialize() {
        deleteFrame = new JFrame("Delete");

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(0x4D84F8));

        JComponent content = this.createContent();

        deleteFrame.setContentPane(contentPane);
        contentPane.add(content);

        deleteFrame.pack();
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setVisible(true);
    }

    private JComponent createContent() {

        Box box = Box.createVerticalBox();

        JLabel inputLabel = new JLabel("Delete entry");
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputLabel.setForeground(new Color(0x151515));

        JTextField inputField = new JTextField();
        inputField.setColumns(30);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JLabel tableNameLabel = new JLabel("Selected Table");
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

        String tableName = String.valueOf(tables.getSelectedItem());
        JComboBox<String> columns = new JComboBox<String>(new MetaSelector().getColumns(tableName));

        JPanel columnBoxPanel = new JPanel();
        columnBoxPanel.setOpaque(false);
        columnBoxPanel.add(columnNameLabel);
        columnBoxPanel.add(columns);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setOpaque(true);
        deleteButton.setBackground(new Color(0x071A64));
        deleteButton.setForeground(new Color(0xFFFFFF));
        deleteButton.setFont(new Font("Georgia", Font.BOLD, 14));
        deleteButton.setMargin(new Insets(0, 0, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(deleteButton);

        tables.addItemListener(e ->
                columns.setModel(new DefaultComboBoxModel<>(new MetaSelector().getColumns(String.valueOf(tables.getSelectedItem())))));

        deleteButton.addActionListener(e -> {
            String item = String.valueOf(tables.getSelectedItem());
            String column = String.valueOf(columns.getSelectedItem());
            JTable resultTable = new JTable();
            switch (item) {
                case "flight":
                    resultTable = flight;
                    new FlightController(isGuest).deleteRecord(column, inputField.getText());
                    break;
                case "airport":
                    resultTable = airport;
                    new AirportController(isGuest).deleteRecord(column, inputField.getText());
                    break;
                case "passenger":
                    resultTable = passenger;
                    new PassengerController(isGuest).deleteRecord(column, inputField.getText());
                    break;
            }
            DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
            int[] rows = resultTable.getSelectedRows();
            for(int i=0;i<rows.length;i++){
                model.removeRow(rows[i]-i);
            }
        });

        box.add(inputPanel);
        box.add(tablesBoxPanel);
        box.add(columnBoxPanel);
        box.add(buttonPanel);

        return box;
    }
}
