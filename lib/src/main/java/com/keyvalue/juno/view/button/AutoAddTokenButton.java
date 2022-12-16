package com.keyvalue.juno.view.button;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;

public class AutoAddTokenButton extends JButton {
    public AutoAddTokenButton(BaseRequestMessageEditor baseRequestMessageEditor, JTextField tokenField) {
        setText(Constants.AUTO_STRING);

        addActionListener(evt -> {
            byte[] messageBytes = baseRequestMessageEditor.getMessageEditor().getMessage();

            if (messageBytes != null) {
                String message = new String(messageBytes);
                Pattern pattern = Pattern.compile(Constants.JWT_REGEX);
                Matcher match = pattern.matcher(message);

                if (match.find()) {
                    tokenField.setText(match.group(0));
                }
            }
        });
    }
}
