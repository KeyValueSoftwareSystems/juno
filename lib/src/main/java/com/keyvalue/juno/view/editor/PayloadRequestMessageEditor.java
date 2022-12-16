package com.keyvalue.juno.view.editor;

import burp.IBurpExtenderCallbacks;
import burp.IMessageEditor;
import burp.IMessageEditorController;
import lombok.Getter;

@Getter
public class PayloadRequestMessageEditor {
    private IMessageEditor messageEditor;
  
    public PayloadRequestMessageEditor(
      IBurpExtenderCallbacks callbacks,
      IMessageEditorController messageEditorController
    ) {
        messageEditor = callbacks.createMessageEditor(messageEditorController, false);
    }
}
