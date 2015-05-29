package org.arps.Grimeron.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.arps.Grimeron.utils.enums.Direction;
import org.arps.Grimeron.Move;
import org.arps.Grimeron.entity.Player;
import org.arps.Grimeron.entity.Tile;

public class DBOperationHandler{
  
    private Statement statement;
    private Connection connection;
    
    private String host;
    private String port;
    private String dbName;
    private String tableName;
    

    public DBOperationHandler(Connection connection, String tableName){
        this.tableName = tableName;
        
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            return;
        } catch (NullPointerException ex) { 
            return;
        }
    }
    
    public DBOperationHandler(String host, String port, String dbName, String tableName, String user, String pass){
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.tableName = tableName;
        
        String url = "jdbc:derby://" + host + ":" + port + "/" + dbName;
         
        if(this.connection != null){
            try {
                this.connection = DBConnect.getConnection(url, user, pass);
                this.statement = connection.createStatement();
            } catch (SQLException ex) {
                return;
            }
        }
    }

    public void inputMove(Move storedMove){
      
        String query = "SELECT * FROM " + tableName + " WHERE x = " + storedMove.fromTile.getGameX() + " AND y = " + storedMove.fromTile.getGameY() + " AND turn = " + storedMove.turn + " AND direction = " + storedMove.direction.getValue();
        ResultSet sameMove = null;
        
        try{
            sameMove = statement.executeQuery(query);
        }catch(Exception e){  
            return;
        }
        
        if(sameMove != null){
            try {
                if(sameMove.first()){
                    //Gets weight of the resultset move
                    double prevWeight = sameMove.getDouble("weight");
                    //Gets frequency of resultset move
                    int frequency = sameMove.getInt("frequency");
                
                    double newWeight = Move.averageWeight(storedMove, frequency, prevWeight);
                
                    int id = sameMove.getInt("id");
                
                    //Stores averaged move
                    updateMove(id, newWeight, frequency);
                }else{
                    insertMove(storedMove);
                }
            } catch (SQLException ex) {
                return;
            }
        }
    }
        
    private void insertMove(Move storedMove){
     
        //System.out.println("Move being inserted has turn number: " + storedMove.turn);
        String insertion = "INSERT INTO " + tableName + " (x, y, direction, weight, turn, frequency) VALUES (" + storedMove.fromTile.getGameX() + ", " + storedMove.fromTile.getGameY() + ", " + storedMove.direction.getValue() + ", " + storedMove.weight + ", " + storedMove.turn + ", 1)";
      
        try{
            statement.execute(insertion);
        }catch(SQLException ex){
            return;
        }     
    }
    
    private void updateMove(int id, double newWeight, int frequency){
        
        frequency = frequency + 1;
        String updater = "UPDATE " + tableName + " SET weight = " + newWeight + ", frequency = " + frequency + "WHERE id = " + id;
          
        try{
            statement.execute(updater);
        }catch(SQLException ex){
            return;
        }     
    }
   
    public double getMoveWeight(Tile fromTile, Direction direction, int turn){
        String selection = "SELECT * FROM " + tableName + " WHERE X=" + fromTile.getGameX() +
                " AND Y=" + fromTile.getGameY() + 
                " AND TURN=" + turn + 
                " AND DIRECTION=" + direction.getValue();
        
        ResultSet foundMove = null;
     
        try{
            foundMove = statement.executeQuery(selection);
        }catch(Exception ex){
        }
        
        double avgWeight = (Player.Place.FIRST.getWeight() + Player.Place.NONE.getWeight()) / 2;
        
        double weight = avgWeight;
        
        if(foundMove != null){
            try {
                if(foundMove.first()){
                    //Gets weight of the resultset move
                    weight = foundMove.getDouble("weight");
                }else{
                }
            } catch (SQLException ex) {
                    
            }
        }
        return weight;
    }

    //Function that Turns a Tile into a Weight
    public double getMoveWeight(Tile fromTile, Tile toTile, int turn){
    
        Direction thisDirection = null;
       
        //up
        if(fromTile.getGameX() == toTile.getGameX() && fromTile.getGameY() - 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(0);
        }
    
        //upleft
        if(fromTile.getGameX() - 1 == toTile.getGameX() && fromTile.getGameY() - 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(7);
        }
    
        //left
        if(fromTile.getGameX() - 1 == toTile.getGameX() && fromTile.getGameY() == toTile.getGameY()){
            thisDirection = Direction.getValueOf(6);
        }
    
        //downleft
        if(fromTile.getGameX() - 1 == toTile.getGameX() && fromTile.getGameY() + 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(5);
        }
    
        //down
        if(fromTile.getGameX() == toTile.getGameX() && fromTile.getGameY() + 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(4);
        }
    
        //downright
        if(fromTile.getGameX() + 1 == toTile.getGameX() && fromTile.getGameY() + 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(3);
        }
    
        //right
        if(fromTile.getGameX() + 1 == toTile.getGameX() && fromTile.getGameY() == toTile.getGameY()){
            thisDirection = Direction.getValueOf(2);
        }
    
        //upright
        if(fromTile.getGameX() + 1 == toTile.getGameX() && fromTile.getGameY() - 1 == toTile.getGameY()){
            thisDirection = Direction.getValueOf(1);
        }
        
        double weight = getMoveWeight(fromTile, thisDirection, turn);
        return weight;
    }
   
    private void closeConnection() throws SQLException{
        if(connection != null){
            if(statement != null){
                statement.close();
            }
            connection.close();
        }
    }  
    
    public ResultSet executeQuery(String sql){
        ResultSet set = null;
        
        try {
            set = statement.executeQuery(sql);
        } catch (SQLException ex) {
        }
        
        return set;
    }
    
    public void execute(String sql){
        try {
            statement.execute(sql);
        } catch (SQLException ex) {
        }
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }

    public String getTableName() {
        return tableName;
    }
    
    public boolean isNull(){
        return connection == null;
    }
}