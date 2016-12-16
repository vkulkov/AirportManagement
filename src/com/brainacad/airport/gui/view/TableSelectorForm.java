package com.brainacad.airport.gui.view;

import com.brainacad.airport.gui.controller.main.FrameDisposal;
import com.brainacad.airport.gui.controller.main.functions.TablePainter;
import com.brainacad.airport.service.business.MetaSelector;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 16.12.2016.
 */
public class TableSelectorForm {
    private JTable table;
    private JFrame selectorFrame;

    public TableSelectorForm(JTable table) {
        this.table = table;
    }

    public void initialize() {
        selectorFrame = new JFrame("Table Selector");

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(0x4D84F8));

        JComponent content = this.createContent();

        selectorFrame.setContentPane(contentPane);
        contentPane.add(content);

        selectorFrame.pack();
        selectorFrame.setLocationRelativeTo(null);
        selectorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectorFrame.setVisible(true);
    }

    private JComponent createContent() {

        Box box = Box.createVerticalBox();

        JLabel nameLabel = new JLabel("Select table from the list");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setPreferredSize(new Dimension(180, 20));
        nameLabel.setForeground(new Color(0x151515));

        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.add(nameLabel);

        JComboBox<String> tables = new JComboBox<String>(new MetaSelector().getTables());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setOpaque(false);
        comboBoxPanel.add(tables);

        JButton confirmButton = new JButton("OK");
        confirmButton.setOpaque(true);
        confirmButton.setBackground(new Color(0x071A64));
        confirmButton.setForeground(new Color(0xFFFFFF));
        confirmButton.setFont(new Font("Georgia", Font.BOLD, 14));
        confirmButton.setMargin(new Insets(0, 0, 0, 0));
        confirmButton.addActionListener(new TablePainter());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setOpaque(true);
        cancelButton.setBackground(new Color(0x071A64));
        cancelButton.setForeground(new Color(0xFFFFFF));
        cancelButton.setFont(new Font("Georgia", Font.BOLD, 14));
        cancelButton.setMargin(new Insets(0, 0, 0, 0));
        cancelButton.addActionListener(new FrameDisposal(selectorFrame));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(cancelButton);

        box.add(namePanel);
        box.add(comboBoxPanel);
        box.add(buttonsPanel);

        return box;
    }
}
