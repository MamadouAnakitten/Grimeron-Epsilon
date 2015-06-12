package org.arps.Grimeron.utils;

import java.util.ArrayList;
import org.arps.Grimeron.UI.Panels.GrimeronGrid;
import org.arps.Grimeron.entity.Tile;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.entity.Player.Place;

/**
 * A class containing methods that will run the game. This class can be subclassed to override rules for the game.
 * The class contains players, modes, and methods that manage the game's funcionality.
 * This class should only be deep copied if for some reason you plan on playing two different games on parallel frames.
 * If this class is deep copied, information will no longer be properly shared throughout the entire program and care
 * must be taken to make sure each different part of the game is using a proper RuleSet.
 * @author Richard Hogans and Julian Clark
 */
public class RuleSet {
    
    private final ArrayList<Player> playerList = new ArrayList<>();
    private final ArrayList<Player> deadPlayers = new ArrayList<>();

    public boolean incognitoMode = false;
    public boolean statisticMode = false;
    public boolean rapidMode = false;
    
    public boolean rapidGraphicOverrideEnabled = true;
    public boolean adminTestOverride = false;
   
    public int rapidModeCount = 0;
    
    public int dimensionSize = 11;

    public int delay = 10;
    
    public RuleSet()
    {
        
    }
    
    /**
     * This is a deep copy restart contructor. This will not copy players nor alive players. That needs to be done manually.
     * @param restart
     */
    public RuleSet(RuleSet restart)
    {
        this.incognitoMode               = restart.incognitoMode;
        this.statisticMode               = restart.statisticMode;
        this.rapidMode                   = restart.rapidMode;
        this.rapidModeCount              = restart.rapidModeCount;
        this.delay                       = restart.delay;
        this.rapidGraphicOverrideEnabled = restart.rapidGraphicOverrideEnabled;
    }
    
    /**
     * Returns true if the game is finished.
     * @return boolean True if game is finished.
     */
    public boolean checkGame()
    {
        if(adminTestOverride)
        {
            return false;
        }
        int deadCount = 0;
        
        for(Player player: playerList)
        {
            if(!player.isExistant()){
                if(!deadPlayers.contains(player))
                {
                    deadPlayers.add(player);
                }
                deadCount++;
            }
        }
        
        if(deadCount == playerList.size()-1)
        {
            assignRank();
            return true;
        }
        return false;
    }
    
    private void assignRank()
    {
        Player nextPlayer;
        int rank;
        
        for(int i=0; i<deadPlayers.size(); i++)
        {
            nextPlayer = deadPlayers.get(i);
            rank = (-(i+1))+(playerList.size());
            if(rank < 5)
            {
                nextPlayer.setPlace(Place.values()[rank]);
            } else {
                nextPlayer.setPlace(Place.NONE);
            }
        }
        
        for(Player player: playerList)
        {
            if(player.isExistant())
            {
                player.setPlace(Place.FIRST);
            }
        }
    }
    
     /**
     * Recommended to call repaint on the grid for these players.
     * @param players
     */
    public void assignPlayers(Player... players)
    {
        playerList.clear();
        for(Player player: players)
        {
            playerList.add(player);
            player.getTile().setColor(player.getColor());
        }
        deadPlayers.clear();
    }
    
    /**
     * Returns an array of players currently bound to this game's ruleset.
     * @return
     */
    public ArrayList<Player> getPlayers() 
    {
        return this.playerList;
    }
    
    public void addPlayer(Player player) 
    {
        playerList.add(player);
        player.getTile().setColor(player.getColor());
    }
    
    public void resetGame(GrimeronGrid newGrid) 
    {
        int previousX;
        int previousY;
        
        Tile newTile;
        
        for(Player player: playerList)
        {
            previousX = player.getStartingTile().getGameX();
            previousY = player.getStartingTile().getGameY();
            
            newTile = newGrid.getTileAt(previousX, previousY);
            
            player.setTile(newTile);
            
            player.setExistant(true);
            player.setPlace(null);
            player.purgeMoveHistory();
        }
        
        Player[] playerArray = new Player[playerList.size()];
        playerArray = playerList.toArray(playerArray);
        assignPlayers(playerArray);
        deadPlayers.clear();
    }
}
