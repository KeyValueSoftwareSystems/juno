package com.keyvalue.juno.view.button;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.keyvalue.juno.model.Constants;

public class ClearTokenButton extends JButton {
    public ClearTokenButton(JTextField tokenField) {
        setText(Constants.CLEAR_STRING);

        addActionListener(evt -> tokenField.setText(""));
    }
}
