package leinne.java.sudoku.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager{

    private Connection connection = null;

    public void connect(){
        if(!isClosed()){
            return;
        }

        try{
            var conn = DriverManager.getConnection("jdbc:mariadb://todo.net:3306/sudoku", "todo", "todo");
            if(conn != null){
                connection = conn;
            }
        }catch(SQLException e){}
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
