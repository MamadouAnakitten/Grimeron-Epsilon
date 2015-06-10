package org.arps.Grimeron.utils.math;

import java.util.ArrayList;
import org.arps.Grimeron.UI.Panels.GrimeronGrid;
import org.arps.Grimeron.entity.Tile;

/**
 *
 * @author Julian Clark ~ Partially Richard Hogans
 */
public class RecursionSegment {

    private ArrayList<RecursionSegment> segmentHistory = new ArrayList<RecursionSegment>();
    
    private Tile currentTile;
    
    private int generation;
        
    private int enemies = 0;
    
    private int recursionMax = 0;
    
    private boolean isEnd = false;
    
    private GrimeronGrid grid;
    
    public RecursionSegment(RecursionSegment lastSegment, Tile nextTile)
    {
        this.grid = lastSegment.getGrid();
        segmentHistory.addAll(lastSegment.getSegmentHistory());
        currentTile = nextTile;
        this.generation = lastSegment.getGeneration() + 1;
        this.recursionMax = lastSegment.getRecursionMax();
    }
    
    public RecursionSegment(Tile currentTile, int recursionMax, GrimeronGrid grid)
    {
        this.grid = grid;
        this.currentTile = currentTile;
        this.recursionMax = recursionMax;
        generation = 1;
    }
    
    public ArrayList<RecursionSegment> recursiveSearch(RecursionSegment origin)
    {
        System.out.println("Assessing tile at " + currentTile + " in generation " + generation + " with a max of " + recursionMax);
        
        segmentHistory.add(this);
        ArrayList<Tile> surroundingTiles = grid.getTilesSurrounding(currentTile);
        ArrayList<Tile> availiableTiles = new ArrayList<Tile>();
        ArrayList<RecursionSegment> endSegs = new ArrayList<RecursionSegment>();
        
        for(Tile tile: surroundingTiles)
        {
            if(tile.getState().equals(Tile.State.OCCUPIED)) this.enemies += 1;
            if(tile.getState().equals(Tile.State.OPEN)) availiableTiles.add(tile);
        }
        
        if(surroundingTiles.isEmpty() || (generation == recursionMax)){
            this.isEnd = true;
            endSegs.add(this);
            return endSegs;
        }
        
        RecursionSegment nextSeg;
        ArrayList<RecursionSegment> nextEndSegs;
        for(Tile tile: availiableTiles)
        {
            nextSeg = new RecursionSegment(this, tile);
            nextEndSegs = nextSeg.recursiveSearch(origin);
            
            if(nextEndSegs != null || endSegs != null) endSegs.addAll(nextEndSegs);
            
            if(nextSeg.isEnd) endSegs.add(nextSeg);
        }
        
        //if(availiableTiles.size() > 4 || endSegs.size() > 4) System.out.println("Something must have went wrong: Assesed " + availiableTiles.size() + " tile and got " + endSegs.size() + " end points.");
        
        
        if(!endSegs.isEmpty()) return endSegs;
        return null;
    }

    public int getEnemies() {
        return enemies;
    }

    public void setEnemies(int enemies) {
        this.enemies = enemies;
    }

    public ArrayList<RecursionSegment> getSegmentHistory() {
        return segmentHistory;
    }

    public void setSegmentHistory(ArrayList<RecursionSegment> segmentHistory) {
        this.segmentHistory = segmentHistory;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getRecursionMax() {
        return recursionMax;
    }

    public void setRecursionMax(int recursionMax) {
        this.recursionMax = recursionMax;
    }

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public GrimeronGrid getGrid() {
        return grid;
    }

    public void setGrid(GrimeronGrid grid) {
        this.grid = grid;
    }
}
