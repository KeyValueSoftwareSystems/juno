package com.keyvalue.jwtTester;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import burp.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Menu implements IContextMenuFactory {
    private final IMessageEditor messageEditor;

    private List<JMenuItem> menuItems;
    private JMenuItem sendOption;
    private IHttpRequestResponse[] messages;

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        messages = invocation.getSelectedMessages();
        menuItems = new ArrayList<>();
        sendOption = new JMenuItem(Constants.SEND_MESSAGE_TEXT);

        initSendOption();

        menuItems.add(sendOption);

        return menuItems;
    }

    private void initSendOption() {
        sendOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (messages.length == 1) {
                    messageEditor.setMessage(messages[0].getRequest(), true);
                }
            }
        });
    }
}
