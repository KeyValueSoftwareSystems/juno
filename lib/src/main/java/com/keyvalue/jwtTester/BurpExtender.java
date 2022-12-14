package com.keyvalue.jwtTester;

import javax.swing.JTextField;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IMessageEditor;
import burp.IMessageEditorController;

public class BurpExtender implements IBurpExtender, IMessageEditorController {
    private Tab tab;
    private JTextField targetField;
    private IMessageEditor payloadMessageEditor;
    private IHttpRequestResponse baseRequestResponse;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        targetField = new JTextField();
        payloadMessageEditor = callbacks.createMessageEditor(this, true);
        Menu menu = new Menu(payloadMessageEditor, baseRequestResponse, targetField);
        tab = new Tab(callbacks, payloadMessageEditor, targetField);

        callbacks.setExtensionName(Constants.EXTENSION_NAME);
        callbacks.addSuiteTab(tab);
        callbacks.registerContextMenuFactory(menu);

        tab.render();
    }

    @Override
    public IHttpService getHttpService() {
        return baseRequestResponse.getHttpService();
    }

    @Override
    public byte[] getRequest() {
        return baseRequestResponse.getRequest();
    }

    @Override
    public byte[] getResponse() {
        return baseRequestResponse.getResponse();
    }
}
