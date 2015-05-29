/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import org.arps.Grimeron.entity.Tile;

/**
 *
 * @author hogansr
 */
public class UITile extends JPanel implements MouseListener{
    
    public Tile tile;

    public UITile(Tile tile, GrimeronGrid grid){
        this.tile = tile;
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(550/grid.getMaxX(), 550/grid.getMaxY()));
        this.setSize(550/grid.getMaxX(), 550/grid.getMaxY());
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(!tile.getState().equals(Tile.State.OCCUPIED)){
            tile.setColor(tile.getState().getColor());
        }
        g.setColor(tile.getColor());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    //
    //  To make sure the sensitivity maintains decent sensitivty,
    //  pseudo clicks are created in case the mouse click listener failed to capture in time.
    //
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        tile.setSelected(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
