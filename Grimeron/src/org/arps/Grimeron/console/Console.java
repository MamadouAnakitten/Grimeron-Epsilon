/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.console;

import java.util.ArrayList;
import java.util.Arrays;
import org.arps.Grimeron.Grimeron;
import org.arps.Grimeron.utils.enums.PromptSet;

/**
 * Interaction class that handles console events and uses 
 * the command class interface to interact with the game and user.
 * @author Richard Hogans and Julian Clark
 */
public class Console { 
    
    private Grimeron game;
    
    public final ArrayList<Command> commands = new ArrayList<>();
    public final ArrayList<String> outputLog = new ArrayList<>();
    private final ArrayList<Command> inputLog = new ArrayList<>();
    
    public Console(){}
    
    public Console(Grimeron game){
        this.game = game;
        outputLog.add("Grimeron Console: Type help for help!");
    }
    
    public void attemptCommand(String input){
        //Split into words.
        String[] inputArray = input.split(" ");
        
        for(Command command: commands){
            if(command.executionString.equalsIgnoreCase(inputArray[0])){
                execute(command);
                return;
            }
        }
        write(PromptSet.CONSOLE_UNRECOGNIZED_COMMAND.toString());
    }
    
    public void registerCommand(Command command){
        commands.add(command);
    }
    
    public boolean execute(Command command){
        //Execute and log command made.
        inputLog.add(command);
        String log = command.onExecute(this);
        if(log.length() > 1){
           write(log);
        }
        return false;
    }
    
    public void write(String output){
        outputLog.add(0, output);
    }
    
    public String getOutput(){
        String outputString = "";
        int counter = 0;
        for(String nextOut: outputLog){
            if(counter == 0){
                outputString = ">> " + nextOut;
            }
            else{
                outputString = outputString + "\n>> " + nextOut;
            }
            counter ++;
        }
        return outputString;
    }
    
    public void clearLog(){
        
    }
}

