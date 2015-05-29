/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.console.commands;

import org.arps.Grimeron.Grimeron;
import org.arps.Grimeron.console.Command;
import org.arps.Grimeron.console.Console;

/**
 * Help Command
 * @author Richard Hogans
 */
public class Help extends Command{

    public Help(Grimeron game) {
        super("help", game);
    }

    @Override
    public String onExecute(Console invoker) {
        
        String returnString = "Availiable Commands:";
        
        System.out.println(invoker.commands);
        
        for(Command command: invoker.commands){
            returnString = returnString + "\n " + command.executionString;
        }
        
        return returnString;
    }   
}
