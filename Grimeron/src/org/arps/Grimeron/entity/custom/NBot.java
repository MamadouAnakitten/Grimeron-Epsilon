/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.entity.custom;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.arps.Grimeron.utils.math.RecursionSegment;

/**
 *
 * @author richa_000
 */
public class NBot extends Player{
    // Deals with the Database connections
    public final DBOperationHandler opHandler;
    public int defaultRecursionCount = 3;
    //public float decisionBuffer = 2;
    private final GrimeronGrid grid;
    
    //Bot Initialization
    public NBot(Tile tile, String name, DBOperationHandler opHandler, GrimeronGrid grid)
    {
        super(Type.BOT, tile, name);
        this.opHandler = opHandler;
        this.grid = grid;
    }
    
    public Move performMove(ArrayList<Tile> emptySquares, int turn)
    {
        ArrayList<Tile> potentialNextMoves = new ArrayList<>();
        
        float defaultWeight = (float) ((Player.Place.FIRST.getWeight() + Place.NONE.getWeight()) / 2.0);
        float weight = defaultWeight; //Deep copy.
        
        //Weights of possible moves 
        HashMap<Tile, Float> moveWeightValues = new HashMap<>();

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
        
        //Start the me decision process.
        if(potentialNextMoves.isEmpty())
        {   
            this.kill();
        }
        else
        {
            Tile bestRatedTile = null;
         
            ArrayList<RecursionSegment> segs  = new ArrayList<RecursionSegment>();
            
            for(Tile tile: potentialNextMoves)
            {
                segs.add(new RecursionSegment(tile, defaultRecursionCount, grid));
            }
            
            try{
                RecursionSegment[] seg = new RecursionSegment[segs.size()];  
                bestRatedTile = pickBestOrigin(segs.toArray(seg)).getCurrentTile();
            }catch(NullPointerException ex ){
                System.out.println("You suck ");
            }
            
            
            
            
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
    
    

    private RecursionSegment pickBestOrigin(RecursionSegment... segs){
        
        HashMap<RecursionSegment, Integer> originMaxPath = new HashMap<>();
        
        for(RecursionSegment segment: Arrays.asList(segs)){
            ArrayList<RecursionSegment> endList = segment.recursiveSearch(segment);
            HashMap<RecursionSegment, Integer> genCount = new HashMap<RecursionSegment, Integer>();
          
            for(RecursionSegment endSeg: endList){
                genCount.put(endSeg, endSeg.getGeneration());
            }
          
            List<Integer> generationList = new ArrayList( genCount.values() );
          
            Collections.sort(generationList, new Comparator<Integer>() {
                @Override
                public int compare(Integer i, Integer j) {
                    return i.compareTo(j);
                }
            });
          
            Collections.reverse(generationList);
          
            originMaxPath.put(segment, generationList.get(0));
        }

        List<Integer> finalGenerations = new ArrayList( originMaxPath.values() );
          
        Collections.sort(finalGenerations, new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return i.compareTo(j);
            }
        });
          
        Collections.reverse(finalGenerations);

        Iterator it = originMaxPath.entrySet().iterator();
        
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            
            if(pair.getValue() == finalGenerations.get(0)) return (RecursionSegment) pair.getKey();
        }
        
        return null;
    }

    
    private class TripleSet
    {
        public final Tile tile;
        public final float weight;
        public final int pathsize;
        
        public TripleSet(Tile tile, float weight, int pathsize){
            this.tile = tile;
            this.weight = weight;
            this.pathsize = pathsize;
        }
    }
}
