package leinne.java.sudoku;

import com.formdev.flatlaf.FlatLightLaf;
import leinne.java.sudoku.db.DBManager;
import leinne.java.sudoku.entity.NumberTile;
import leinne.java.sudoku.ui.window.LoginWindow;
import leinne.java.sudoku.ui.window.WindowManager;
import leinne.java.sudoku.util.Config;
import leinne.java.sudoku.util.SudokuUtils;
import leinne.java.sudoku.util.Utils;

import javax.swing.*;
import java.io.*;

public final class SudokuSystem{

    private static final SudokuSystem instance = new SudokuSystem();

    private final DBManager dbManager = new DBManager();
    private final WindowManager windowManager = new WindowManager();

    private final Config config = new Config();
    private final NumberTile[][] gameBoard = new NumberTile[9][9];

    private void loadConfig(){
        if(!new File("./config.ini").exists()){
            try(var fileStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.ini");
                var outStream = new FileOutputStream("./config.ini")){
                if(fileStream == null){
                    showErrorAndExit("실행 파일이 손상된것 같습니다.");
                    return;
                }

                var read = 0;
                var bytes = new byte[1024];
                try{
                    while((read = fileStream.read(bytes)) != -1){
                        outStream.write(bytes, 0, read);
                    }
                }catch(IOException e){
                    showErrorAndExit("실행중인 경로가 올바르지 않거나 파일 생성 권한이 없습니다.\n혹은 장치의 용량이 부족할 수도 있습니다.");
                    return;
                }
            }catch(Exception e){}
        }
        config.loadData();
    }

    private void showErrorAndExit(String message){
        JOptionPane.showConfirmDialog(
            null,
            "config.ini 파일 생성에 실패했습니다.\n" + message,
            "파일 생성 실패",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.ERROR_MESSAGE
        );
        System.exit(0);
    }

    public static void main(String[] args){
        FlatLightLaf.setup();
        instance.loadConfig();
        while(!instance.dbManager.connect()){
            if(Utils.showYesNoDialog("DB 접속 실패", "DB 서버에 접속할 수 없습니다.\n다시 시도하시겠습니까?") != JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        Runtime.getRuntime().addShutdownHook(new Thread(instance.config::saveData));

        for(var i = 0; i < 9; ++i){
            for(var j = 0; j < 9; ++j){
                instance.gameBoard[i][j] = new NumberTile(i, j);
            }
        }
        instance.windowManager.setCurrentWindow(new LoginWindow());
    }

    public static SudokuSystem getInstance(){
        return instance;
    }

    public Config getConfig(){
        return config;
    }

    public DBManager getDBManager(){
        return dbManager;
    }

    public WindowManager getWindowManager(){
        return windowManager;
    }

    public void setProblem(String sudoku){
        var sudokuArray = SudokuUtils.parse(sudoku);
        if(!SudokuUtils.isValidProblem(sudokuArray)){
            return;
        }


        for(int row = 0;  row < 9; ++row){
            for(int column = 0; column < 9; ++column){
                gameBoard[row][column].setNumber(sudokuArray[row][column], true);
            }
        }
    }

    public boolean isSolved(){
        for(int row = 0;  row < 9; ++row){
            for(int column = 0; column < 9; ++column){
                var tile = gameBoard[row][column];
                if(tile.isNesting() || tile.getNumber() == 0) return false;
            }
        }
        return true;
    }

    public NumberTile getNumberTile(int row, int column){
        return row >= 0 && row < 9 && column >= 0 && column < 9 ? gameBoard[row][column] : null;
    }

    public NumberTile[][] getNumberTiles(){
        return gameBoard;
    }

    public void checkVerifyNumbers(NumberTile my, int number){
        if(my.isProblem() || !my.isValid()){
            return;
        }

        for(int i = 0; i < 9; ++i){
            verifyNumber(my, gameBoard[my.row][i], number); // i: 가로
            verifyNumber(my, gameBoard[i][my.column], number); // i: 세로
        }

        for(int rowStart = my.row / 3 * 3, rowEnd = rowStart + 3; rowStart < rowEnd; ++rowStart){
            for(int colStart = my.column / 3 * 3, colEnd = colStart + 3; colStart < colEnd; ++colStart){ // 3 * 3
                verifyNumber(my, gameBoard[rowStart][colStart], number);
            }
        }
    }

    private void verifyNumber(NumberTile my, NumberTile other, int number){
        if(my.getIndex() == other.getIndex()){
            return;
        }

        if(number > 0 && number == other.getNumber()){
            my.addNesting(other.getIndex());
            other.addNesting(my.getIndex());
        }else if(my.getNumber() == other.getNumber()){
            my.removeNesting(other.getIndex());
            other.removeNesting(my.getIndex());
        }
    }
}
