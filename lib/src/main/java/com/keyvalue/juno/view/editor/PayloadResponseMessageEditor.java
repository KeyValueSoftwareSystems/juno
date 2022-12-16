package com.keyvalue.juno.view.editor;

import burp.IBurpExtenderCallbacks;
import burp.IMessageEditor;
import burp.IMessageEditorController;
import lombok.Getter;

@Getter
public class PayloadResponseMessageEditor {
    private IMessageEditor messageEditor;

    public PayloadResponseMessageEditor(
      IBurpExtenderCallbacks callbacks,
      IMessageEditorController messageEditorController
    ) {
        messageEditor = callbacks.createMessageEditor(messageEditorController, false);
    }
}
