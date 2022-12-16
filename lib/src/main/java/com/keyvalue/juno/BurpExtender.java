package com.keyvalue.juno;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.view.Menu;
import com.keyvalue.juno.view.Tab;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;
import com.keyvalue.juno.view.field.TargetField;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IMessageEditorController;

public class BurpExtender implements IBurpExtender, IMessageEditorController {
    private Tab tab;
    private Menu menu;
    private TargetField targetField;
    private BaseRequestMessageEditor baseRequestMessageEditor;
    private IHttpRequestResponse baseRequestResponse;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        targetField = new TargetField();
        baseRequestMessageEditor = new BaseRequestMessageEditor(callbacks, this);
        tab = new Tab(callbacks, baseRequestMessageEditor, targetField);
        menu = new Menu(baseRequestMessageEditor, baseRequestResponse, targetField);

        callbacks.setExtensionName(Constants.EXTENSION_NAME);
        callbacks.addSuiteTab(tab);
        callbacks.registerContextMenuFactory(menu);
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
