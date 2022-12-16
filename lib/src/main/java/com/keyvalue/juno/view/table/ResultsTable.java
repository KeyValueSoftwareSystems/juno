package com.keyvalue.juno.view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.model.LogEntry;
import com.keyvalue.juno.view.editor.PayloadRequestMessageEditor;
import com.keyvalue.juno.view.editor.PayloadResponseMessageEditor;

import burp.IHttpRequestResponse;

public class ResultsTable extends JTable {
    private final PayloadRequestMessageEditor payloadRequestMessageEditor;
    private final PayloadResponseMessageEditor payloadResponseMessageEditor;
    private final List<Object[]> data = new ArrayList<>();
    private final List<LogEntry> logs = new ArrayList<>();

    private final AbstractTableModel tableModel = new AbstractTableModel() {
        @Override
        public String getColumnName(int column) {
            return Constants.RESULT_TABLE_COLUMNS[column];
        }

        @Override
        public int getColumnCount() {
            return Constants.RESULT_TABLE_COLUMNS.length;
        }

        @Override
        public synchronized int getRowCount() {
            return data.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            return data.get(row)[column];
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            data.get(row)[column] = value;
        }
    };

    public ResultsTable(
        PayloadRequestMessageEditor payloadRequestMessageEditor,
        PayloadResponseMessageEditor payloadResponseMessageEditor
    ) {
        this.payloadRequestMessageEditor = payloadRequestMessageEditor;
        this.payloadResponseMessageEditor = payloadResponseMessageEditor;

        setModel(tableModel);
        setAutoCreateRowSorter(true);
    }

    /**
     * The action to perform when individual table rows are selected by the user.
     * Here it is used to show the request and response for the selected row.
     */
    @Override
    public void changeSelection(int row, int column, boolean toggle, boolean extend) {
        LogEntry logEntry = logs.get(row);
        IHttpRequestResponse selectedMessage = logEntry.requestResponse();

        payloadRequestMessageEditor.getMessageEditor().setMessage(selectedMessage.getRequest(), true);
        payloadResponseMessageEditor.getMessageEditor().setMessage(selectedMessage.getResponse(), false);

        super.changeSelection(row, column, toggle, extend);
    }

    /**
     * Adds a new LogEntry to the result table.
     * @param logEntry - The LogEntry to add.
     */
    public synchronized void addDataRow(LogEntry logEntry) {
        logs.add(logEntry);
        data.add(new Object [] {
            getRowCount() + 1,
            logEntry.payload(),
            logEntry.statusCode(),
            logEntry.contentLength()
        });

        tableModel.fireTableDataChanged();
    }
}
