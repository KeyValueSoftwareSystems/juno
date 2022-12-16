package com.keyvalue.juno.view.pane;

import java.awt.Component;

import javax.swing.JTabbedPane;

import com.keyvalue.juno.model.Constants;

public class PayloadRequestResponseTab extends JTabbedPane {
    public PayloadRequestResponseTab(Component leftComponent, Component rightComponent) {
        addTab(Constants.REQUEST_STRING, leftComponent);
        addTab(Constants.RESPONSE_STRING, rightComponent);
    }
}
