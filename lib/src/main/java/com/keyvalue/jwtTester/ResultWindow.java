package com.keyvalue.jwtTester;

import java.awt.Font;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import burp.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultWindow extends JFrame {
    private final IBurpExtenderCallbacks callbacks;
    private final IMessageEditor requestMessageViewer;
    private final IMessageEditor responseMessageViewer;

    private JLabel resultsLabel;
    private JTable resultsTable;
    private JTabbedPane tabs;
    private JSplitPane splitPane;
    private JScrollPane resultsScrollPane;
    private JScrollPane requestScrollPane;
    private JScrollPane responseScrollPane;

    public void render() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        tabs = new JTabbedPane();
        splitPane = new JSplitPane();
        resultsLabel = new JLabel();
        resultsTable = new JTable();
        resultsScrollPane = new JScrollPane();
        requestScrollPane = new JScrollPane();
        responseScrollPane = new JScrollPane();

        setTitle(Constants.RESULTS_WINDOW_NAME);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        resultsLabel.setFont(new Font(resultsLabel.getFont().getName(), Font.BOLD, 14));
        resultsLabel.setText(Constants.RESULTS_STRING);

        resultsTable.setAutoCreateRowSorter(true);
        resultsTable.setPreferredSize(new Dimension(300, 0));
        resultsTable.setModel(new DefaultTableModel(
            Constants.RESULT_TABLE_MODEL,
            Constants.RESULT_TABLE_COLUMNS
        ) {
            Class<?>[] types = new Class [] {
                Object.class, String.class, Integer.class, Long.class
            };

            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        resultsScrollPane.setViewportView(resultsTable);
        requestScrollPane.setViewportView(requestMessageViewer.getComponent());
        responseScrollPane.setViewportView(responseMessageViewer.getComponent());
        
        tabs.addTab(Constants.REQUEST_STRING, requestScrollPane);
        tabs.addTab(Constants.RESPONSE_STRING, responseScrollPane);
        
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setLeftComponent(resultsScrollPane);
        splitPane.setRightComponent(tabs);

        initLayout();
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(resultsLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(splitPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(resultsLabel)
                .addGap(12, 12, 12)
                .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            )
        );

        callbacks.customizeUiComponent(this);
    }
}
