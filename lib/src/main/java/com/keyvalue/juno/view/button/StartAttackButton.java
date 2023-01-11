package com.keyvalue.juno.view.button;

import javax.swing.JButton;

import com.keyvalue.juno.controller.NoneAlgorithmAttacker;
import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.model.LogEntry;
import com.keyvalue.juno.model.Target;
import com.keyvalue.juno.view.ResultWindow;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;
import com.keyvalue.juno.view.field.TargetField;
import com.keyvalue.juno.view.field.ThreadsField;
import com.keyvalue.juno.view.field.TokenField;

import burp.IBurpExtenderCallbacks;
import burp.IResponseInfo;

public class StartAttackButton extends JButton {
    public StartAttackButton(
        IBurpExtenderCallbacks callbacks,
        BaseRequestMessageEditor baseRequestMessageEditor,
        TargetField targetField,
        TokenField tokenField,
        ThreadsField threadsField
    ) {
        setText(Constants.ATTACK_STRING);

        addActionListener(evt -> {
            ResultWindow resultWindow = new ResultWindow(callbacks);
            Target target = new Target(
                callbacks,
                targetField.getText(),
                baseRequestMessageEditor.getMessageEditor().getMessage(),
                tokenField.getText()
            );
            NoneAlgorithmAttacker attacker = new NoneAlgorithmAttacker(
                callbacks,
                target,
                Integer.parseInt(threadsField.getText())
            );

            attacker.startAttack((payload, requestResponse) -> {
                IResponseInfo responseInfo = callbacks.getHelpers().analyzeResponse(requestResponse.getResponse());
                int contentLength = requestResponse.getResponse().length - responseInfo.getBodyOffset();

                resultWindow.addDataRow(
                    new LogEntry(
                        responseInfo.getStatusCode(),
                        contentLength,
                        payload,
                        requestResponse
                ));
            });
        });
    }
}
