/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.entity.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.arps.Grimeron.Move;
import org.arps.Grimeron.UI.GrimeronGrid;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.entity.Tile;
import org.arps.Grimeron.entity.custom.bot.EquationSet;
import org.arps.Grimeron.utils.DBOperationHandler;

/**
 *
 * @author richa_000
 */
public class NBot extends Player{
    // Deals with the Database connections
    public final DBOperationHandler opHandler;
    public int defaultRecursionCount = 6;
    public double decisionBuffer = 2;
    private final GrimeronGrid grid;
    
    //Bot Initialization
    public NBot(Tile tile, String name, DBOperationHandler opHandler, GrimeronGrid grid)
    {
        super(Player.Type.BOT, tile, name);
        this.opHandler = opHandler;
        this.grid = grid;
    }
    
    public Move performMove(ArrayList<Tile> emptySquares, int turn)
    {
        ArrayList<EquationSet> potentialSearchResults;
        ArrayList<Tile> potentialNextMoves = new ArrayList<>();
        
        double defaultWeight = (Player.Place.FIRST.getWeight() + Player.Place.NONE.getWeight()) / 2.0;
        double weight = new Double(defaultWeight); //Deep copy.
        

        //Narrow next moves to 8 moves.
        for(Tile eachTile: emptySquares)
        {
            int xDif = eachTile.getGameX() - this.getTile().getGameX();
            int yDif = eachTile.getGameY() - this.getTile().getGameY();
            if(Math.abs(xDif) <= 1 && Math.abs(yDif) <= 1)
            {
                if(!(xDif == 0 && yDif == 0))
                {
                    potentialNextMoves.add(eachTile);
                }    
            }
        }
        
        Collections.shuffle(potentialNextMoves);

        //Fills a hashmap of possible moves and their values in terms of move weight
        for(Tile nextTile: potentialNextMoves)
        {
            if(opHandler != null){
                nextTile.weight = opHandler.getMoveWeight(this.getTile(), nextTile, turn);
            }
        }
        
        //Start the move decision process.
        if(potentialNextMoves.isEmpty())
        {    
            this.kill();
        }
        else
        {
            
            Tile bestRatedTile = null;
            Move performedMove = null;
        
            potentialSearchResults = new ArrayList<>();
            
            for(Tile nextTile: potentialNextMoves)
            {
                potentialSearchResults.add(recursiveSearch(nextTile, defaultRecursionCount, new ArrayList<>()));
            }
            
            //If still alive, moves towards the square it picked
            if(this.isExistant()){
                performedMove = new Move(this, this.getTile(), bestRatedTile, turn);
                this.addMoveToMoveHistory(performedMove);
                this.move(bestRatedTile);
            }
            
            return performedMove;
        }
        return null;
    }
    
    //Return the amount of possible moves from <tile>, <recursions> times.
    private EquationSet recursiveSearch(Tile tile, int recursions, ArrayList<Tile> exclusion)
    {
        EquationSet newSet = new EquationSet();
        ArrayList<Tile> foundTiles;
        for(int loop=0; loop < recursions; loop++)
        {
            foundTiles = grid.getTilesSurrounding(tile);
            for(Tile nextTile: foundTiles)
            {
                if(!exclusion.contains(nextTile))
                {
                    switch(nextTile.getState())
                    {
                        case OPEN:
                            newSet.setPathsize(newSet.getPathsize() + 1);
                            break;
                        case OCCUPIED:
                            newSet.setEnemiesOnPath(newSet.getEnemiesOnPath() + 1);
                            break;
                    }
                }
            }
        }
        return newSet;
    }
}
