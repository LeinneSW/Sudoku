package leinne.java.sudoku;

import leinne.java.sudoku.ui.screen.MainScreen;

public class Main{

    public static void main(String[] args){
        /*try{
            var conn = DriverManager.getConnection("jdbc:mariadb://arm.leinne.net:3306/sudoku", "leinne", "root");
            if(conn != null){
                System.out.println("DB 접속 성공");
            }
        }catch(SQLException e){
            System.out.println("DB 접속 실패");
        }*/

        var frame = new MainScreen();
        frame.setVisible(true);
    }
}
