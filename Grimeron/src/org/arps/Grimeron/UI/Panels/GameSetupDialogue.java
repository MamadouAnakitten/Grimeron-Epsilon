/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.UI.Panels;

import java.awt.Color;
import java.awt.Frame;
import java.sql.Connection;
import javax.swing.*;
import org.arps.Grimeron.utils.DBConnector;
import org.arps.Grimeron.utils.RuleSet;

/**
 *
 * @author richa_000
 */
public class GameSetupDialogue extends javax.swing.JDialog {
    
    private RuleSet ruleSet;
    
    /**
     * Creates new form GameSetupDialoge
     */
    public GameSetupDialogue(java.awt.Frame parent, boolean modal, RuleSet rules) {
        super(parent, modal);
        this.ruleSet = rules;
        initComponents();
        preinitialize();
    }
      
    public GameSetupDialogue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.ruleSet = new RuleSet();
        initComponents();
        preinitialize();
    }
    
    public GameSetupDialogue(RuleSet ruleSet) {
        super(new Frame(), true);
        this.ruleSet = ruleSet;
        initComponents();
        preinitialize();
    }
    
    public GameSetupDialogue() {
        super(new Frame(), true);
        this.ruleSet = new RuleSet();
        initComponents();
        preinitialize();
    }
    
    private void preinitialize()
    {
        this.gameCountInput.setEnabled(false);
        this.rapidDelaySlider.setEnabled(false);
        this.statisticModeSelection.setEnabled(false);
        this.togglePanel(advancedPanel, text_advanced_hide);
        
        this.dynamictext_rapidhelp = new JLabel("This forces all players to be bots and plays them against each other.");
    }

    public void togglePanel(JComponent panel, JLabel hideText)
    {
        panel.setVisible(!panel.isVisible());
        
        if(panel.isVisible())
        {
            hideText.setText("[Hide]");
        }
        else
        {
            hideText.setText("[Show]");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setupText = new javax.swing.JLabel();
        text_generalsettings = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        text_general_hide = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        generalPanel = new javax.swing.JLayeredPane();
        rapidCheckbox = new javax.swing.JCheckBox();
        incognitoCheckBox = new javax.swing.JCheckBox();
        statisticModeSelection = new javax.swing.JCheckBox();
        gridDimensionField = new javax.swing.JFormattedTextField();
        gridDimensionText = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        rapidDelaySlider = new javax.swing.JSlider();
        text_rapiddelay = new javax.swing.JLabel();
        gameCountInput = new javax.swing.JFormattedTextField();
        text_rapid_count = new javax.swing.JLabel();
        text_advancedsettings = new javax.swing.JLabel();
        text_advanced_hide = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        advancedPanel = new javax.swing.JPanel();
        text_db_url = new javax.swing.JLabel();
        text_db_driver = new javax.swing.JLabel();
        urlField = new javax.swing.JFormattedTextField();
        portField = new javax.swing.JFormattedTextField();
        text_db_username = new javax.swing.JLabel();
        text_db_password = new javax.swing.JLabel();
        usernameField = new javax.swing.JFormattedTextField();
        driverSelector = new javax.swing.JComboBox();
        text_db_name = new javax.swing.JLabel();
        nameField = new javax.swing.JFormattedTextField();
        useConnection = new javax.swing.JCheckBox();
        testDBBtn = new javax.swing.JButton();
        text_teststatus = new javax.swing.JLabel();
        text_db_port = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        adminOverride = new javax.swing.JCheckBox();
        graphicOverrideEnabled = new javax.swing.JCheckBox();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setupText.setFont(new java.awt.Font("Golden Age", 0, 36)); // NOI18N
        setupText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setupText.setText("Grimeron Game Setup");
        setupText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        text_generalsettings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        text_generalsettings.setText("General Settings");

        text_general_hide.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        text_general_hide.setText("[Hide]");
        text_general_hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                text_general_hideMousePressed(evt);
            }
        });

        generalPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rapidCheckbox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rapidCheckbox.setText("Enable Rapid Mode");
        rapidCheckbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rapidCheckboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rapidCheckboxMouseExited(evt);
            }
        });
        rapidCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rapidCheckboxActionPerformed(evt);
            }
        });

        incognitoCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        incognitoCheckBox.setText("Incognito Mode: Disables database learning and keeps AI knowledge constant.");
        incognitoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incognitoCheckBoxActionPerformed(evt);
            }
        });

        statisticModeSelection.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        statisticModeSelection.setText("Statistic Mode: Generates statistics chart alongside simulated games.");
        statisticModeSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticModeSelectionActionPerformed(evt);
            }
        });

        gridDimensionField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridDimensionField.setText("11");

        gridDimensionText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        gridDimensionText.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        gridDimensionText.setText("Grid Dimension");

        rapidDelaySlider.setMajorTickSpacing(10);
        rapidDelaySlider.setMinimum(5);
        rapidDelaySlider.setMinorTickSpacing(5);
        rapidDelaySlider.setPaintLabels(true);
        rapidDelaySlider.setPaintTicks(true);
        rapidDelaySlider.setSnapToTicks(true);
        rapidDelaySlider.setDoubleBuffered(true);

        text_rapiddelay.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_rapiddelay.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        text_rapiddelay.setText("Rapid Graphic Delay (ms)");

        gameCountInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gameCountInput.setText("5");

        text_rapid_count.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_rapid_count.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        text_rapid_count.setText("Rapid Mode Game Count");

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rapidCheckbox)
                    .addComponent(incognitoCheckBox)
                    .addComponent(statisticModeSelection)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(text_rapid_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gridDimensionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(text_rapiddelay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gameCountInput, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gridDimensionField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rapidDelaySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(490, Short.MAX_VALUE))
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rapidCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(incognitoCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statisticModeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(gridDimensionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(gridDimensionField)))
                .addGap(6, 6, 6)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameCountInput)
                    .addComponent(text_rapid_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addComponent(text_rapiddelay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(125, 125, 125))
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addComponent(rapidDelaySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(90, 90, 90))))
        );
        generalPanel.setLayer(rapidCheckbox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(incognitoCheckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(statisticModeSelection, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(gridDimensionField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(gridDimensionText, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(jSeparator7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(rapidDelaySlider, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(text_rapiddelay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(gameCountInput, javax.swing.JLayeredPane.DEFAULT_LAYER);
        generalPanel.setLayer(text_rapid_count, javax.swing.JLayeredPane.DEFAULT_LAYER);

        text_advancedsettings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        text_advancedsettings.setText("Advanced Settings");

        text_advanced_hide.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        text_advanced_hide.setText("[Hide]");
        text_advanced_hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                text_advanced_hideMousePressed(evt);
            }
        });

        advancedPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        text_db_url.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_db_url.setText("Database URL");

        text_db_driver.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_db_driver.setText("DB Driver");

        urlField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        urlField.setText("URL");

        portField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        portField.setText("3306");

        text_db_username.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_db_username.setText("DB Username");

        text_db_password.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_db_password.setText("DB Password");

        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameField.setText("Grimeron");

        driverSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "JDBC / JNDI", "Derby" }));

        text_db_name.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_db_name.setText("Database Name");

        nameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameField.setText("Name");

        useConnection.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        useConnection.setText("Use this Connection");

        testDBBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        testDBBtn.setText("Test Connection");
        testDBBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testDBBtnActionPerformed(evt);
            }
        });

        text_teststatus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_teststatus.setForeground(new java.awt.Color(255, 51, 51));
        text_teststatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_teststatus.setText("Status: No Connection");

        text_db_port.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        text_db_port.setText("Database Port");

        adminOverride.setText("Don't Check Settings Before Running Game");

        graphicOverrideEnabled.setText("Disable Rapid Mode Graphics");
        graphicOverrideEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphicOverrideEnabledActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout advancedPanelLayout = new javax.swing.GroupLayout(advancedPanel);
        advancedPanel.setLayout(advancedPanelLayout);
        advancedPanelLayout.setHorizontalGroup(
            advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(text_db_url)
                    .addComponent(text_db_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(text_db_port, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(portField)
                    .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(urlField))
                .addGap(18, 18, 18)
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(advancedPanelLayout.createSequentialGroup()
                        .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_db_password)
                            .addComponent(text_db_driver))
                        .addGap(18, 18, 18)
                        .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driverSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField)))
                    .addGroup(advancedPanelLayout.createSequentialGroup()
                        .addComponent(text_db_username)
                        .addGap(18, 18, 18)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(useConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(testDBBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(text_teststatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminOverride)
                    .addComponent(graphicOverrideEnabled))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        advancedPanelLayout.setVerticalGroup(
            advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_db_url)
                    .addComponent(urlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_db_username)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useConnection)
                    .addComponent(adminOverride))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_db_password)
                    .addComponent(testDBBtn)
                    .addComponent(text_db_port, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(graphicOverrideEnabled, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_db_driver)
                    .addComponent(driverSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_db_name)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_teststatus))
                .addGap(16, 16, 16))
        );

        submitButton.setFont(new java.awt.Font("Golden Age", 0, 18)); // NOI18N
        submitButton.setText("Submit and Play");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_generalsettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_general_hide)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1))
            .addComponent(setupText, javax.swing.GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_advancedsettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_advanced_hide)
                .addGap(8, 8, 8)
                .addComponent(jSeparator6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalPanel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(submitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(advancedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(setupText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_general_hide)
                        .addComponent(text_generalsettings))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_advancedsettings)
                        .addComponent(text_advanced_hide))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advancedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void text_general_hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text_general_hideMousePressed
        togglePanel(generalPanel, text_general_hide);
    }//GEN-LAST:event_text_general_hideMousePressed

    private void rapidCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rapidCheckboxActionPerformed
        ruleSet.rapidMode = rapidCheckbox.isSelected();
        this.gameCountInput.setEnabled(rapidCheckbox.isSelected());
        this.rapidDelaySlider.setEnabled(rapidCheckbox.isSelected());
    }//GEN-LAST:event_rapidCheckboxActionPerformed

    private void graphicOverrideEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphicOverrideEnabledActionPerformed
        ruleSet.rapidGraphicOverrideEnabled = graphicOverrideEnabled.isEnabled();
    }//GEN-LAST:event_graphicOverrideEnabledActionPerformed

    private void incognitoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incognitoCheckBoxActionPerformed
        ruleSet.incognitoMode = incognitoCheckBox.isEnabled();
    }//GEN-LAST:event_incognitoCheckBoxActionPerformed

    private void statisticModeSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticModeSelectionActionPerformed
        ruleSet.statisticMode = statisticModeSelection.isEnabled();
    }//GEN-LAST:event_statisticModeSelectionActionPerformed

    private void text_advanced_hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text_advanced_hideMousePressed
        togglePanel(advancedPanel, text_advanced_hide);
    }//GEN-LAST:event_text_advanced_hideMousePressed

    private void testDBBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testDBBtnActionPerformed
        String host = urlField.getText();
        String port = portField.getText();
        String dbName = nameField.getText();
        String user = usernameField.getText();
        String pass = passwordField.getText();

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        Connection testCon = DBConnector.getSimpleConnection(url, user, pass);

        if(testCon == null)
        {
            text_teststatus.setForeground(Color.RED);
            text_teststatus.setText("Status: Connection Failed");
        }
        else
        {
            text_teststatus.setForeground(Color.GREEN);
            text_teststatus.setText("Status: Connection Good!");
        }
    }//GEN-LAST:event_testDBBtnActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        if(rapidCheckbox.isSelected())
        {
            if(graphicOverrideEnabled.isSelected())
            {
                ruleSet.rapidGraphicOverrideEnabled = true;
            }
            int rapidModeCount = 0;

            try
            {
                rapidModeCount = Integer.parseInt(gameCountInput.getText());
            } catch(Exception e){ }
            ruleSet.rapidModeCount = Math.abs(rapidModeCount);
        }
        if(adminOverride.isSelected())
        {
            ruleSet.adminTestOverride = true;
        }
        try
        {
            //int dimension = Integer.parseInt(gridDimensionField.getText());
            //GrimeronGrid finalGrid = new GrimeronGrid(dimension, dimension);
            //game.getGameFrame().setGrid(finalGrid);
        } catch(Exception ex) {

        }
        if(ruleSet.rapidMode && ruleSet.rapidGraphicOverrideEnabled)
        {
            ruleSet.delay = rapidDelaySlider.getValue();
        }
        try
        {
            ruleSet.dimensionSize = Integer.parseInt(gridDimensionField.getText());
        } catch (Exception ex){}
        
        this.dispose();
    }//GEN-LAST:event_submitButtonActionPerformed

    private void rapidCheckboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rapidCheckboxMouseEntered
        this.dynamictext_rapidhelp.setLocation(evt.getLocationOnScreen());
        this.dynamictext_rapidhelp.setVisible(true);
        System.out.println("Mouse Entered Rapid Checkbox");
    }//GEN-LAST:event_rapidCheckboxMouseEntered

    private void rapidCheckboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rapidCheckboxMouseExited
        this.dynamictext_rapidhelp.setVisible(false);
    }//GEN-LAST:event_rapidCheckboxMouseExited
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adminOverride;
    private javax.swing.JPanel advancedPanel;
    private javax.swing.JComboBox driverSelector;
    private javax.swing.JFormattedTextField gameCountInput;
    private javax.swing.JLayeredPane generalPanel;
    private javax.swing.JCheckBox graphicOverrideEnabled;
    private javax.swing.JFormattedTextField gridDimensionField;
    private javax.swing.JLabel gridDimensionText;
    private javax.swing.JCheckBox incognitoCheckBox;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JFormattedTextField nameField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JFormattedTextField portField;
    private javax.swing.JCheckBox rapidCheckbox;
    private javax.swing.JSlider rapidDelaySlider;
    private javax.swing.JLabel setupText;
    private javax.swing.JCheckBox statisticModeSelection;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton testDBBtn;
    private javax.swing.JLabel text_advanced_hide;
    private javax.swing.JLabel text_advancedsettings;
    private javax.swing.JLabel text_db_driver;
    private javax.swing.JLabel text_db_name;
    private javax.swing.JLabel text_db_password;
    private javax.swing.JLabel text_db_port;
    private javax.swing.JLabel text_db_url;
    private javax.swing.JLabel text_db_username;
    private javax.swing.JLabel text_general_hide;
    private javax.swing.JLabel text_generalsettings;
    private javax.swing.JLabel text_rapid_count;
    private javax.swing.JLabel text_rapiddelay;
    private javax.swing.JLabel text_teststatus;
    private javax.swing.JFormattedTextField urlField;
    private javax.swing.JCheckBox useConnection;
    private javax.swing.JFormattedTextField usernameField;
    // End of variables declaration//GEN-END:variables
    
    private JLabel dynamictext_rapidhelp;
    private JLabel dynamictext_incognitohelp;
    private JLabel dynamictext_statistichelp;
}
