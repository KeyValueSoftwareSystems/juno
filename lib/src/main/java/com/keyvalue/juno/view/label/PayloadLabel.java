package com.keyvalue.juno.view.label;

import javax.swing.JLabel;

import com.keyvalue.juno.model.Constants;

import java.awt.Font;

public class PayloadLabel extends JLabel {
    public PayloadLabel() {
        setFont(new Font(getFont().getName(), Font.BOLD, 14));
        setText(Constants.PAYLOAD_STRING);
    }
}