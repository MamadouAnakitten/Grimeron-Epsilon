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
import org.arps.Grimeron.UI.Panels.GrimeronGrid;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.entity.Tile;
import org.arps.Grimeron.utils.DBOperationHandler;

/**
 *
 * @author richa_000
 */
public class Bot extends Player{
    public final DBOperationHandler opHandler;
    public int defaultRecursionCount = 10;
    private final GrimeronGrid grid;
    
    //Bot Initialization
    public Bot(Tile tile, String name, DBOperationHandler opHandler, GrimeronGrid grid)
    {
        super(Type.BOT, tile, name);
        this.opHandler = opHandler;
        this.grid = grid;
    }
    
    //Bot Initialization
    public Bot(Tile tile, String name, DBOperationHandler opHandler, GrimeronGrid grid, int defaultRecursionCount)
    {
        super(Type.BOT, tile, name);
        this.opHandler = opHandler;
        this.grid = grid;
        this.defaultRecursionCount = defaultRecursionCount;
    }
    
    @Override
    public Move performMove(ArrayList<Tile> emptySquares, int turn)
    {
        ArrayList<Tile> potentialNextMoves = new ArrayList<>();
        
        float defaultWeight = (float) ((Player.Place.FIRST.getWeight() + Place.NONE.getWeight()) / 2.0);
        float weight = defaultWeight; //copy.
        
        //BOTH MAPS SHOULD BE MADE FROM POTENTIAL LEGAL MOEVS..
        //Weights of possible moves 
        HashMap<Tile, Float> moveWeightValues = new HashMap<>();
        //Potential Pathsize of possible moves.
        ArrayList<TripleSet> tripletAssociation = new ArrayList<>();
      
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
        
        if(potentialNextMoves.size() == 1)
        {
            Move performedMove = null;
        
            //If still alive, moves towards the square it picked
            if(this.isExistant()){
                performedMove = new Move(this, this.getTile(), potentialNextMoves.get(0), turn);
                this.addMoveToMoveHistory(performedMove);
                this.move(potentialNextMoves.get(0));
            }
            
            return performedMove;
        }
        
        Collections.shuffle(potentialNextMoves);
        
        //Fills a hashmap of possible moves and their values in terms of move weight
        for(Tile nextTile: potentialNextMoves)
        {
            if(opHandler != null){
                weight = opHandler.getMoveWeight(this.getTile(), nextTile, turn);
            }
            moveWeightValues.put(nextTile, weight);
        }
        
        ArrayList<Tile> preExclude = new ArrayList<Tile>();
            preExclude.add(this.getTile());
        Iterator it = moveWeightValues.entrySet().iterator();        
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            
            tripletAssociation.add( 
                    new TripleSet((Tile)pair.getKey(),
                    (float)pair.getValue(), 
                    recursiveSearch((Tile)pair.getKey(), 0, preExclude)));
        }
        
        //Start the move decision process.
        if(potentialNextMoves.isEmpty())
        {   
            this.kill();
        }
        else
        {
            Tile bestRatedTile = null;
         
            bestRatedTile = getBestTile(potentialNextMoves);
            
            Move performedMove = null;
        
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
    
    public Tile getBestTile(ArrayList<Tile> potentialTiles)
    {
        // Association between tiles and how many generations are possible from them.
        HashMap<Tile, Integer> tileToGenerations = new HashMap<>();
        
        // A list of pairs that will be retrieved from a future loop.
        ArrayList<Map.Entry> pairs = new ArrayList<>();
        // Retrieved generation values.
        List<Integer> generationValues;
        
        // For every potential tile...
        for(Tile tile: potentialTiles)
        {
            // Put into the map the tile and its potential count.
            tileToGenerations.put(tile, recursiveSearch(tile, 0, new ArrayList<Tile>()));
        }
        
        // Get the values for generations then sort them from highest to lowest..
        generationValues = new ArrayList(tileToGenerations.values());

        Collections.sort(generationValues, new Comparator<Integer>() 
        {
            @Override
            public int compare(Integer int_a, Integer int_b) {
                return int_a.compareTo(int_b);
            }
        }); Collections.reverse(generationValues); 
        
        
        // The highest path is the first in the list.
        int bestGeneration = generationValues.get(0);
        
        ArrayList<Tile> bestTiles = new ArrayList<Tile>();
        
        for(Tile tile: tileToGenerations.keySet())
        {
            if(tileToGenerations.get(tile) == bestGeneration) bestTiles.add(tile);
        }
        
        Collections.shuffle(bestTiles);
        
        //System.out.println("Best Tiles: " + bestTiles);
        //System.out.println("Chose tile " + bestTiles.get(0) + " which had a generation count of " + tileToGenerations.get(bestTiles.get(0)) +
        //        " out of " + generationValues);
        
        return bestTiles.get(0);
    }
    
    //Return the amount of possible moves from <tile>, <recursions> times.
    private int recursiveSearch(Tile tile, int generation, ArrayList<Tile> exclusion)
    {
        exclusion.add(tile);                                  // Add current tile to exclusion.
        int nextGen = 0;                                      // The number of the next generation.
        int highestGen = generation;                          // Highest Generation Reported.
        ArrayList<Tile> foundTiles;                           // The Live Tiles Surrounding 'tile'
        for(int genIncrement = generation; genIncrement<defaultRecursionCount; genIncrement++)            // Increment loop to maximum allowed recursions. (Should never reach that number.)
        {
            foundTiles = grid.getLiveTilesSurrounding(tile);  // Found tiles are the tiles that are alive around 'tile'.

            for(Tile nextTile: exclusion)                     // For every one of the tiles in exclusion...
            {
                if(foundTiles.contains(nextTile))             // If the found tile should be excluded...
                {
                    foundTiles.remove(nextTile);              // Remove the tile from foundTiles.
                }
            }
            
            if(foundTiles.isEmpty())                          // If theres nothing else to find, break from the loop.
            {
                break;
            }
            
            for(Tile nextTile: foundTiles)                    // For every tile that is validly found...
            {                                                 // Get the highest generation/
                nextGen = recursiveSearch(nextTile, generation + 1, exclusion);
                if(nextGen > highestGen) highestGen = nextGen;
            }  
        }
        return highestGen;                                    // Return it.
    }

    public int getDefaultRecursionCount() {
        return defaultRecursionCount;
    }

    public void setDefaultRecursionCount(int defaultRecursionCount) {
        this.defaultRecursionCount = defaultRecursionCount;
    }
    
    private class TripleSet
    {
        public final Tile tile;
        public final float weight;
        public final int generations;
        
        public TripleSet(Tile tile, float weight, int generations){
            this.tile = tile;
            this.weight = weight;
            this.generations = generations;
        }
    }
}
