package com.keyvalue.juno.view.button;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;

public class AddTokenButton extends JButton {
    public AddTokenButton(BaseRequestMessageEditor baseRequestMessageEditor, JTextField tokenField) {
        setText(Constants.ADD_STRING);

        addActionListener(evt -> {
            byte[] selectedDataBytes = baseRequestMessageEditor.getMessageEditor().getSelectedData();

            if (selectedDataBytes != null) {
                String jwtToken = new String(selectedDataBytes);
                tokenField.setText(jwtToken);
            }
        });
    }
}
