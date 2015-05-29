package org.arps.Grimeron.entity;

import java.awt.Color;
import org.arps.Grimeron.UI.UITile;

/**
 *
 * @author hogansr
 */
public class Tile{
    
    private UITile uiTile;
    
    public enum State {
        OPEN(Color.LIGHT_GRAY),
        OCCUPIED(Color.WHITE),   //Color not acutally used. If the tile turns cyan thats a problem.
        DEAD(Color.BLACK);
        
        private Color color;
        
        private State(Color color){
            this.color = color;
        }
        
        public Color getColor() {return this.color;}
    }
    
    private State state;
    
    private final int gameX;
    private final int gameY;
    
    private Color color;
    
    private boolean wasSelected = false;
    
    /**
     * Tile to be represented by JComponent UI.
     * @param x
     * @param y
     */
    public Tile(int x, int y){
        this.gameX = x;
        this.gameY = y;
        state = State.OPEN;
        this.color = this.state.getColor();
    }
    
    /**
     *
     * @param x
     * @param y
     * @param uiTile
     */
    public Tile(int x, int y, UITile uiTile){
        this.gameX = x;
        this.gameY = y;
        this.uiTile = uiTile;
    }
    
    /**
     * Deep copy constructor for tile. Renders the tile inhibited from the interface. 
     * @param tile
     */
    public Tile(Tile tile){
        this.gameX = tile.getGameX();
        this.gameY = tile.getGameY();
        this.state = tile.getState();
        this.color = tile.getColor();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Get the x of the tile on the grid.
     * @return int X Position
     */
    public int getGameX(){return this.gameX;}

    /**
     * Get the y of the tile on the grid.
     * @return int Y Position
     */
    public int getGameY(){return this.gameY;}
    
    /**
     * Get the state of a tile.
     * @return State Current State
     */
    public State getState(){return this.state;}

    /**
     * Set the state of the tile to a new state.
     * @param newState
     */
    public void setState(State newState) {this.state = newState;}

    public boolean wasSelected() {
        return wasSelected;
    }

    public void setSelected(boolean wasClicked) {
        this.wasSelected = wasClicked;
    }

    public void setUITile(UITile tile){
        this.uiTile = tile;
    }
    
    public UITile getUITile(){
        return uiTile;
    }
    
    @Override
    public boolean equals(Object obj){
        try{
            Tile comparator = (Tile)obj;
            
            if(comparator.getGameX() == this.getGameX() && 
                    comparator.getGameY() == this.getGameY() && 
                    comparator.getState().equals(this.getState())){
                return true;
            }
            
        }catch(ClassCastException ex){
            return false;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return ("(" + this.gameX + "," + this.gameY + ")");
    }
}
