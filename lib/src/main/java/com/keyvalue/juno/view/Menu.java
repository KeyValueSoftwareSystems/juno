package com.keyvalue.juno.view;

import java.util.List;

import javax.swing.JMenuItem;

import com.keyvalue.juno.controller.Utils;
import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;
import com.keyvalue.juno.view.field.TargetField;

import burp.IContextMenuFactory;
import burp.IContextMenuInvocation;
import burp.IHttpRequestResponse;

public class Menu implements IContextMenuFactory {
    private final BaseRequestMessageEditor baseRequestMessageEditor;
    private final TargetField targetField;
    private final JMenuItem sendOption = new JMenuItem(Constants.SEND_MESSAGE_STRING);
    private final List<JMenuItem> menuItems = List.of(sendOption);

    private IHttpRequestResponse baseRequestResponse;
    private IHttpRequestResponse[] messages;

    public Menu(BaseRequestMessageEditor baseRequestMessageEditor, IHttpRequestResponse baseRequestResponse, TargetField targetField) {
        this.baseRequestMessageEditor = baseRequestMessageEditor;
        this.baseRequestResponse = baseRequestResponse;
        this.targetField = targetField;
    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        messages = invocation.getSelectedMessages();
        sendMessageToTab();

        return menuItems;
    }

    /**
     * Sends the current HTTP message to the extension tab.
     */
    private void sendMessageToTab() {
        sendOption.addActionListener(evt -> {
            if (messages.length == 1) {
                baseRequestResponse = messages[0];
                String protocol = baseRequestResponse.getHttpService().getProtocol();
                String host = baseRequestResponse.getHttpService().getHost();
                Integer port = baseRequestResponse.getHttpService().getPort();
                String targetAddress = Utils.generateWebAddress(protocol, host, port);

                targetField.setText(targetAddress);
                baseRequestMessageEditor.getMessageEditor().setMessage(baseRequestResponse.getRequest(), true);
            }
        });
    }
}
