package com.keyvalue.jwtTester;

import burp.*;

public class BurpExtender implements IBurpExtender, IHttpListener, IMessageEditorController {

    private IBurpExtenderCallbacks callbacks;
    private IHttpRequestResponse messageInfo;
    private Tab tab;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks extenderCallbacks) {
        callbacks = extenderCallbacks;

        tab = new Tab(callbacks);
        
        callbacks.setExtensionName(Constants.EXTENTION_NAME);
        callbacks.registerHttpListener(BurpExtender.this);

        callbacks.customizeUiComponent(tab);
        callbacks.addSuiteTab(tab);
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse httpRequestResponse) {
        messageInfo = httpRequestResponse;
    }

    @Override
    public IHttpService getHttpService() {
        return messageInfo.getHttpService();
    }

    @Override
    public byte[] getRequest() {
        return messageInfo.getRequest();
    }

    @Override
    public byte[] getResponse() {
        return messageInfo.getResponse();
    }
}
