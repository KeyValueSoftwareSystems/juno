package com.keyvalue.jwtTester;

import javax.swing.*;

import java.awt.Component;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import burp.*;

public class Tab extends JPanel implements ITab {
    private final IBurpExtenderCallbacks callbacks;
    private final IMessageEditor messageEditor;

    private JButton addPayloadButton;
    private JButton autoPayloadButton;
    private JButton clearPayloadButton;
    private JCheckBox httpsCheckbox;
    private JSeparator mainSeperator;
    private JLabel optionsLabel;
    private JPanel optionsPanel;
    private JLabel payloadLabel;
    private JPanel payloadPanel;
    private JScrollPane payloadScrollPane;
    private JButton startAttackButton;
    private JTextField targetField;
    private JLabel targetLabel;
    private JLabel tokenLabel;
    private JTextField tokenField;
    private JTextField threadsField;
    private JLabel threadsLabel;

    public Tab(IBurpExtenderCallbacks extenderCallbacks, IMessageEditor editor) {
        callbacks = extenderCallbacks;
        messageEditor = editor;
        
        initComponents();
    }

    private void initComponents() {
        optionsPanel = new JPanel();
        optionsLabel = new JLabel();
        threadsLabel = new JLabel();
        threadsField = new JTextField();
        httpsCheckbox = new JCheckBox();
        startAttackButton = new JButton();
        mainSeperator = new JSeparator();
        payloadPanel = new JPanel();
        targetLabel = new JLabel();
        targetField = new JTextField();
        payloadScrollPane = new JScrollPane();
        tokenLabel = new JLabel();
        tokenField = new JTextField();
        payloadLabel = new JLabel();
        addPayloadButton = new JButton();
        clearPayloadButton = new JButton();
        autoPayloadButton = new JButton();

        initMessageEditor();
        initOptionsLabel();
        initThreadsLabel();
        initHttpsCheckbox();
        initStartAttackButton();
        initTargetField();
        initTokenField();
        initPayloadLabel();
        initAddPayloadButton();
        initClearPayloadButton();
        initAutoPayloadButton();
        initLayout();
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
                                .addComponent(threadsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(httpsCheckbox))
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
                    .addComponent(threadsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(httpsCheckbox)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        payloadScrollPane.setViewportView(messageEditor.getComponent());

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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mainSeperator)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(payloadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(payloadLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addPayloadButton)
                                    .addComponent(clearPayloadButton)
                                    .addComponent(autoPayloadButton))))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSeperator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(payloadLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(payloadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(addPayloadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearPayloadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(autoPayloadButton)))
                .addContainerGap())
        );
    }

    private void initMessageEditor() {
        messageEditor.setMessage("".getBytes(), true);
    }
    
    private void initPayloadLabel() {
        payloadLabel.setFont(new Font(payloadLabel.getFont().getName(), Font.BOLD, 14));
        payloadLabel.setText(Constants.PAYLOAD_TEXT);
    }

    private void initTargetField() {
        targetLabel.setText(Constants.TARGET_TEXT);
        targetField.setText(Constants.DEFAULT_TARGET_TEXT);
        targetField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // TODO - add action
            }
        });
    }

    private void initTokenField() {
        tokenLabel.setText(Constants.TOKEN_TEXT);
        tokenField.setEditable(false);
        tokenField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // TODO
            }
        });
    }

    private void initStartAttackButton() {
        startAttackButton.setText(Constants.ATTACK_TEXT);
        startAttackButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // TODO - add action
            }
        });
    }

    private void initHttpsCheckbox() {
        httpsCheckbox.setText(Constants.HTTPS_TEXT);
        httpsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
        httpsCheckbox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // TODO - add action
            }
        });
    }

    private void initThreadsLabel() {
        threadsLabel.setText(Constants.THREADS_TEXT);
        threadsField.setText("1");
        threadsField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // TODO - add action
            }
        });
    }

    private void initOptionsLabel() {
        optionsLabel.setFont(new Font(optionsLabel.getFont().getName(), Font.BOLD, 14));
        optionsLabel.setText(Constants.OPTIONS_TEXT);
    }

    private void initAddPayloadButton() {
        addPayloadButton.setText(Constants.ADD_TEXT);
        addPayloadButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byte[] selectedDataBytes = messageEditor.getSelectedData();
                
                if (selectedDataBytes != null) {
                    String jwtToken = new String(selectedDataBytes);

                    tokenField.setText(jwtToken);
                }
            }
        });
    }

    private void initClearPayloadButton() {
        clearPayloadButton.setText(Constants.CLEAR_TEXT);
        clearPayloadButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tokenField.setText("");
            }
        });
    }

    private void initAutoPayloadButton() {
        autoPayloadButton.setText(Constants.AUTO_TEXT);
        autoPayloadButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byte[] messageBytes = messageEditor.getMessage();
                
                if (messageBytes != null) {
                    String message = new String(messageBytes);
                    Pattern pattern = Pattern.compile(Constants.JWT_REGEX);
                    Matcher match = pattern.matcher(message);

                    if (match.find()) {
                        tokenField.setText(match.group(0));
                    }
                }
            }
        });
    }

    @Override
    public String getTabCaption() {
        return Constants.EXTENTION_NAME;
    }

    @Override
    public Component getUiComponent() {
        return this;
    }
}
