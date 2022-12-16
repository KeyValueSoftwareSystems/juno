package com.keyvalue.juno.view;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.view.button.AddTokenButton;
import com.keyvalue.juno.view.button.AutoAddTokenButton;
import com.keyvalue.juno.view.button.ClearTokenButton;
import com.keyvalue.juno.view.button.StartAttackButton;
import com.keyvalue.juno.view.editor.BaseRequestMessageEditor;
import com.keyvalue.juno.view.field.TargetField;
import com.keyvalue.juno.view.field.ThreadsField;
import com.keyvalue.juno.view.field.TokenField;
import com.keyvalue.juno.view.label.OptionsLabel;
import com.keyvalue.juno.view.label.PayloadLabel;
import com.keyvalue.juno.view.label.TargetLabel;
import com.keyvalue.juno.view.label.ThreadsLabel;
import com.keyvalue.juno.view.label.TokenLabel;

import java.awt.Component;

import burp.IBurpExtenderCallbacks;
import burp.ITab;

public class Tab extends JPanel implements ITab {
    private final IBurpExtenderCallbacks callbacks;
    private final BaseRequestMessageEditor baseRequestMessageEditor;
    private final TargetField targetField;

    private JPanel optionsPanel;
    private JPanel payloadPanel;
    private JSeparator separator;
    private JScrollPane payloadScrollPane;
    private TokenField tokenField;
    private ThreadsField threadsField;
    private AddTokenButton addTokenButton;
    private ClearTokenButton clearTokenButton;
    private AutoAddTokenButton autoAddTokenButton;
    private StartAttackButton startAttackButton;
    private OptionsLabel optionsLabel;
    private PayloadLabel payloadLabel;
    private TargetLabel targetLabel;
    private TokenLabel tokenLabel;
    private ThreadsLabel threadsLabel;

    public Tab(
        IBurpExtenderCallbacks callbacks,
        BaseRequestMessageEditor baseRequestMessageEditor,
        TargetField targetField
    ) {
        this.callbacks = callbacks;
        this.baseRequestMessageEditor = baseRequestMessageEditor;
        this.targetField = targetField;
        
        initComponents();
        initLayout();
    }
    
    private void initComponents() {
        optionsLabel = new OptionsLabel();
        payloadLabel = new PayloadLabel();
        targetLabel = new TargetLabel();
        tokenLabel = new TokenLabel();
        threadsLabel = new ThreadsLabel();
        optionsPanel = new JPanel();
        payloadPanel = new JPanel();
        separator = new JSeparator();
        payloadScrollPane = new JScrollPane();
        tokenField = new TokenField();
        threadsField = new ThreadsField();
        clearTokenButton = new ClearTokenButton(tokenField);
        addTokenButton = new AddTokenButton(baseRequestMessageEditor, tokenField);
        autoAddTokenButton = new AutoAddTokenButton(baseRequestMessageEditor, tokenField);
        startAttackButton = new StartAttackButton(callbacks,baseRequestMessageEditor,targetField,tokenField,threadsField);
    }

    @Override
    public Component getUiComponent() {
        return this;
    }

    @Override
    public String getTabCaption() {
        return Constants.EXTENSION_NAME;
    }

    private void initLayout() {
        GroupLayout optionsPanelLayout = new GroupLayout(optionsPanel);

        optionsPanel.setLayout(optionsPanelLayout);

        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addComponent(optionsLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startAttackButton))
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(optionsPanelLayout.createSequentialGroup()
                                .addComponent(threadsLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(threadsField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(optionsLabel)
                    .addComponent(startAttackButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(threadsLabel)
                    .addComponent(threadsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        payloadScrollPane.setViewportView(baseRequestMessageEditor.getMessageEditor().getComponent());

        GroupLayout payloadPanelLayout = new GroupLayout(payloadPanel);

        payloadPanel.setLayout(payloadPanelLayout);

        payloadPanelLayout.setHorizontalGroup(
            payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(payloadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(payloadScrollPane)
                    .addGroup(payloadPanelLayout.createSequentialGroup()
                        .addComponent(targetLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(targetField, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
                    .addGroup(payloadPanelLayout.createSequentialGroup()
                        .addComponent(tokenLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tokenField, GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)))
                .addContainerGap())
        );

        payloadPanelLayout.setVerticalGroup(
            payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(payloadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(targetLabel)
                    .addComponent(targetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(tokenLabel)
                    .addComponent(tokenField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payloadScrollPane, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout tabLayout = new GroupLayout(this);

        setLayout(tabLayout);

        tabLayout.setHorizontalGroup(
            tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, tabLayout.createSequentialGroup()
                        .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(separator)
                            .addGroup(tabLayout.createSequentialGroup()
                                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(payloadPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(tabLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(payloadLabel)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(addTokenButton)
                                    .addComponent(clearTokenButton)
                                    .addComponent(autoAddTokenButton))))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        tabLayout.setVerticalGroup(
            tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(tabLayout.createSequentialGroup()
                        .addComponent(payloadLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(payloadPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(addTokenButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearTokenButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(autoAddTokenButton)))
                .addContainerGap())
        );

        callbacks.customizeUiComponent(this);
    }
}
