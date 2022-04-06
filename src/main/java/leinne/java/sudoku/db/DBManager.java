package leinne.java.sudoku.db;

import leinne.java.sudoku.SudokuSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager{

    private Connection connection = null;

    public boolean connect(){
        var config = SudokuSystem.getInstance().getConfig();
        if(!config.getBoolean("database", "allow", true)){
            return true;
        }

        if(!isClosed()){
            return false;
        }

        try{
            var ip = config.get("database", "ip");
            var port = config.get("database", "port");
            var dbname = config.get("database", "name");
            if(ip == null || port == null || dbname == null){
                return false;
            }

            connection = DriverManager.getConnection(
                String.format("jdbc:mariadb://%s:%s/%s", ip, port, dbname),
                config.get("database", "username", "root"),
                config.get("database", "password", "root")
            );
            return connection != null;
        }catch(SQLException e){
            return false;
        }
    }

    public boolean isClosed(){
        try{
            return connection == null || connection.isClosed();
        }catch(SQLException e){
            return true;
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
