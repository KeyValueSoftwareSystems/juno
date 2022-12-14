package com.keyvalue.jwtTester;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import burp.IHttpRequestResponse;
import burp.IMessageEditor;

public class ResultTable extends JTable {
    private final IMessageEditor requestMessageViewer;
    private final IMessageEditor responseMessageViewer;
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

    public ResultTable(IMessageEditor requestMessageViewer, IMessageEditor responseMessageViewer) {
        this.requestMessageViewer = requestMessageViewer;
        this.responseMessageViewer = responseMessageViewer;

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

        requestMessageViewer.setMessage(selectedMessage.getRequest(), true);
        responseMessageViewer.setMessage(selectedMessage.getResponse(), false);

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
