package com.keyvalue.juno.view.editor;

import burp.IBurpExtenderCallbacks;
import burp.IMessageEditor;
import burp.IMessageEditorController;

import lombok.Getter;

@Getter
public class BaseRequestMessageEditor {
    private IMessageEditor messageEditor;
    
    public BaseRequestMessageEditor(
        IBurpExtenderCallbacks callbacks,
        IMessageEditorController messageEditorController
    ) {
        messageEditor = callbacks.createMessageEditor(messageEditorController, true);
        messageEditor.setMessage("".getBytes(), true);
    }
}
