/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.arps.Grimeron.Grimeron;
import org.arps.Grimeron.Move;
import org.arps.Grimeron.entity.Player;

/**
 * A class containing the ability to store information about games.
 * @author Richard Hogans and Julian Clark
 */
@SuppressWarnings("UnusedAssignment")
public class StatisticSet {
    private ArrayList<Player> playerList;
    
    private RuleSet rulesUsed = null;
    
    private final HashMap<Player, Integer> playerWins = new HashMap<>();
    
    private final ArrayList<Integer> gameLengths = new ArrayList<>(); 
    private final ArrayList<Move> allMoves = new ArrayList<>();
    private ArrayList<Move> mostFrequentMoves;
    private ArrayList<Move> bestWeightedMoves;
    private ArrayList<Move> leastFrequentMoves;
    private ArrayList<Move> worstWeightedMoves;
    
    private DBOperationHandler dbHandler;
    
    private int gamesPlayed = 0;
    private int averageLength = 0;
    
    public StatisticSet(Grimeron game){
        updateStatistics(game);
    }
    
    public StatisticSet(){
        
    }
    
    public void updateStatistics(Grimeron game){
        rulesUsed = game.getRuleSet();
        gamesPlayed = rulesUsed.rapidModeCount;
        dbHandler = game.getDBHandler();
        playerList = rulesUsed.getPlayers();
        gamesPlayed++;
        gameLengths.add(game.round);
        
        int combinedLens = 0;
        for(Integer nextLength: gameLengths){
            combinedLens += nextLength;
        }
        
        averageLength = combinedLens / gameLengths.size();
        
        for(Player player: playerList){                    //   For every player in this last game.
            for(Move move: player.getMoveHistory()){       //   For every move they made..
                allMoves.add(move);                        //   Add it to the array.
            }                                              //   Moves know who made them so
        }                                                  //   no worried over identity.
        
        mostFrequentMoves = getLeastMost(dbHandler, "FREQUENCY", true, 10);
        leastFrequentMoves = getLeastMost(dbHandler, "FREQUENCY", false, 10);
        
        bestWeightedMoves = getLeastMost(dbHandler, "WEIGHT", true, 10);
        worstWeightedMoves = getLeastMost(dbHandler, "WEIGHT", true, 10);
        
        //Update player wins.
        for(Player player: playerList){
            if(player.getPlace() != null){
                if(player.getPlace().equals(Player.Place.FIRST)){
                    if(playerWins.containsKey(player)){
                        playerWins.replace(player, playerWins.get(player)+1);
                    }
                    else{
                        playerWins.put(player, 1);
                    }
                }
            }
        }
    }
    
    private static ArrayList<Move> getLeastMost(DBOperationHandler dbHandler, String mostFrequentSortBy, boolean descending, int count){
        
        String ascDirection = "DESC";
        
        if(!descending){
            ascDirection = "ASC";
        }
        
        try {
            ArrayList<Move> moves = new ArrayList<>();
            
            String sql = "";
            sql = "SELECT * FROM " + dbHandler.getTableName() + " ORDER BY " + mostFrequentSortBy + " " + ascDirection;
            
            System.out.println(sql);
            
            ResultSet allMoves = dbHandler.executeQuery(sql);
            
            int counter = 0;
            
            if(allMoves.first()){
            
                while(allMoves.next()){
                    if(counter == count){
                        break;
                    }
                
                    int fromX=0, fromY=0;
                    double weight=5;
                    int direction=0;
                    int frequency=0;
                    int turn=0;
                
                    fromX = allMoves.getInt("x");
                    fromY = allMoves.getInt("y");
                    weight = allMoves.getDouble("weight");
                    direction = allMoves.getInt("direction");
                    frequency = allMoves.getInt("frequency");
                    turn = allMoves.getInt("turn");
                
                    Move nextMove = new Move(fromX, fromY, direction, turn, frequency, weight);
                    moves.add(nextMove);
                
                    counter++;
                }
                
            return moves;
            } 
        }catch (SQLException ex) {
            return null;
        }catch (NullPointerException ex) {
            return null;
        }
        return null;
    }

    public RuleSet getRulesUsed() {
        return rulesUsed;
    }

    public HashMap<Player, Integer> getPlayerWins() {
        return playerWins;
    }

    public ArrayList<Move> getAllMoves() {
        return allMoves;
    }

    public ArrayList<Move> getMostFrequentMoves() {
        return mostFrequentMoves;
    }

    public ArrayList<Move> getBestWeightedMoves() {
        return bestWeightedMoves;
    }

    public ArrayList<Move> getLeastFrequentMoves() {
        return leastFrequentMoves;
    }

    public ArrayList<Move> getWorstWeightedMoves() {
        return worstWeightedMoves;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<Integer> getGameLengths() {
        return gameLengths;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getAverageLength() {
        return averageLength;
    } 
}
