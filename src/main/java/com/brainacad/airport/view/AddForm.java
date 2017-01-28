package com.brainacad.airport.view;

import com.brainacad.airport.controller.AirportController;
import com.brainacad.airport.controller.FlightController;
import com.brainacad.airport.dao.models.Airport;
import com.brainacad.airport.service.MetaSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by User on 20.01.2017.
 */
public class AddForm {
    private boolean isGuest;
    private JTable flight;
    private JTable airport;
    private JTable passenger;
    private String pattern = "airport_iata";
    private Box dataPanel;
    private JFrame addFrame;

    private JTextField iataField;
    private JTextField icaoField;
    private JTextField cityField;


    public AddForm(boolean isGuest, JTable flight, JTable airport, JTable passenger) {
        this.isGuest = isGuest;
        this.flight = flight;
        this.airport = airport;
        this.passenger = passenger;
    }

    public void initialize() {
        addFrame = new JFrame("Delete");

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(0x4D84F8));

        JComponent content = this.createContent();

        addFrame.setContentPane(contentPane);
        contentPane.add(content);

        addFrame.pack();
        addFrame.setLocationRelativeTo(null);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setVisible(true);
    }

    private JComponent createContent() {

        Box box = Box.createVerticalBox();

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

        dataPanel = pattern();

        JLabel inputLabel = new JLabel("Add entry");
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputLabel.setForeground(new Color(0x151515));

        JTextField inputField = new JTextField();
        inputField.setColumns(30);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JButton addButton = new JButton("Add Row");
        addButton.setOpaque(true);
        addButton.setBackground(new Color(0x071A64));
        addButton.setForeground(new Color(0xFFFFFF));
        addButton.setFont(new Font("Georgia", Font.BOLD, 14));
        addButton.setMargin(new Insets(0, 0, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);

        tables.addItemListener(e -> {
            Vector<String> names = new MetaSelector().getColumns(String.valueOf(tables.getSelectedItem()));
            columns.setModel(new DefaultComboBoxModel<>(names));
            pattern = names.firstElement();
            dataPanel = pattern();
            dataPanel.revalidate();
            addFrame.pack();
        });

        addButton.addActionListener(e -> {
            new AirportController(isGuest).addRecord(iataField.getText(), icaoField.getText(), cityField.getText());
        });

        box.add(tablesBoxPanel);
        box.add(columnBoxPanel);
        box.add(dataPanel);
        box.add(buttonPanel);

        return box;
    }

    private Box pattern() {
        Box dataPanel = Box.createVerticalBox();
        switch (pattern) {
            case "airport_iata":
                JLabel iataLabel = new JLabel("IATA");
                iataLabel.setHorizontalAlignment(SwingConstants.CENTER);
                iataLabel.setForeground(new Color(0x151515));

                iataField = new JTextField();
                iataField.setColumns(30);

                JPanel iataPanel = new JPanel();
                iataPanel.setOpaque(false);
                iataPanel.add(iataLabel);
                iataPanel.add(iataField);

                JLabel icaoLabel = new JLabel("ICAO");
                icaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                icaoLabel.setForeground(new Color(0x151515));

                icaoField = new JTextField();
                icaoField.setColumns(30);

                JPanel icaoPanel = new JPanel();
                icaoPanel.setOpaque(false);
                icaoPanel.add(icaoLabel);
                icaoPanel.add(icaoField);

                JLabel cityLabel = new JLabel("City");
                cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cityLabel.setForeground(new Color(0x151515));

                cityField = new JTextField();
                cityField.setColumns(30);

                JPanel cityPanel = new JPanel();
                cityPanel.setOpaque(false);
                cityPanel.add(cityLabel);
                cityPanel.add(cityField);

                dataPanel.add(iataPanel);
                dataPanel.add(icaoPanel);
                dataPanel.add(cityPanel);
                break;
            case "flight_id":

                break;

            case "passenger_id":
                JLabel idLabel = new JLabel("ID");
                idLabel.setHorizontalAlignment(SwingConstants.CENTER);
                idLabel.setForeground(new Color(0x151515));

                JTextField idField = new JTextField();
                idField.setColumns(30);

                JPanel idPanel = new JPanel();
                idPanel.setOpaque(false);
                idPanel.add(idLabel);
                idPanel.add(idField);

                JLabel firstLabel = new JLabel("First Name");
                firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
                firstLabel.setForeground(new Color(0x151515));

                JTextField firstField = new JTextField();
                firstField.setColumns(30);

                JPanel firstPanel = new JPanel();
                firstPanel.setOpaque(false);
                firstPanel.add(firstLabel);
                firstPanel.add(firstField);

                JLabel lastLabel = new JLabel("Last Name");
                lastLabel.setHorizontalAlignment(SwingConstants.CENTER);
                lastLabel.setForeground(new Color(0x151515));

                JTextField lastField = new JTextField();
                lastField.setColumns(30);

                JPanel lastPanel = new JPanel();
                lastPanel.setOpaque(false);
                lastPanel.add(lastLabel);
                lastPanel.add(lastField);

                JLabel birthdayLabel = new JLabel("Date of Birthday");
                birthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
                birthdayLabel.setForeground(new Color(0x151515));

                JTextField birthdayField = new JTextField();
                birthdayField.setColumns(30);

                JPanel birthPanel = new JPanel();
                birthPanel.setOpaque(false);
                birthPanel.add(birthdayLabel);
                birthPanel.add(birthdayField);

                JLabel sexLabel = new JLabel("Sex");
                sexLabel.setHorizontalAlignment(SwingConstants.CENTER);
                sexLabel.setForeground(new Color(0x151515));

                JTextField sexField = new JTextField();
                sexField.setColumns(30);

                JPanel sexPanel = new JPanel();
                sexPanel.setOpaque(false);
                sexPanel.add(sexLabel);
                sexPanel.add(sexField);

                JLabel flightLabel = new JLabel("Flight");
                flightLabel.setHorizontalAlignment(SwingConstants.CENTER);
                flightLabel.setForeground(new Color(0x151515));

                JTextField flightField = new JTextField();
                flightField.setColumns(30);

                JPanel flightPanel = new JPanel();
                flightPanel.setOpaque(false);
                flightPanel.add(flightLabel);
                flightPanel.add(flightField);

                dataPanel.add(idPanel);
                dataPanel.add(firstPanel);
                dataPanel.add(lastPanel);
                dataPanel.add(birthPanel);
                dataPanel.add(sexPanel);
                dataPanel.add(flightPanel);
                break;
        }
        return dataPanel;
    }
}
