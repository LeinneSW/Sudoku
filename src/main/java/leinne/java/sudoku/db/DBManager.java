package leinne.java.sudoku.db;

import leinne.java.sudoku.SudokuSystem;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager{

    private boolean offline = false;
    private Connection connection = null;

    public boolean connect(){
        if(isConnected()){
            return false;
        }

        var config = SudokuSystem.getInstance().getConfig();
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

    public void setOffline(boolean value){
        offline = value;
    }

    public boolean isConnected(){
        try{
            return offline || connection != null && !connection.isClosed();
        }catch(SQLException e){
            return false;
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public boolean checkAccount(String id, String password){
        if(offline){
            return true;
        }

        if(id.length() <= 0 || password.length() <= 0){
            return false;
        }

        try{
            var statement = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            statement.setString(1, id);
            var resultSet = statement.executeQuery();
            return resultSet.first() && BCrypt.checkpw(password, resultSet.getString("password"));
        }catch(SQLException e){}
        return false;
    }
}
