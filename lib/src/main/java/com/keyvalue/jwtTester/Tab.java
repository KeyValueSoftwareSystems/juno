package com.keyvalue.jwtTester;

import java.awt.Component;
import java.awt.Font;

import burp.*;

import javax.swing.*;;

public class Tab extends JPanel implements ITab, IMessageEditorController {
    private final IBurpExtenderCallbacks callbacks;
    
    private JButton addPayloadButton;
    private JButton autoPayloadButton;
    private JButton clearPayloadButton;
    private JCheckBox httpsCheckbox;
    private JSeparator mainSeperator;
    private JLabel optionsLabel;
    private JPanel optionsPanel;
    private IMessageEditor payloadEditorPane;
    private JLabel payloadLabel;
    private JPanel payloadPanel;
    private JScrollPane payloadScrollPane;
    private JButton startAttackButton;
    private JTextField targetField;
    private JLabel targetLabel;
    private JTextField threadsField;
    private JLabel threadsLabel;

    public Tab(IBurpExtenderCallbacks extenderCallbacks) {
        callbacks = extenderCallbacks;
        
        initComponents();
    }

    private void initComponents() {

        autoPayloadButton = new JButton();
        clearPayloadButton = new JButton();
        addPayloadButton = new JButton();
        optionsPanel = new JPanel();
        optionsLabel = new JLabel();
        threadsLabel = new JLabel();
        threadsField = new JTextField();
        httpsCheckbox = new JCheckBox();
        startAttackButton = new JButton();
        payloadPanel = new JPanel();
        targetLabel = new JLabel();
        targetField = new JTextField();
        payloadScrollPane = new JScrollPane();
        payloadEditorPane = callbacks.createMessageEditor(Tab.this, true);
        payloadLabel = new JLabel();
        mainSeperator = new JSeparator();

        autoPayloadButton.setText(Constants.AUTO_TEXT);
        autoPayloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoPayloadButtonActionPerformed(evt);
            }
        });

        clearPayloadButton.setText(Constants.CLEAR_TEXT);
        clearPayloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPayloadButtonActionPerformed(evt);
            }
        });

        addPayloadButton.setText(Constants.ADD_TEXT);
        addPayloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayloadButtonActionPerformed(evt);
            }
        });

        optionsLabel.setFont(new Font(optionsLabel.getFont().getName(), Font.BOLD, 14));
        optionsLabel.setText(Constants.OPTIONS_TEXT);

        threadsLabel.setText(Constants.THREADS_TEXT);

        threadsField.setText("1");
        threadsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threadsFieldActionPerformed(evt);
            }
        });

        httpsCheckbox.setText(Constants.HTTPS_TEXT);
        httpsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
        httpsCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                httpsCheckboxActionPerformed(evt);
            }
        });

        startAttackButton.setText(Constants.ATTACK_TEXT);
        startAttackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAttackButtonActionPerformed(evt);
            }
        });

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

        targetLabel.setText(Constants.TARGET_TEXT);

        targetField.setColumns(10);
        targetField.setText(Constants.DEFAULT_TARGET_TEXT);
        targetField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetFieldActionPerformed(evt);
            }
        });

        payloadScrollPane.setViewportView(payloadEditorPane.getComponent());

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
                        .addComponent(targetField, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)))
                .addContainerGap())
        );
        payloadPanelLayout.setVerticalGroup(
            payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(payloadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payloadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(targetLabel)
                    .addComponent(targetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payloadScrollPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        payloadLabel.setFont(new Font(payloadLabel.getFont().getName(), Font.BOLD, 14));
        payloadLabel.setText(Constants.PAYLOAD_TEXT);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(payloadPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(payloadLabel)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(addPayloadButton)
                            .addComponent(clearPayloadButton)
                            .addComponent(autoPayloadButton))
                        .addGap(8, 8, 8))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mainSeperator)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainSeperator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(payloadLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(payloadPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(addPayloadButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearPayloadButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(autoPayloadButton)))
                .addContainerGap())
        );
    }

    private void autoPayloadButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void clearPayloadButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void addPayloadButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void threadsFieldActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void httpsCheckboxActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void startAttackButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void targetFieldActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }

    @Override
    public String getTabCaption() {
        return Constants.EXTENTION_NAME;
    }

    @Override
    public Component getUiComponent() {
        return this;
    }

    @Override
    public IHttpService getHttpService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] getRequest() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] getResponse() {
        // TODO Auto-generated method stub
        return null;
    }
}
