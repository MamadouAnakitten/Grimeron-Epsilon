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
import org.arps.Grimeron.utils.DBOperationHandler;

/**
 *
 * @author richa_000
 */
public class Bot extends Player{
    // Deals with the Database connections
    public final DBOperationHandler opHandler;
    public int defaultRecursionCount = 6;
    public double decisionBuffer = 2;
    private final GrimeronGrid grid;
    
    //Bot Initialization
    public Bot(Tile tile, String name, DBOperationHandler opHandler, GrimeronGrid grid)
    {
        super(Type.BOT, tile, name);
        this.opHandler = opHandler;
        this.grid = grid;
    }
    
    public Move performMove(ArrayList<Tile> emptySquares, int turn)
    {
        ArrayList<Tile> potentialNextMoves = new ArrayList<>();
        ArrayList<Tile> unratedMoves = new ArrayList<>();
        
        Tile chosenTile;
        
        double defaultWeight = (Player.Place.FIRST.getWeight() + Place.NONE.getWeight()) / 2.0;
        double weight = new Double(defaultWeight); //Deep copy.
        
        //BOTH MAPS SHOULD BE MADE FROM POTENTIAL LEGAL MOEVS..
        //Weights of possible moves 
        HashMap<Tile, Double> moveWeightValues = new HashMap<>();
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
        
        Collections.shuffle(potentialNextMoves);

        //Fills a hashmap of possible moves and their values in terms of move weight
        for(Tile nextTile: potentialNextMoves)
        {
            if(opHandler != null){
                weight = opHandler.getMoveWeight(this.getTile(), nextTile, turn);
            }
            moveWeightValues.put(nextTile, weight);
        }
        
        Iterator it = moveWeightValues.entrySet().iterator();        
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            tripletAssociation.add( new TripleSet((Tile)pair.getKey(), (Double)pair.getValue(), recursiveSearch((Tile)pair.getKey(), defaultRecursionCount, new ArrayList<>())));
        }
        
        //Start the move decision process.
        if(potentialNextMoves.isEmpty())
        {   
            this.kill();
        }
        else
        {
            Tile bestRatedTile = null;
         
            //Start with the equations.
            // f(x,y,z) = x/(y/z) where x = weight, y = potential moves count, z = recursionCount
            //Variables being used: moveWeightValues, pathsizeValues, potentialMoves, defaultRecursionCount
            bestRatedTile = retrieveBestTileFromEquation(tripletAssociation);
            
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
    
    private Tile retrieveBestTileFromEquation(ArrayList<TripleSet> moves)
    {
        HashMap<TripleSet, Double> f_of_x = new HashMap<>();
        
        for(TripleSet move: moves)
        {
            f_of_x.put(move, evaluate(move.weight, move.pathsize, defaultRecursionCount));
        }
        
        List<Double> results = new ArrayList(f_of_x.values());
        Collections.sort(results, new Comparator<Double>() 
        {
            @Override
            public int compare(Double d, Double d_1) {
                return d.compareTo(d_1);
            }
        });
        Iterator it = f_of_x.entrySet().iterator();
        TripleSet nextSet = null;
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            
            if((Double)pair.getValue() == results.get((results.size()-1)))
            {
                nextSet = (TripleSet)pair.getKey();
                return nextSet.tile;
            }
        }
        return null;
    }
    
    private double evaluate(double x, int y, int z)
    {
        return ((y / z)/x);
    }
    
    public Tile getHumanlyBestMove(ArrayList<Tile> potentialTiles)
    {
        HashMap<Tile, Integer> pathsizes = new HashMap<>();
        
        ArrayList<Map.Entry> pairs = new ArrayList<>();
        ArrayList<Integer> retrievedPaths = new ArrayList<>();
        
        for(Tile tile: potentialTiles)
        {
            pathsizes.put(tile, recursiveSearch(tile, defaultRecursionCount, new ArrayList<>()));
        }
        Iterator it = pathsizes.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            retrievedPaths.add((int)pair.getValue());
        }
        
        Collections.sort(retrievedPaths, new Comparator<Integer>() 
        {
            @Override
            public int compare(Integer int_a, Integer int_b) {
                return int_a.compareTo(int_b);
            }
        });
        
        int selectedHighestPath = retrievedPaths.get(retrievedPaths.size()-1);
        Tile selectedTile = potentialTiles.get(0);
        
        for(Map.Entry pair: pairs){
            if((int)pair.getValue() == selectedHighestPath){
                selectedTile = (Tile)pair.getKey();
            }
        }
        
        System.out.println("Chose " + selectedHighestPath + " out of values: " + retrievedPaths);
        
        return selectedTile;
    }
    
    //Return the amount of possible moves from <tile>, <recursions> times.
    private int recursiveSearch(Tile tile, int recursions, ArrayList<Tile> exclusion)
    {
        int potentialPaths = 0;
        ArrayList<Tile> foundTiles;
        for(int loop=0; loop < recursions; loop++)
        {
            foundTiles = grid.getLiveTilesSurrounding(tile);
            
            for(Tile nextTile: exclusion)
            {
                if(foundTiles.contains(nextTile))
                {
                    foundTiles.remove(nextTile);
                }
            }
            
            potentialPaths = potentialPaths + foundTiles.size();
            exclusion.add(tile);
            
            for(Tile nextTile: foundTiles)
            {
                potentialPaths = potentialPaths + recursiveSearch(nextTile, recursions - 1, exclusion);
            }
            
        }
        return potentialPaths;
    }
    
    private class TripleSet
    {
        public final Tile tile;
        public final double weight;
        public final int pathsize;
        
        public TripleSet(Tile tile, double weight, int pathsize){
            this.tile = tile;
            this.weight = weight;
            this.pathsize = pathsize;
        }
    }
}
