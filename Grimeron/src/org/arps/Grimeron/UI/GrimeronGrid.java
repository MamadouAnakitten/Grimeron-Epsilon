
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.UI;

import org.arps.Grimeron.entity.Player;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import org.arps.Grimeron.entity.Tile;
import org.arps.Grimeron.entity.Tile.State;
import org.arps.Grimeron.utils.RuleSet;

/**
 * A JPanel component that creates a x by y grid of Tiles. 
 * @author Richard Hogans
 */
public class GrimeronGrid extends JPanel {
    
    private final int maxX; //Maximum x.
    private final int maxY; //Maximum y.
    
    private ArrayList<Tile> tiles = new ArrayList<>();
    
    private ArrayList<Tile> clickedTiles = new ArrayList<>();
    /**
     * Creates new form GameGrid
     */
    public GrimeronGrid() {
        this.maxX = 11;
        this.maxY = 11;
        initComponents();
    }
    
    public GrimeronGrid(int x, int y){
        
        if(x < 4 && y < 4){
            x = 4;
            y = 4;
        }
        
        this.maxX = Math.abs(x);
        this.maxY = Math.abs(y);
        
        initComponents();
    }
    
    private void initComponents(){
        setLayout(new GridBagLayout());                             //Set grid layout.
        GridBagConstraints constraints = new GridBagConstraints();  //Create constraints.
        for(int x=0; x<maxX; x++){                                  //For x by y...
            for(int y=0; y<maxY; y++){
                constraints.gridx = x;                              //Set the current constraint x.
                constraints.gridy = y;                              //Set the current constraint y.
                
                Tile newTile = new Tile(x, y);
                
                UITile tilePanel = new UITile(newTile, this);
                
                newTile.setUITile(tilePanel);
                
                //Make a new tile at x, y.
                newTile.setState(State.OPEN);
                MatteBorder border = null;                          //Give it a matte border.
                
                if(x < (maxX-1)){                                   //Processing for the border...
                    if(y < (maxY-1)){
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    }else{
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if(y < (maxY-1)){
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else { 
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                tilePanel.setBorder(border);    //Set the border for the piece.
                add(tilePanel, constraints);    //Add the piece with constraints to the grid panel.
                tiles.add(newTile);           //Add the piece to the tile array.
            }
        }
    }

    /**
     * Updates the graphics for every tile in the grid. Then repaints the grid as a whole.
     */
    public void updateGraphics(RuleSet rules){
        //System.out.println("Update called with playercount: " + playerList.size());
        for(Tile tile: tiles){
            tile.setColor(tile.getState().getColor());
            tile.getUITile().repaint();
        }
        for(Player player: rules.getPlayers()){
            player.getTile().setState(State.OCCUPIED);
            player.getTile().setColor(player.getColor());
            player.getTile().getUITile().repaint();
        }
        this.repaint();
    }
    
    /**
     * Returns a shallow copy of every piece in the grid.
     * @return ArrayList<Tile> Tiles.
     */
    public ArrayList<Tile> getPieces() {
        return tiles;
    }
    
    /**
     * Returns the top left tile of the grid.
     * @return Tile Top left tile
     */
    public Tile getTopLeft(){
        for(Tile tile: tiles){
            if(tile.getGameX() == 0 && tile.getGameY() == 0){
                return tile;
            }
        } 
        return null;
    }
    
    /**
     * Returns the top right tile of the grid.
     * @return Tile Top rght tile
     */
    public Tile getTopRight(){
        for(Tile tile: tiles){
            if(tile.getGameX() == 0 && tile.getGameY() == maxY-1){
                return tile;
            }
        } 
        return null;
    }
    
    /**
     * Returns the bottom left tile in the grid.
     * @return Tile Bottom left tile
     */
    public Tile getBottomLeft(){
        for(Tile tile: tiles){
            if(tile.getGameX() == maxX-1 && tile.getGameY() == 0){
                return tile;
            }
        } 
        return null;
    }
    
    /**
     * Returns the bottom right tile of the grid.
     * @return Tile Bottom right tile
     */
    public Tile getBottomRight(){
        for(Tile tile: tiles){
            if(tile.getGameX() == maxX-1 && tile.getGameY() == maxY-1){
                return tile;
            }
        } 
        return null;
    }
    
    /**
     * Returns the tile at a given x and y and will return null if there is no present tile at that position.
     * @param x
     * @param y
     * @return Tile Tile at position (x, y)
     */
    public Tile getTileAt(int x, int y){
        for(Tile tile: tiles){
            if(tile.getGameX() == x && tile.getGameY() == y){
                return tile;
            }
        } 
        return null;
    }
    
    /**
     * Returns a list of live/open tiles.
     * @return ArrayList<Tile> Open Tiles
     */
    public ArrayList<Tile> getLiveTiles(){
        ArrayList<Tile> emptyTiles = new ArrayList<>();
        
        for(Tile tile: tiles){
            if(tile.getState().equals(State.OPEN)){
                emptyTiles.add(tile);
            }
        }
        
        return emptyTiles;
    }
    
    /**
     * Returns an integral value of the max X.
     * @return int maxX
     */
    public int getMaxX(){
        return this.maxX;
    }
    
    /**
     * Returns an integral value of the max Y.
     * @return int maxY
     */
    public int getMaxY(){
        return this.maxY;
    }
    
    /**
     * Returns true if the object given is a grid that contains all the same tile states.
     * This method does not distinguish players from occupied tiles. Occupied grid tiles are
     * not bound by specific players.
     * @param grid
     * @return boolean True if the given object is a grid with the same tileset.
     */
    @Override
    public boolean equals(Object grid){
        try{
            GrimeronGrid nGrid = (GrimeronGrid)grid;
            
            if(nGrid.getPieces().equals(this.tiles) && nGrid.getMaxX() == this.getMaxX() && nGrid.getMaxY() == this.getMaxY()){
                return true;
            }
            
        }catch(ClassCastException ex){
            return false;
        }catch(NullPointerException ex){  
            System.err.println("Null Pointer Exception at GrimeronGrid.equals() ... "
                    + "\n Check that both grids are not null..."
                    + "\n Check that no tiles in the grids are null... "
                    + "\n Check that the maximum x and y are not null...");
            return false;
        }
        
        return false;
    }

    public void reset() {
        for(Tile tile: tiles){
            tile.setState(State.OPEN);
        }
    }
    
    /**
     * Dump all tile clicks and reset tile clicks to false.
     */
    public void dumpTileClicks(){
        for(Tile tile: tiles){
            tile.setSelected(false);
        }
        clickedTiles.clear();
    }
    
    /**
     * Checks for tile clicks. Use this in a loop to keep updating tile clicks. 
     */
    public void checkForTileClicks(){
        for(Tile tile: tiles){
            if(tile.wasSelected() && !clickedTiles.contains(tile)){
                clickedTiles.add(tile);
            }
        }
    }
    
    /**
     * Returns the last clicked tile and then dumps all tile clicks. Returns null if there was no tile clicked.
     * @return
     */
    public Tile getLastClicked(){
        if(clickedTiles.size() > 0){
            Tile lastClickedTile = clickedTiles.get(clickedTiles.size()-1);
            return lastClickedTile;
        }
        return null;
    }
    
    public ArrayList<Tile> getTilesSurrounding(Tile tile){
        ArrayList<Tile> surroundingTiles = new ArrayList<>();
        
        int tileX = tile.getGameX(), tileY = tile.getGameY();
        Tile nextTile;
        //North y-1
            nextTile = this.getTileAt(tileX, tileY-1);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //North East x+1 y-1
            nextTile = this.getTileAt(tileX+1, tileY-1);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //East x+1
            nextTile = this.getTileAt(tileX+1, tileY);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //South East x+1 y+1
            nextTile = this.getTileAt(tileX+1, tileY+1);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //South y+1
            nextTile = this.getTileAt(tileX, tileY+1);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //South West x-1 y+1
            nextTile = this.getTileAt(tileX-1, tileY+1);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //West x-1
            nextTile = this.getTileAt(tileX+1, tileY);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
        //North West x-1 y-1
            nextTile = this.getTileAt(tileX-1, tileY-1);
            if(nextTile != null){
                surroundingTiles.add(nextTile);
            }
            
            
        
        return surroundingTiles;
    }
    
    public ArrayList<Tile> getLiveTilesSurrounding(Tile tile){
        ArrayList<Tile> surroundingTiles = this.getTilesSurrounding(tile);
        ArrayList<Tile> liveSurroundingTiles = new ArrayList<>();
        for(Tile nextTile: surroundingTiles){
            if(!nextTile.getState().equals(Tile.State.OPEN)){
                liveSurroundingTiles.remove(nextTile);
            }
        }
        
        return surroundingTiles;
    }
}
