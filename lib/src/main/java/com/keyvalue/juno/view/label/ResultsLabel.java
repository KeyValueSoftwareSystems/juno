package com.keyvalue.juno.view.label;

import java.awt.Font;

import javax.swing.JLabel;

import com.keyvalue.juno.model.Constants;

public class ResultsLabel extends JLabel {
    public ResultsLabel() {
        setFont(new Font(getFont().getName(), Font.BOLD, 14));
        setText(Constants.RESULTS_STRING);
    }
}
