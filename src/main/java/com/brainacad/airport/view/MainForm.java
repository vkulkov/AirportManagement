package com.brainacad.airport.view;

import com.brainacad.airport.controller.AirportController;
import com.brainacad.airport.controller.FlightController;
import com.brainacad.airport.controller.PassengerController;
import com.brainacad.airport.view.listeners.main.TableSelector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 12.12.2016.
 */
public class MainForm {
    private boolean isGuest;
    private boolean isEditor;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTable flightTable;
    private JTable airportTable;
    private JTable passengerTable;

    public boolean isGuest() {
        return isGuest;
    }

    public boolean isEditor() {
        return isEditor;
    }

    public static void main(String[] args) {
        new MainForm(false).initialize();
    }

    public MainForm(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public void initialize() {
        //Create and set up the window.
        mainFrame = new JFrame("Airport Terminal");

        //Create the menu bar.  Make it have a blue background.
        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setOpaque(true);
        mainMenuBar.setBackground(new Color(7, 26, 100));
        mainMenuBar.setPreferredSize(new Dimension(700, 20));

        //Create content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        Border someBorder = new CompoundBorder();
        contentPane.setBorder(someBorder);

        //Set the menu bar, content pane and add the panes to the content pane.
        mainFrame.setJMenuBar(mainMenuBar);
        mainFrame.setContentPane(contentPane);
        contentPane.add(initMainPanel(), BorderLayout.CENTER);
        contentPane.add(initSidePanel(), BorderLayout.WEST);


        //Display the window.
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private JPanel initSidePanel() {
        Icon searchIcon = new ImageIcon(ClassLoader.getSystemResource("images/search.gif"));
        Icon addIcon = new ImageIcon(ClassLoader.getSystemResource("images/add.gif"));
        Icon deleteIcon = new ImageIcon(ClassLoader.getSystemResource("images/delete.gif"));

        //Create a side pane to put in the content pane.
        JPanel sidePane = new JPanel(new FlowLayout());
        sidePane.setOpaque(true);
        sidePane.setBackground(new Color(77, 132, 248));
        sidePane.setPreferredSize(new Dimension(50, 300));

        JButton searchButton = new JButton();
        searchButton.setOpaque(true);
        //chooseTableButton.setBackground(new Color(0xF82D20));
        searchButton.setPreferredSize(new Dimension(30, 30));
        searchButton.setIcon(searchIcon);
        //chooseTableButton.setMargin(new Insets(10, 10, 10, 10));
        searchButton.setToolTipText("Search");

        //Create a button to put in the side pane
        JButton addRowButton = new JButton();
        addRowButton.setOpaque(true);
        //chooseTableButton.setBackground(new Color(0xF82D20));
        addRowButton.setPreferredSize(new Dimension(30, 30));
        addRowButton.setIcon(addIcon);
        //chooseTableButton.setMargin(new Insets(10, 10, 10, 10));
        addRowButton.setToolTipText("Add Row");

        //Create a button to put in the side pane
        JButton deleteRowButton = new JButton();
        deleteRowButton.setOpaque(true);
        //chooseTableButton.setBackground(new Color(0xF82D20));
        deleteRowButton.setPreferredSize(new Dimension(30, 30));
        deleteRowButton.setIcon(deleteIcon);
        //chooseTableButton.setMargin(new Insets(10, 10, 10, 10));
        deleteRowButton.setToolTipText("Delete Row");

        //Listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchForm(isGuest).initialize();
            }
        });

        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddForm(isGuest, flightTable, airportTable, passengerTable).initialize();
            }
        });

        deleteRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteForm(isGuest, flightTable, airportTable, passengerTable).initialize();
            }
        });


        sidePane.add(searchButton, BorderLayout.CENTER);
        if (!isGuest) {
            sidePane.add(addRowButton, BorderLayout.CENTER);
            sidePane.add(deleteRowButton, BorderLayout.CENTER);
        }

        return sidePane;
    }

    private JPanel initMainPanel() {

        flightTable = new JTable(new FlightController(isGuest).createTableModel());
        TableRowSorter<TableModel> flightSorter = new TableRowSorter<TableModel>(flightTable.getModel());
        flightTable.setRowSorter(flightSorter);

        airportTable = new JTable(new AirportController(isGuest).createTableModel());
        TableRowSorter<TableModel> airportSorter = new TableRowSorter<TableModel>(airportTable.getModel());
        airportTable.setRowSorter(airportSorter);

        passengerTable = new JTable(new PassengerController(isGuest).createTableModel());
        TableRowSorter<TableModel> passengerSorter = new TableRowSorter<TableModel>(passengerTable.getModel());
        passengerTable.setRowSorter(passengerSorter);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Flights", new JScrollPane(flightTable));
        if (!isGuest) {
            tabbedPane.addTab("Airports", new JScrollPane(airportTable));
            tabbedPane.addTab("Passengers", new JScrollPane(passengerTable));
        }
        //Create a back pane to put in the content pane
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(0xE1E1E1));
        mainPanel.setPreferredSize(new Dimension(350, 300));

        mainPanel.add(tabbedPane);

        return mainPanel;
    }
}
