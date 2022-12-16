package com.keyvalue.juno.view.pane;

import java.awt.Component;

import javax.swing.JSplitPane;

public class ResultSplitPane extends JSplitPane {
    public ResultSplitPane(Component topComponent, Component bottomComponent) {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setLeftComponent(topComponent);
        setRightComponent(bottomComponent);
    }
}
