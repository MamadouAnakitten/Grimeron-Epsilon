/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.console;

import org.arps.Grimeron.Grimeron;

/**
 *
 * @author Richard Hogans
 */
public abstract class Command {
    
    public final String executionString;
    public final String[] args;
    public final Grimeron game;
    public final Console invoker;
    
    public Command(String executionString, Grimeron game){
        this.executionString = executionString;
        this.game = game;
        this.args = null;
        this.invoker = game.getConsole();
    }

    public Command(String executionString, String[] args, Grimeron game){
        this.executionString = executionString;
        this.args = args;
        this.game = game;
        this.invoker = game.getConsole();
    }
    
    protected void throwException(Exception e, Console invoker){
        invoker.execute(new Command(null, null) {
            @Override
            public String onExecute(Console invoker) {
                return "There was a problem trying to execute this command...";
            }
        });
    }
    
    /**
     * This method will be called when the command is executed.
     * @param invoker
     * @return String to be printed.
     */
    public String onExecute(Console invoker){
        return null;
    }
    
    public void registerCommand(Console invoker){
        invoker.commands.add(this);
    }
}
