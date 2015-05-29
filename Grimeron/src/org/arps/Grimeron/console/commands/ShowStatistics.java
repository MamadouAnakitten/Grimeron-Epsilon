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
 *
 * @author richa_000
 */
public class ShowStatistics extends Command{

    public ShowStatistics(Grimeron game) {
        super("showstats", game);
    }
    
    @Override
    public String onExecute(Console invoker){
        String returnString = ":: Statistics ::\n";
        
        
        return returnString;
    }
}
