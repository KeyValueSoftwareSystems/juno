package com.keyvalue.jwtTester;

import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JTextField;

import burp.IContextMenuFactory;
import burp.IContextMenuInvocation;
import burp.IHttpRequestResponse;
import burp.IMessageEditor;

public class Menu implements IContextMenuFactory {
    private final IMessageEditor messageEditor;
    private final JTextField targetField;
    private final JMenuItem sendOption = new JMenuItem(Constants.SEND_MESSAGE_STRING);
    private final List<JMenuItem> menuItems = List.of(sendOption);

    private IHttpRequestResponse baseRequestResponse;
    private IHttpRequestResponse[] messages;

    public Menu(IMessageEditor messageEditor, IHttpRequestResponse baseRequestResponse, JTextField targetField) {
        this.messageEditor = messageEditor;
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
                int port = baseRequestResponse.getHttpService().getPort();
                String target = Utils.generateTarget(protocol, host, port);

                messageEditor.setMessage(baseRequestResponse.getRequest(), true);
                targetField.setText(target);
            }
        });
    }
}
