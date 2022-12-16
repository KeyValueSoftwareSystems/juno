package com.keyvalue.juno.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.model.LogEntry;
import com.keyvalue.juno.view.editor.PayloadRequestMessageEditor;
import com.keyvalue.juno.view.editor.PayloadResponseMessageEditor;
import com.keyvalue.juno.view.label.ResultsLabel;
import com.keyvalue.juno.view.pane.PayloadRequestResponseTab;
import com.keyvalue.juno.view.pane.ResultSplitPane;
import com.keyvalue.juno.view.table.ResultsTable;

import burp.IHttpService;
import burp.IBurpExtenderCallbacks;
import burp.IMessageEditorController;

public class ResultWindow extends JFrame implements IMessageEditorController {
    private final IBurpExtenderCallbacks callbacks;
    private final PayloadRequestMessageEditor payloadRequestMessageEditor;
    private final PayloadResponseMessageEditor payloadResponseMessageEditor;

    private JScrollPane resultsScrollPane;
    private JScrollPane requestScrollPane;
    private JScrollPane responseScrollPane;
    private ResultsLabel resultsLabel;
    private ResultsTable resultsTable;
    private PayloadRequestResponseTab payloadRequestResponseTab;
    private ResultSplitPane resultSplitPane;

    public ResultWindow(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.payloadRequestMessageEditor = new PayloadRequestMessageEditor(callbacks, this);
        this.payloadResponseMessageEditor = new PayloadResponseMessageEditor(callbacks, this);

        setTitle(Constants.RESULTS_WINDOW_NAME);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initComponents();
        initLayout();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        resultsLabel = new ResultsLabel();
        resultsTable = new ResultsTable(payloadRequestMessageEditor, payloadResponseMessageEditor);
        requestScrollPane = new JScrollPane(payloadRequestMessageEditor.getMessageEditor().getComponent());
        responseScrollPane = new JScrollPane(payloadResponseMessageEditor.getMessageEditor().getComponent());
        payloadRequestResponseTab = new PayloadRequestResponseTab(requestScrollPane, responseScrollPane);
        resultsScrollPane = new JScrollPane(resultsTable);
        resultSplitPane = new ResultSplitPane(resultsScrollPane, payloadRequestResponseTab);
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
            .addComponent(resultSplitPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(resultsLabel)
                .addGap(12, 12, 12)
                .addComponent(resultSplitPane, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            )
        );

        callbacks.customizeUiComponent(this);
    }

    public void addDataRow(LogEntry logEntry) {
        resultsTable.addDataRow(logEntry);
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
