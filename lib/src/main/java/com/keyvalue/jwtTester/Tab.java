package com.keyvalue.jwtTester;

import burp.IBurpExtenderCallbacks;
import burp.IMessageEditor;
import burp.IResponseInfo;
import burp.ITab;

import java.awt.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Tab extends JPanel implements ITab {
    private final IBurpExtenderCallbacks callbacks;
    private final IMessageEditor payloadMessageEditor;
    private final JTextField targetField;
    
    private JButton addPayloadButton;
    private JButton autoPayloadButton;
    private JButton clearPayloadButton;
    private JSeparator mainSeparator;
    private JLabel optionsLabel;
    private JPanel optionsPanel;
    private JLabel payloadLabel;
    private JPanel payloadPanel;
    private JScrollPane payloadScrollPane;
    private JButton startAttackButton;
    private JLabel targetLabel;
    private JLabel tokenLabel;
    private JTextField tokenField;
    private JTextField threadsField;
    private JLabel threadsLabel;
    
    public void render() {
        initComponents();
    }

    private void initComponents() {
        optionsPanel = new JPanel();
        optionsLabel = new JLabel();
        threadsLabel = new JLabel();
        threadsField = new JTextField();
        startAttackButton = new JButton();
        mainSeparator = new JSeparator();
        payloadPanel = new JPanel();
        targetLabel = new JLabel();
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

        payloadScrollPane.setViewportView(payloadMessageEditor.getComponent());

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
                            .addComponent(mainSeparator)
                            .addGroup(tabLayout.createSequentialGroup()
                                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(payloadPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(tabLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(payloadLabel)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(addPayloadButton)
                                    .addComponent(clearPayloadButton)
                                    .addComponent(autoPayloadButton))))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        tabLayout.setVerticalGroup(
            tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSeparator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(tabLayout.createSequentialGroup()
                        .addComponent(payloadLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(payloadPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(addPayloadButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearPayloadButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(autoPayloadButton)))
                .addContainerGap())
        );

        callbacks.customizeUiComponent(this);
    }

    private void initMessageEditor() {
        payloadMessageEditor.setMessage("".getBytes(), true);
    }
    
    private void initPayloadLabel() {
        payloadLabel.setFont(new Font(payloadLabel.getFont().getName(), Font.BOLD, 14));
        payloadLabel.setText(Constants.PAYLOAD_STRING);
    }

    private void initTargetField() {
        targetLabel.setText(Constants.TARGET_STRING);
        targetField.setText(Constants.DEFAULT_TARGET_STRING);
    }

    private void initTokenField() {
        tokenLabel.setText(Constants.TOKEN_STRING);
        tokenField.setEditable(false);
    }

    private void initThreadsLabel() {
        threadsLabel.setText(Constants.THREADS_STRING);
        threadsField.setText("1");
    }
    
    private void initOptionsLabel() {
        optionsLabel.setFont(new Font(optionsLabel.getFont().getName(), Font.BOLD, 14));
        optionsLabel.setText(Constants.OPTIONS_STRING);
    }

    private void initAddPayloadButton() {
        addPayloadButton.setText(Constants.ADD_STRING);
        addPayloadButton.addActionListener(evt -> {
            byte[] selectedDataBytes = payloadMessageEditor.getSelectedData();

            if (selectedDataBytes != null) {
                String jwtToken = new String(selectedDataBytes);

                tokenField.setText(jwtToken);
            }
        });
    }

    private void initClearPayloadButton() {
        clearPayloadButton.setText(Constants.CLEAR_STRING);
        clearPayloadButton.addActionListener(evt -> tokenField.setText(""));
    }

    private void initAutoPayloadButton() {
        autoPayloadButton.setText(Constants.AUTO_STRING);
        autoPayloadButton.addActionListener(evt -> {
            byte[] messageBytes = payloadMessageEditor.getMessage();

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

    private void initStartAttackButton() {
        startAttackButton.setText(Constants.ATTACK_STRING);
        startAttackButton.addActionListener(evt -> {
            ResultWindow resultWindow = new ResultWindow(callbacks);
            Attacker attacker = new Attacker(
                callbacks,
                targetField.getText(),
                payloadMessageEditor.getMessage(),
                tokenField.getText(),
                Integer.parseInt(threadsField.getText())
            );

            resultWindow.render();

            attacker.startAttack((payload, requestResponse) -> {
                IResponseInfo responseInfo = callbacks.getHelpers().analyzeResponse(requestResponse.getResponse());
                int contentLength = requestResponse.getResponse().length - responseInfo.getBodyOffset();

                resultWindow.addDataRow(
                    new LogEntry(
                        responseInfo.getStatusCode(),
                        contentLength,
                        payload,
                        requestResponse
                ));
            });
        });
    }

    @Override
    public String getTabCaption() {
        return Constants.EXTENSION_NAME;
    }

    @Override
    public Component getUiComponent() {
        return this;
    }
}
