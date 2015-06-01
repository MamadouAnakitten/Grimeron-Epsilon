/*
 * Grimeron is an open source artificial intelligence testing grounds for 
 * teaching a computer how to play a game and develop strategy. The foundation
 * of the game allows for other developers and programmers to create their own 
 * types of bots and see how they run in the game's interface. By default, the
 * main implemented interface is the Java Swing interface, and has been optimized
 * to use a swing interface. Future plans include making an interface class that 
 * runs the whole game separating the implementation from the interface. 
 */
package org.arps.Grimeron;

import org.arps.Grimeron.entity.Tile;
import java.util.ArrayList;
import java.util.Properties;
import org.arps.Grimeron.UI.GrimeronFrame;
import org.arps.Grimeron.UI.GrimeronSetupFrame;
import org.arps.Grimeron.console.Console;
import org.arps.Grimeron.console.commands.Help;
import org.arps.Grimeron.console.commands.Restart;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.utils.DBOperationHandler;
import org.arps.Grimeron.utils.RuleSet;
import org.arps.Grimeron.utils.enums.PromptSet;
/**
 * Primary running class for the Grimeron Game.
 * @author Richard Hogans and Julian Clark
 */
public class Grimeron{
    
    private GrimeronFrame gameFrame;
    private GrimeronSetupFrame setupFrame;

    private DBOperationHandler dbHandler;
    private RuleSet ruleSet;
   
    private Console console;
    
    public int round = 0;

    /**
     * Creates a gamePanel and gets the pieces then opens the setup gamePanel.
     * @param ruleSet
     */
    public Grimeron(RuleSet ruleSet)
    {        
        console = new Console(this);      
        this.ruleSet = ruleSet;     
        
        gameFrame = new GrimeronFrame(this, console);
    }
    
    public void automatedRun()
    {
        registerCommands();
        setupGame();
        enterGame();
        
        boolean gameRunning = true;
        
        while(gameRunning)
        {
            gameRunning = manageRestart();
        }
        
        System.out.println("Game over");
    }
    
    private boolean manageRestart()
    {
        boolean waitingForRestart = gameFrame.isWaitingForRestart();
        boolean shouldRestart = gameFrame.getRestart();
        
        while(waitingForRestart)
        {
            waitingForRestart = gameFrame.isWaitingForRestart();
            shouldRestart = gameFrame.getRestart();
            
            if(shouldRestart && waitingForRestart == false)
            {
                gameFrame.toggleResetText(false);
                enterGame();
                gameFrame.setWaitingForRestart(true);
                return true;
            }
            
            if(waitingForRestart == false && shouldRestart == false)
            {
                gameFrame.toggleResetText(false);
                return false;
            }
        }
        return true;
    }
    
    private void registerCommands(){
        console.registerCommand(new Help(this));
        console.registerCommand(new Restart(this));
    }
    
    
    public void setupGame()
    {
        setupFrame = new GrimeronSetupFrame(this);
        setupFrame.setVisible(true);
        
        while(!setupFrame.isSetup())
        {
            Thread.yield();
            
        }
        setupFrame.dispose();
        
        gameFrame.setVisible(true);
        
        if(dbHandler == null)
        {
            console.write(PromptSet.DB_NULL_INCOGNITO_ENABLE.toString());
            ruleSet.incognitoMode = true;
        }
        if(ruleSet.statisticMode == false)
        {
            gameFrame.getTabbedPane().setEnabledAt(1, false);
        }
    }
    
    public void enterGame()
    {
        if(ruleSet.rapidMode)
        {
            if(ruleSet.rapidGraphicOverrideEnabled)
            {
                gameFrame.getGamePanel().setVisible(true);
            } else {
                gameFrame.getGamePanel().setVisible(false);
            }
            
            for(int i=0; i<ruleSet.rapidModeCount; i++)
            {
                runGame();
                console.write(PromptSet.PROMPT_GAME_COMPLETE_COUNT + " " + (i+1));
                if(ruleSet.statisticMode)
                {
                    gameFrame.updateStatistics(this);
                    console.write(PromptSet.UPDATE_STATISTICS + " :: Grimeron.EnterGame()");
                }
                if(i != ruleSet.rapidModeCount){
                    resetGame();
                }
            }
        }
        if(!ruleSet.rapidMode)
        {
            runGame();
        }
        gameFrame.clickableYes.setVisible(true);
        gameFrame.clickableNo.setVisible(true);
        gameFrame.text_restart.setVisible(true);
    }
    
    private void runGame()
    {      
        if(ruleSet.rapidMode)
        {
            gameFrame.getGamePanel().setEnabled(false);
            runStandard();
        }
        if(!ruleSet.rapidMode)
        {
            gameFrame.getGamePanel().setEnabled(true);
            runStandard();
        }
        
        for(Player player: ruleSet.getPlayers())
        {
            if(player.getPlace().equals(Player.Place.FIRST))
            {
                gameFrame.getGamePanel().moveDataText.setText(player + " has won the game!!");
                console.write(player + " has won the last game!");
                gameFrame.updateConsole();
            }
        }
        
        if(!ruleSet.incognitoMode)
        {
            submitToDB();
        }
    }
    
    public void runStandard()
    {
        boolean running = true;
        
        round = 1;
        
        while(running)
        {
            ArrayList<Tile> emptyTiles;
            Move lastMove;
            
            for(Player player: ruleSet.getPlayers())
            {
                if(ruleSet.checkGame())
                {
                    running = false;
                    break;
                }
                
                emptyTiles = gameFrame.getGrid().getLiveTiles();
                gameFrame.getGrid().checkForTileClicks();
                
                lastMove = player.performMove(emptyTiles, round);
                
                if(!ruleSet.rapidMode || ruleSet.rapidGraphicOverrideEnabled)
                {
                    updateGraphics(lastMove);
                }
                
                if(ruleSet.statisticMode)
                {
                    gameFrame.updateStatistics(this);
                }
                
                try 
                {
                    if(!ruleSet.rapidMode || ruleSet.rapidGraphicOverrideEnabled)
                    {
                        Thread.sleep(ruleSet.delay);
                    }
                } catch (InterruptedException ex) {}
            }
            gameFrame.updateConsole();
            round++;
        }      
    }
    
    private void updateGraphics(Move lastMove)
    {
        gameFrame.getGrid().updateGraphics(ruleSet);
        gameFrame.getGrid().repaint();  
        gameFrame.getGamePanel().updateInformation(round, lastMove);
    }
    
    private void updateGraphics()
    {
        gameFrame.getGrid().updateGraphics(ruleSet);
        gameFrame.getGrid().repaint();  
    }

    private void submitToDB()
    {
        if(ruleSet.getPlayers().size() > 4 || ruleSet.getPlayers().size() < 4)
        {
            return;
        }
        
        console.write(PromptSet.UPDATE_DB_SUBMIT.toString());
        for(Player player: ruleSet.getPlayers())
        {
            for(Move move: player.getMoveHistory())
            {
                if(player.getType().equals(Player.Type.HUMAN) && player.getPlace().equals(Player.Place.FIRST))
                {
                    move.weight = player.getPlace().getWeight() - 0.7;
                }else{
                    move.weight = player.getPlace().getWeight();
                }
            }
            if(dbHandler != null)
            {
                for(Move move: player.getMoveHistory())
                {
                    move.weight = player.getPlace().getWeight();
                    dbHandler.inputMove(move);
                }
            }
        }
    }
    
    public void resetGame()
    {
        ArrayList<Player> players = new ArrayList<>();
        
        for(Player player: ruleSet.getPlayers())
        {
            player.setTile(gameFrame.getGamePanel().getGrid().getTileAt(player.getStartingTile().getGameX(), player.getStartingTile().getGameY()));
            player.setExistant(true);
            player.setPlace(null);
            player.purgeMoveHistory();
            player.getTile().setColor(player.getColor());
            players.add(player);
        }
        
        this.ruleSet = new RuleSet(this.ruleSet);
        
        ruleSet.assignPlayers(players.toArray(new Player[players.size()]));
        
        for(Tile tile: gameFrame.getGamePanel().getGrid().getPieces())
        {
            tile.setState(Tile.State.OPEN);
            tile.getUITile().repaint();
        }
        
        updateGraphics();
    }

    public GrimeronFrame getGameFrame() 
    {
        return gameFrame;
    }

    public DBOperationHandler getDBHandler() 
    {
        return dbHandler;
    }

    public RuleSet getRuleSet() 
    {
        return ruleSet;
    }

    public int getTurn() 
    {
        return round;
    }
    
    public static void main(String[] args) 
    {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrimeronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrimeronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrimeronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrimeronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        RuleSet rules = new RuleSet();
        
        Grimeron game = new Grimeron(rules);
        
        game.automatedRun();
    }

    public Console getConsole() {
        return this.console;
    }
}


    
