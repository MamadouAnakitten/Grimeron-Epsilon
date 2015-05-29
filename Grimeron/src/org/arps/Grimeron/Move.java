package org.arps.Grimeron;

import org.arps.Grimeron.utils.enums.Direction;
import org.arps.Grimeron.entity.Tile;
import org.arps.Grimeron.entity.Player;

/**
 *
 * @author Julian Clark
 */
public class Move {
    
    public Player invoker = null;
    public Tile fromTile = null;
    public Tile toTile = null;
    public Direction direction = null;
    public int turn = -1;
    public int frequency = 0;
    public double weight = 5;
    
    /**
     * Create new move information (Handy for communicating all the details of moves massively.)
     * @param invoker
     * @param fromTile
     * @param toTile
     * @param turnOfOccurance
     */
    public Move(Player invoker, Tile fromTile, Tile toTile, int turnOfOccurance){
        this.fromTile = fromTile;
        this.toTile = toTile;
        this.turn = turnOfOccurance;
        this.invoker = invoker;
        this.setDirection();
    }
    
    /**
     * Create new move information (Handy for communicating all the details of moves massively.)
     * @param fromTile
     * @param direction
     * @param turnOfOccurance
     */
    public Move(Tile fromTile, Direction direction, int turnOfOccurance){
        this.fromTile = fromTile;
        this.direction = direction;
        this.turn = turnOfOccurance;
    }
    
    /**
     * Create a statistical move. This should not be constructed unless you are using it for statistical purpose.
     * @param fromX
     * @param fromY
     * @param direction
     * @param turn
     * @param frequency
     */
    public Move(int fromX, int fromY, int direction, int turn, int frequency, double weight){
        this.fromTile = new Tile(fromX, fromY);
        this.direction = Direction.getValueOf(direction);
        this.turn = turn;
        this.weight = weight;
        this.frequency = frequency;
    }
    
    /**
     * Deep copy constructor for a move.
     * @param move
     */
    public Move(Move move){
        this.fromTile = new Tile(move.fromTile);
        this.toTile = new Tile(move.toTile);
        this.direction = move.direction;
        this.invoker = move.invoker;
        this.weight = move.weight;
        this.frequency = move.frequency;
    }
    
    /**
     *  Basic move constructor.
     */
    public Move(){
        
    }

    /**
     * Returns an average weight of a new weight and old weight based on frequency.
     * @param move
     * @param frequency
     * @param previousWeight
     * @return double Averaged Weight
     */
    public static double averageWeight(Move move, int frequency, double previousWeight){
        double newWeight;
        newWeight = (((previousWeight * frequency) + move.weight)/(frequency + 1));
        return newWeight;
    }
    
    @Override
    public String toString(){
        String toString = "";
        
        try{
            toString = (invoker.name + " moved from (" + fromTile.getGameX() + ", " + fromTile.getGameY() + ") to (" + toTile.getGameX() + ", " + toTile.getGameY() + ").");
        }catch(NullPointerException ex){
            
        }
        
        return toString;
    }
    
    //Function that Turns a Tile into a Weight
    private void setDirection(){
    
        Direction thisDirection = null;
    
        //up
        if(fromTile.getGameX() == toTile.getGameX() && fromTile.getGameY() - 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(0);
        }
    
        //upleft
        if(fromTile.getGameX() - 1 == toTile.getGameX() && fromTile.getGameY() - 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(1);
        }
    
        //left
        if(fromTile.getGameX() - 1 == toTile.getGameX() && fromTile.getGameY() == toTile.getGameY()){
            thisDirection = Direction.getValueOf(2);
        }
    
        //downleft
        if(fromTile.getGameX() - 1 == toTile.getGameX() && fromTile.getGameY() + 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(3);
        }
    
        //down
        if(fromTile.getGameX() == toTile.getGameX() && fromTile.getGameY() + 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(4);
        }
    
        //downright
        if(fromTile.getGameX() + 1 == toTile.getGameX() && fromTile.getGameY() + 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(5);
        }
    
        //right
        if(fromTile.getGameX() + 1 == toTile.getGameX() && fromTile.getGameY() == toTile.getGameY()){
            thisDirection = Direction.getValueOf(6);
        }
    
        //upright
        if(fromTile.getGameX() + 1 == toTile.getGameX() && fromTile.getGameY() - 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(7);
        }
    
        this.direction = thisDirection;
    }
}
