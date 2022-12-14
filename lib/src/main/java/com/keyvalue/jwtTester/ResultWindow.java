package com.keyvalue.jwtTester;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

import burp.IBurpExtenderCallbacks;
import burp.IHttpService;
import burp.IMessageEditor;
import burp.IMessageEditorController;

public class ResultWindow extends JFrame implements IMessageEditorController {
    private final IBurpExtenderCallbacks callbacks;
    private final IMessageEditor requestMessageViewer;
    private final IMessageEditor responseMessageViewer;

    private ResultTable resultTable;
    private JLabel resultsLabel;
    private JTabbedPane tabs;
    private JSplitPane splitPane;
    private JScrollPane resultsScrollPane;
    private JScrollPane requestScrollPane;
    private JScrollPane responseScrollPane;

    public ResultWindow(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        requestMessageViewer = callbacks.createMessageEditor(this, false);
        responseMessageViewer = callbacks.createMessageEditor(this, false);
    }

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
        resultsScrollPane = new JScrollPane();
        requestScrollPane = new JScrollPane();
        responseScrollPane = new JScrollPane();
        resultTable = new ResultTable(requestMessageViewer, responseMessageViewer);

        setTitle(Constants.RESULTS_WINDOW_NAME);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        resultsLabel.setFont(new Font(resultsLabel.getFont().getName(), Font.BOLD, 14));
        resultsLabel.setText(Constants.RESULTS_STRING);

        resultsScrollPane.setViewportView(resultTable);
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

    public void addDataRow(LogEntry logEntry) {
        resultTable.addDataRow(logEntry);
    }

    @Override
    public IHttpService getHttpService() {
        return null;
    }

    @Override
    public byte[] getRequest() {
        return null;
    }

    @Override
    public byte[] getResponse() {
        return null;
    }
}
