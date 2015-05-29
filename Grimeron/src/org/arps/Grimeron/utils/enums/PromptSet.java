/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arps.Grimeron.utils.enums;

/**
 * Set of prompts for outputting...
 * @author Richard Hogans and Julian Clark
 */
public enum PromptSet {
    
    CONSOLE_UNRECOGNIZED_COMMAND("The command you entered wasn't recognized!", PromptType.ERROR),
    DB_NULL_INCOGNITO_ENABLE("There is no database handler! Enabling incognito..", PromptType.ERROR),
    DB_NULL_INCOGNITO_DISABLE("The database handler is no longer null. Disabling incognito..", PromptType.INFO),
    INSUFFICIENT_PLAYER_COUNT("Not enough players! Adding bots..", PromptType.WARNING),
    PROMPT_GAME_COMPLETE_COUNT("Game completed:", PromptType.INFO),
    PROMPT_GAME_COMPLETE_NOCOUNT("The game has been completed!", PromptType.INFO),
    PROMPT_GAME_RESTART("The game has been reset!", PromptType.INFO),
    UPDATE_DB_SUBMIT("Submitting all the moves to the database!", PromptType.INFO),
    UPDATE_STATISTICS("Updating the statistic tab!", PromptType.INFO);
    
    private String description;
    private PromptType type;

    private PromptSet(String description, PromptType type){
        this.description = description;
        this.type = type;
    }

    public String getDescription(){
        return "[Grimeron] " + type + description;
    }
    
    @Override
    public String toString(){
        return getDescription();
    }
    
    private enum PromptType{
        
        INFO("Info: "),
        WARNING("Warning: "),
        ERROR("Error: ");
        
        private String prefix;
        
        private PromptType(String prefix){
            this.prefix = prefix;
        }
        
        @Override
        public String toString(){
            return prefix;
        }
    }
}
