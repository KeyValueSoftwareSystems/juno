package com.keyvalue.juno.view.label;

import javax.swing.JLabel;

import com.keyvalue.juno.model.Constants;

import java.awt.Font;

public class OptionsLabel extends JLabel {
    public OptionsLabel() {
        setFont(new Font(getFont().getName(), Font.BOLD, 14));
        setText(Constants.OPTIONS_STRING);
    }
}
