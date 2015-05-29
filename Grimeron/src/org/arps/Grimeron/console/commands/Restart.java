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
public class Restart extends Command{

    public Restart(Grimeron game) 
    {
        super("restart", game);
    }
    
    @Override
    public String onExecute(Console invoker)
    {
        //game.resetGame();
        //game.enterGame();
        return "Restart has not been implemented! This is just to showcase the help command.";
    }
}
