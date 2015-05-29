/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.UI;

import java.awt.Color;
import java.util.Iterator;
import org.arps.Grimeron.Grimeron;
import org.arps.Grimeron.entity.custom.Human;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.entity.Tile;
import org.arps.Grimeron.entity.custom.Bot;
import org.arps.Grimeron.utils.DBOperationHandler;
import org.arps.Grimeron.utils.RuleSet;

/**
 * A frame and form for selecting game features and setting up players.
 * @author Richard Hogans
 */
public class GrimeronSetupFrame extends javax.swing.JFrame {
    
    private Grimeron game;
    
    private GrimeronGrid grid;
    
    private RuleSet ruleSet;
    private DBOperationHandler dbHandler;
    
    public volatile boolean isSetup = false;
    
    /**
     * Creates new setup frame and hands options over to a given ruleSet.
     * @param grid
     * @param ruleSet
     * @param opHandler
     */
    public GrimeronSetupFrame(Grimeron game) {
        this.game = game;
        this.grid = game.getGameFrame().getGrid();
        this.ruleSet = game.getRuleSet();
        this.dbHandler = game.getDBHandler();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        setupText = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        incognitoCheckBox = new javax.swing.JCheckBox();
        rapidModeText = new javax.swing.JLabel();
        rapidCheckbox = new javax.swing.JCheckBox();
        statisticModeSelection = new javax.swing.JCheckBox();
        submitGameSetupBtn = new javax.swing.JButton();
        gameCountInput = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        registeredPlayerCombo = new javax.swing.JComboBox();
        createdPlayersText = new javax.swing.JLabel();
        pTypeSubText = new javax.swing.JLabel();
        pStartLocSubText = new javax.swing.JLabel();
        pColorSubText = new javax.swing.JLabel();
        pTypeText = new javax.swing.JLabel();
        pStartLocText = new javax.swing.JLabel();
        pColorPanel = new javax.swing.JPanel();
        playerRegButton = new javax.swing.JButton();
        editPlayerButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        graphicOverrideEnabled = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        databasePreloader = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        databaseName = new javax.swing.JTextField();
        databasePort = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        customDBToggle = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        databaseUsername = new javax.swing.JTextField();
        databasePassword = new javax.swing.JPasswordField();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        setupText.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        setupText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setupText.setText("Grimeron Game Setup");
        setupText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        incognitoCheckBox.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        incognitoCheckBox.setText("Incognito Mode: Disables database learning and keeps AI knowledge constant.");
        incognitoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incognitoCheckBoxActionPerformed(evt);
            }
        });

        rapidModeText.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rapidModeText.setText("Rapid Mode: Causes game to run without graphics for a specified number of games. (Only compatible with bot-only games.)");

        rapidCheckbox.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rapidCheckbox.setText("Enable Rapid Mode");
        rapidCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rapidCheckboxActionPerformed(evt);
            }
        });

        statisticModeSelection.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        statisticModeSelection.setText("Statistic Mode: (Requires Rapid Mode) Generates statistics chart alongside simulated games.");
        statisticModeSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticModeSelectionActionPerformed(evt);
            }
        });

        submitGameSetupBtn.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        submitGameSetupBtn.setText("Play Game");
        submitGameSetupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitGameSetupBtnActionPerformed(evt);
            }
        });

        gameCountInput.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gameCountInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gameCountInput.setText("Enter Game Count");
        gameCountInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameCountInputActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Player Creator", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        registeredPlayerCombo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registeredPlayerCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empty", " " }));
        registeredPlayerCombo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        registeredPlayerCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registeredPlayerComboActionPerformed(evt);
            }
        });

        createdPlayersText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        createdPlayersText.setText("Created Players");

        pTypeSubText.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        pTypeSubText.setText("Player Type");

        pStartLocSubText.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        pStartLocSubText.setText("Player Starting Location");

        pColorSubText.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        pColorSubText.setText("Player Color");

        pTypeText.setText("None Yet.");

        pStartLocText.setText("None Yet");

        pColorPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pColorPanelLayout = new javax.swing.GroupLayout(pColorPanel);
        pColorPanel.setLayout(pColorPanelLayout);
        pColorPanelLayout.setHorizontalGroup(
            pColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        pColorPanelLayout.setVerticalGroup(
            pColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        playerRegButton.setText("Register New Player");
        playerRegButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerRegButtonActionPerformed(evt);
            }
        });

        editPlayerButton.setText("Edit Selected Player");
        editPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlayerButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete Selected Player");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registeredPlayerCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pTypeSubText)
                                .addGap(119, 119, 119)
                                .addComponent(pTypeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(createdPlayersText)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pStartLocSubText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pStartLocText))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pColorSubText)
                                .addGap(117, 117, 117)
                                .addComponent(pColorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(playerRegButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editPlayerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(createdPlayersText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registeredPlayerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pTypeSubText)
                    .addComponent(pTypeText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pStartLocSubText)
                    .addComponent(pStartLocText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pColorSubText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pColorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerRegButton)
                            .addComponent(editPlayerButton)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(deleteButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        graphicOverrideEnabled.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        graphicOverrideEnabled.setText("Enable Rapid Graphics Override (Show Graphics)");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Advanced Settings"));

        databasePreloader.setText("Preload Database");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Custom Database Settings");

        jLabel2.setText("Host / IP :");

        jLabel3.setText("Port");

        jLabel4.setText("Database Name");

        databasePort.setText("3306");

        customDBToggle.setText("Toggle: Use the custom database.");

        jLabel5.setText("Username");

        jLabel6.setText("Password");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(databasePreloader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(databaseName))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(79, 79, 79)
                        .addComponent(databasePort))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customDBToggle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(databasePassword)
                            .addComponent(databaseUsername))))
                .addGap(273, 273, 273))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databasePreloader)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(databaseUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(databasePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(databasePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(databaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customDBToggle)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitGameSetupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rapidModeText)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rapidCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gameCountInput, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(graphicOverrideEnabled))
                            .addComponent(incognitoCheckBox)
                            .addComponent(statisticModeSelection))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rapidModeText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rapidCheckbox)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gameCountInput)
                            .addComponent(graphicOverrideEnabled))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(incognitoCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statisticModeSelection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitGameSetupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setupText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(setupText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void statisticModeSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticModeSelectionActionPerformed
        this.ruleSet.statisticMode = statisticModeSelection.isSelected();
    }//GEN-LAST:event_statisticModeSelectionActionPerformed

    private void incognitoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incognitoCheckBoxActionPerformed
        this.ruleSet.incognitoMode = incognitoCheckBox.isSelected();
    }//GEN-LAST:event_incognitoCheckBoxActionPerformed

    private void submitGameSetupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitGameSetupBtnActionPerformed
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
        verify();
        isSetup = true;
    }//GEN-LAST:event_submitGameSetupBtnActionPerformed

    private void rapidCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rapidCheckboxActionPerformed
        this.ruleSet.rapidMode = rapidCheckbox.isSelected();
    }//GEN-LAST:event_rapidCheckboxActionPerformed

    private void playerRegButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerRegButtonActionPerformed
        GrimeronPlayerCreator newPlayerCreator = new GrimeronPlayerCreator(game, this);
        newPlayerCreator.setVisible(true);
    }//GEN-LAST:event_playerRegButtonActionPerformed

    private void registeredPlayerComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registeredPlayerComboActionPerformed

        try
        {
            Player selectedPlayer = (Player)registeredPlayerCombo.getSelectedItem();
        
            pStartLocText.setText(selectedPlayer.getTile().toString());
    
            pTypeText.setText(selectedPlayer.getType().getDescription());
            
            pColorPanel.setBackground(selectedPlayer.getColor());
            
        }catch(NullPointerException | ClassCastException ex){}
    }//GEN-LAST:event_registeredPlayerComboActionPerformed

    private void gameCountInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameCountInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gameCountInputActionPerformed

    private void editPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlayerButtonActionPerformed
        
        if(registeredPlayerCombo.getSelectedItem() == null){
            return;
        }
        
        try
        {
            Player selectedPlayer = (Player)registeredPlayerCombo.getSelectedItem();
            
            selectedPlayer.getTile().setState(Tile.State.OPEN);
            selectedPlayer.getStartingTile().setState(Tile.State.OPEN);
            
            GrimeronPlayerCreator newPlayerCreator = new GrimeronPlayerCreator(game, this);
            newPlayerCreator.setVisible(true);
            
            if(selectedPlayer.getType().equals(Player.Type.HUMAN))
            {
                newPlayerCreator.humanRadioButton.setSelected(true);
            } else { 
                newPlayerCreator.botRadioButton.setSelected(true);
            }
            
            newPlayerCreator.playerNameSelector.setText(selectedPlayer.name);
            
            newPlayerCreator.playerColorSelector.setColor(selectedPlayer.getColor());
            
            this.registeredPlayerCombo.removeItem(registeredPlayerCombo.getSelectedItem());
            
            ruleSet.getPlayers().remove(selectedPlayer);
            
        }catch(ClassCastException | NullPointerException ex){}
    }//GEN-LAST:event_editPlayerButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try{
            Player selectedPlayer = (Player)registeredPlayerCombo.getSelectedItem();
            
            selectedPlayer.getTile().setState(Tile.State.OPEN);
            selectedPlayer.getStartingTile().setState(Tile.State.OPEN);
            
            ruleSet.getPlayers().remove((Player)registeredPlayerCombo.getSelectedItem());        
            registeredPlayerCombo.removeItem(registeredPlayerCombo.getSelectedItem());
            
            pColorPanel.setBackground(Color.LIGHT_GRAY);
            pStartLocText.setText("None");
            pTypeText.setText("None");
        } catch (Exception ex) {
            return;
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel createdPlayersText;
    private javax.swing.JToggleButton customDBToggle;
    private javax.swing.JTextField databaseName;
    private javax.swing.JPasswordField databasePassword;
    private javax.swing.JTextField databasePort;
    private javax.swing.JCheckBox databasePreloader;
    private javax.swing.JTextField databaseUsername;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editPlayerButton;
    private javax.swing.JTextField gameCountInput;
    private javax.swing.JCheckBox graphicOverrideEnabled;
    private javax.swing.JCheckBox incognitoCheckBox;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel pColorPanel;
    private javax.swing.JLabel pColorSubText;
    private javax.swing.JLabel pStartLocSubText;
    private javax.swing.JLabel pStartLocText;
    private javax.swing.JLabel pTypeSubText;
    private javax.swing.JLabel pTypeText;
    private javax.swing.JButton playerRegButton;
    private javax.swing.JCheckBox rapidCheckbox;
    private javax.swing.JLabel rapidModeText;
    private javax.swing.JComboBox registeredPlayerCombo;
    private javax.swing.JLabel setupText;
    private javax.swing.JCheckBox statisticModeSelection;
    private javax.swing.JButton submitGameSetupBtn;
    // End of variables declaration//GEN-END:variables

    public boolean isSetup()
    {
        return isSetup;
    }
    
    public javax.swing.JComboBox getRegisteredPlayerCombo ()
    {
        return this.registeredPlayerCombo;
    }
    
    private void verify()
    {       
        if(ruleSet.getPlayers().size() == 1)
        {
            Bot a = new Bot(grid.getBottomRight(), "Red", dbHandler, grid);
            
            ruleSet.addPlayer(a);
            
            ruleSet.getPlayers().get(0).getTile().setState(Tile.State.OPEN);
            ruleSet.getPlayers().get(0).setStartingTile(grid.getTopLeft());
            ruleSet.getPlayers().get(0).setTile(grid.getTopLeft());
        }
        
        if(ruleSet.rapidMode)
        {
            for (Iterator<Player> it = ruleSet.getPlayers().iterator(); it.hasNext();) 
            {
                Player player = it.next();
                if(player.getType().equals(Player.Type.HUMAN))
                {
                    it.remove();
                }
            }
            
            if(ruleSet.getPlayers().size() < 2)
            {
                ruleSet.getPlayers().clear();
                Bot a = new Bot(game.getGameFrame().getGamePanel().getGrid().getTopLeft(), "Red", dbHandler, grid); //Top Left
                Bot b = new Bot(game.getGameFrame().getGamePanel().getGrid().getTopRight(), "Orange", dbHandler, grid); //Top Right
                Bot c = new Bot(game.getGameFrame().getGamePanel().getGrid().getBottomLeft(), "Yellow", dbHandler, grid); //Bottom Left
                Bot d = new Bot(game.getGameFrame().getGamePanel().getGrid().getBottomRight(), "Blue", dbHandler, grid); //Botton Right
                
                a.setColor(Color.RED);
                b.setColor(Color.ORANGE);
                c.setColor(Color.YELLOW);
                d.setColor(Color.BLUE);
        
                ruleSet.assignPlayers(a, b, c, d);
            }
        }
        
        if(!ruleSet.rapidMode)
        {
            if(ruleSet.getPlayers().size() < 2)
            {
                ruleSet.getPlayers().clear();
                Bot a = new Bot(game.getGameFrame().getGamePanel().getGrid().getTopLeft(), "Red", dbHandler, grid); //Top Left
                Bot b = new Bot(game.getGameFrame().getGamePanel().getGrid().getTopRight(), "Orange", dbHandler, grid); //Top Right
                Bot c = new Bot(game.getGameFrame().getGamePanel().getGrid().getBottomLeft(), "Yellow", dbHandler, grid); //Bottom Left
                Human d = new Human(game.getGameFrame().getGamePanel().getGrid().getBottomRight(), "Blue", grid); //Bottom Right
                
                a.setColor(Color.RED);
                b.setColor(Color.ORANGE);
                c.setColor(Color.YELLOW);
                d.setColor(Color.BLUE);
        
                ruleSet.assignPlayers(a, b, c, d);
            }
        }
    }
}
