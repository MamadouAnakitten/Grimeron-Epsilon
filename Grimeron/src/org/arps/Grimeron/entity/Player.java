//Used for Human and Bot classes

package org.arps.Grimeron.entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import org.arps.Grimeron.Move;
import org.arps.Grimeron.UI.GrimeronGrid;

/**
 * Abstract class outlining what a player will have.
 * @author Mostly Julian Clark / A Few Methods From Richard Hogans
 */
public abstract class Player{
  
    /**
     * This defines whether the player is a bot or human.
     */
    public enum Type{
        BOT("Bot"),
        HUMAN("Person");
        
        private String description;
        
        private Type(String description){
            this.description = description;
        }
        
        public String getDescription(){
            return this.description;
        }
    }
    
    /**
     * Defines the different places a player can be in and values can be changed to assign different weights if using a database and learning.
     */
    public enum Place{ 
        FIRST(1), //Change the value to change the weight that gets put into the database.
        SECOND(2),//This is NOT assigning an index-like value. 
        THIRD(3),
        FOURTH(4),
        NONE(5);
        
        float weight;
        
        private Place(float weight){
            this.weight = weight;
        }
        public float getWeight(){
            return this.weight;
        }
    }
    
    private ArrayList<Move> moveHistory = new ArrayList<>();
    
    private Place place;
  
    private Type type;
    
    private Tile tile;
    private Tile startingTile;
    
    private boolean existant;
    
    private Color color;

    public String name;
    
    /**
     * Create a player given a starting tile and type.
     * @param type
     * @param tile
     * @param name
     */
    public Player(Type type, Tile tile, String name){
        this.tile = tile;
        this.startingTile = new Tile(tile.getGameX(), tile.getGameY());
        this.type = type;
        this.name = name;
        existant = true;
        this.setRandomColor();
    }
    
    /**
     * Deep copy of player. 
     * @param player
     */
    public Player(Player player){
        this.tile = player.getStartingTile();
        this.startingTile = player.getStartingTile();
        this.type = player.getType();
        this.name = player.name;
        existant = true;
        this.setRandomColor();
    }
  
    /**
     * Returns the type of player that this player is.
     * @return Type Player type
     */
    public Type getType(){
        return this.type;
    }
  
    /**
     * Returns the tile that the player is on.
     * @return Tile Occupied tile
     */
    public Tile getTile(){
        return this.tile;
    }
    
    /**
     * Returns true if the player is not dead.
     * @return boolean True if alive.
     */
    public boolean isExistant(){
        return this.existant;
    }
    
    public void setExistant(boolean existant){
        this.existant = existant;
    }
  
    /**
     * Distinguished from subclass move operations, this will take the old tile and assign it to dead, 
     * set the new tile, and set the new tile state to occupied.
     * @param toTile
     */
    public void move(Tile toTile){
        //System.out.println("Player Moved...");
        this.tile.setState(Tile.State.DEAD);
        this.tile = toTile;
        toTile.setState(Tile.State.OCCUPIED);
        toTile.setColor(this.getColor());
    }
    
    public Move performMove(ArrayList<Tile> emptyTiles, int turn){
        return null;
    }
  
    /**
     * Kill this player......
     */
    public void kill(){
        this.existant = false;
    }
    
    /**
     * Define what place this player is in.
     * @param place
     */
    public void setPlace(Place place){
        this.place = place;
    }
    
    /**
     * Returns the place this player is in.
     * @return Place place of the player.
     */
    public Place getPlace(){
        return this.place;
    }
    
    /**
     * Returns the color of the player.
     * @return Color Player color
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * Manually set the awt color of the player.
     * @param color
     */
    public void setColor(Color color){
        this.color = color;
        this.tile.setColor(color);
    }
    
    /**
     * Randomly assign the player an rgb color.
     */
    public void setRandomColor(){
        Random random = new Random();
        
        int r, g, b;
        r = random.nextInt(225);
        g = random.nextInt(225);
        b = random.nextInt(225);
        
        this.color = new Color(r, g, b);
    }
    
    public ArrayList<Move> getMoveHistory(){
        return moveHistory;
    }
    
    public void addMoveToMoveHistory(Move move){
        moveHistory.add(move);
    }  
    
    public void purgeMoveHistory(){
        moveHistory.clear();
    }
    
    public boolean putSelfInGrid(GrimeronGrid grid){
        try{
            int x, y;
            Tile newTile;
         
            x = tile.getGameX();
            y = tile.getGameY();
            
            newTile = grid.getTileAt(x, y);
            
            this.tile = newTile;
            this.startingTile = grid.getTileAt(startingTile.getGameX(), startingTile.getGameY());
            
            return true;
        }catch(Exception e){
            return false;
        }
    }    

    public void setTile(Tile tile) {
        this.tile = tile;
        tile.setState(Tile.State.OCCUPIED);
    }

    public void setStartingTile(Tile startingTile) {
        this.startingTile = startingTile;
    }

    public Tile getStartingTile() {
        return startingTile;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}

  
  