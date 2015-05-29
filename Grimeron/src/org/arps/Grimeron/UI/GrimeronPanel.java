/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.UI;

import org.arps.Grimeron.Move;

/**
 *
 * @author richa_000
 */
public class GrimeronPanel extends javax.swing.JPanel {

    private final GrimeronGrid retrievedGrid;
    private int round = 0;
    private Move lastMove = null;
    
    /**
     * Creates new form GrimeronFrame
     * @param grid
     */
    public GrimeronPanel(GrimeronGrid grid) {
        retrievedGrid = grid;
        initComponents();
    }
    
    public GrimeronPanel(){
        retrievedGrid = new GrimeronGrid();
        initComponents();
    }
    
    public void updateInformation(int newRound, Move nextMove){
        this.round = newRound;
        this.lastMove = nextMove;
        roundText.setText(Integer.toString(round));
        if(lastMove != null){
            String moveDescription = lastMove.toString();
            moveDataText.setText(moveDescription);
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

        infoPanel = new javax.swing.JPanel();
        subjectRound = new javax.swing.JLabel();
        roundText = new javax.swing.JLabel();
        subjectMove = new javax.swing.JLabel();
        moveDataText = new javax.swing.JLabel();
        grimeronGrid = retrievedGrid;

        setBackground(new java.awt.Color(102, 102, 102));

        infoPanel.setBackground(new java.awt.Color(232, 232, 255));
        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        subjectRound.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        subjectRound.setText("Round Number:");

        roundText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        roundText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roundText.setText("0");
        roundText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        subjectMove.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        subjectMove.setText("Last Move: ");

        moveDataText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        moveDataText.setText("Move Data Will Show Here");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subjectRound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectMove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moveDataText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectRound)
                    .addComponent(roundText)
                    .addComponent(subjectMove)
                    .addComponent(moveDataText))
                .addContainerGap())
        );

        grimeronGrid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(grimeronGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grimeronGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.arps.Grimeron.UI.GrimeronGrid grimeronGrid;
    private javax.swing.JPanel infoPanel;
    public javax.swing.JLabel moveDataText;
    private javax.swing.JLabel roundText;
    private javax.swing.JLabel subjectMove;
    private javax.swing.JLabel subjectRound;
    // End of variables declaration//GEN-END:variables

    public GrimeronGrid getGrid() {
        return grimeronGrid;
    }

    public void setGrid(GrimeronGrid grimeronGrid) {
        this.grimeronGrid = grimeronGrid;
    }
    
}
