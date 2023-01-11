package com.keyvalue.juno.view.button;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.keyvalue.juno.controller.Utils;
import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;

public class AutoAddTokenButton extends JButton {
    public AutoAddTokenButton(BaseRequestMessageEditor baseRequestMessageEditor, JTextField tokenField) {
        setText(Constants.AUTO_STRING);

        addActionListener(evt -> {
            byte[] messageBytes = baseRequestMessageEditor.getMessageEditor().getMessage();

            if (messageBytes != null) {
                String message = new String(messageBytes);
                String extractedJWT = Utils.extractJWT(message);

                if (extractedJWT != null) {
                    tokenField.setText(extractedJWT);
                }
            }
        });
    }
}
