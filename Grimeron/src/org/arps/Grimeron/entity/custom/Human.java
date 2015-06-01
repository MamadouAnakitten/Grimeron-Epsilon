package org.arps.Grimeron.entity.custom;


import java.util.ArrayList;
import org.arps.Grimeron.Move;
import org.arps.Grimeron.UI.GrimeronGrid;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.entity.Tile;

/**
 * Human player with JComponent UI. 
 * @author Julian Clark
 */
public class Human extends Player{
  
    private GrimeronGrid grid;
    
    public Human(Tile tile, String name, GrimeronGrid grid){
        super(Type.HUMAN, tile, name);
        this.grid = grid;
    }
  
    @Override
    public Move performMove(ArrayList<Tile> emptySquares, int turn){
        Move performedMove = null;
        
        boolean moveMade = false;
        
        Tile selectedTile = null;
        int xDif, yDif;
        
        if(!this.isExistant()){
            moveMade = true;
            grid.dumpTileClicks();
        }
        
        while(!moveMade){
            grid.checkForTileClicks();
            
            selectedTile = grid.getLastClicked();
            
            //Check to see that the player selected a tile.
            if(selectedTile != null && !selectedTile.getState().equals(Tile.State.OCCUPIED)){
                
                xDif = this.getTile().getGameX()-selectedTile.getGameX();
                yDif = this.getTile().getGameY()-selectedTile.getGameY();
                
                //If the tile is within range..
                if(Math.abs(xDif) <= 1 && Math.abs(yDif) <= 1){
                    
                    //If the player killed themselves.
                    if(selectedTile.getState().equals(Tile.State.DEAD)){
                        this.kill();
                    }
                    
                    
                    performedMove = new Move(this, this.getTile(), selectedTile, turn);
                    this.addMoveToMoveHistory(performedMove);
                    this.move(selectedTile);

                    moveMade = true;
                } 
            }
        }
        grid.dumpTileClicks();
        return performedMove;
    }
}