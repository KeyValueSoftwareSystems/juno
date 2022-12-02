package com.keyvalue.jwtTester;

import burp.*;

public class BurpExtender implements IBurpExtender, IMessageEditorController {
    private IBurpExtenderCallbacks callbacks;
    private IMessageEditor payloadMessageEditor;
    private IMessageEditor requestMessageViewer;
    private IMessageEditor responseMessageViewer;
    private Tab tab;
    private Menu menu;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks extenderCallbacks) {
        callbacks = extenderCallbacks;
        payloadMessageEditor = callbacks.createMessageEditor(this, true);
        requestMessageViewer = callbacks.createMessageEditor(this, false);
        responseMessageViewer = callbacks.createMessageEditor(this, false);
        menu = new Menu(payloadMessageEditor);
        tab = new Tab(callbacks, payloadMessageEditor, requestMessageViewer, responseMessageViewer);

        callbacks.setExtensionName(Constants.EXTENTION_NAME);
        callbacks.customizeUiComponent(tab);
        callbacks.addSuiteTab(tab);
        callbacks.registerContextMenuFactory(menu);
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
