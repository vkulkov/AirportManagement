package com.brainacad.airport.gui.view;

import com.brainacad.airport.gui.controller.main.TableSelector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * Created by User on 12.12.2016.
 */
public class MainForm {
    private boolean isGuest;
    private JFrame mainFrame;
    private JTable table;



    public static void main(String[] args) {
        new MainForm(false).initialize();
    }

    public MainForm(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public void initialize() {
        //Create and set up the window.
        mainFrame = new JFrame("Airport Terminal");

        //Create the menu bar.  Make it have a green background.
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
        contentPane.add(initSidePanel(), BorderLayout.WEST);
        contentPane.add(initMainPanel(), BorderLayout.CENTER);

        //Display the window.
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private JPanel initSidePanel() {
        Icon chooseTableIcon = new ImageIcon("./images/get_table.gif");

        //Create a side pane to put in the content pane.
        JPanel sidePane = new JPanel(new FlowLayout());
        sidePane.setOpaque(true);
        sidePane.setBackground(new Color(77, 132, 248));
        sidePane.setPreferredSize(new Dimension(50, 300));

        //Create a button to put in the side pane
        JButton chooseTableButton = new JButton();
        chooseTableButton.setOpaque(true);
        //chooseTableButton.setBackground(new Color(0xF82D20));
        chooseTableButton.setPreferredSize(new Dimension(30, 30));
        chooseTableButton.setIcon(chooseTableIcon);
        //chooseTableButton.setMargin(new Insets(10, 10, 10, 10));
        chooseTableButton.setToolTipText("Select Table");

        chooseTableButton.addActionListener(new TableSelector(table));

        sidePane.add(chooseTableButton, BorderLayout.CENTER);

        return sidePane;
    }

    private JPanel initMainPanel() {
        table = new JTable();
        //Create a back pane to put in the content pane
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(0xE1E1E1));
        mainPanel.setPreferredSize(new Dimension(350, 300));

        mainPanel.add(table);

        return mainPanel;
    }
}
