package com.keyvalue.jwtTester;

import burp.*;

public class BurpExtender implements IBurpExtender, IMessageEditorController {
    private IBurpExtenderCallbacks callbacks;
    private IMessageEditor messageEditor;
    private Tab tab;
    private Menu menu;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks extenderCallbacks) {
        callbacks = extenderCallbacks;
        messageEditor = callbacks.createMessageEditor(this, true);

        tab = new Tab(callbacks, messageEditor);
        menu = new Menu(messageEditor);
        
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
