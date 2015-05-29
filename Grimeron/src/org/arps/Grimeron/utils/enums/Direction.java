/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.utils.enums;

/**
 * Enum for direction value assignment, is used when handling moves and database communication.
 */
public enum Direction {
    NORTH(0), NORTHEAST(1), EAST(2), SOUTHEAST(3), SOUTH(4), SOUTHWEST(5), WEST(6), NORTHWEST(7); 
 
    private int value;
    
    private Direction(int value){
         this.value = value;
    }
        
    public int getValue(){
        return this.value;
    }
        
    /**
     * In order to make reverse retrieval more efficient, I have made this a method.
     * @param value
     * @return
     */
    public static Direction getValueOf(int value){
        Direction[] directions = Direction.values();
        for(Direction direction: directions){
            if(direction.getValue() == value){
                return direction;
            }
            
        }
        return null;
    }
}