package leinne.java.sudoku;

import com.formdev.flatlaf.FlatLightLaf;
import leinne.java.sudoku.db.DBManager;
import leinne.java.sudoku.entity.NumberTile;
import leinne.java.sudoku.ui.window.LoginWindow;
import leinne.java.sudoku.ui.window.WindowManager;
import leinne.java.sudoku.util.SudokuUtils;

public final class SudokuSystem{

    private static final SudokuSystem instance = new SudokuSystem();

    private final DBManager dbManager = new DBManager();
    private final WindowManager windowManager = new WindowManager();

    private final NumberTile[][] gameBoard = new NumberTile[9][9];

    public static void main(String[] args){
        instance.startApp();
    }

    public static SudokuSystem getInstance(){
        return instance;
    }

    public DBManager getDBManager(){
        return dbManager;
    }

    public WindowManager getWindowManager(){
        return windowManager;
    }

    private void startApp(){
        FlatLightLaf.setup();
        /*while(!dbManager.connect()){
            if(Utils.showDialog("DB 접속 실패", "DB 서버에 접속할 수 없습니다.\n다시 시도하시겠습니까?") != JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }*/

        for(var i = 0; i < 9; ++i){
            for(var j = 0; j < 9; ++j){
                gameBoard[i][j] = new NumberTile(i, j);
            }
        }
        windowManager.setCurrentWindow(new LoginWindow());
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

    public void setNumber(NumberTile my, int number){
        if(!my.isValid()){
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
        my.setNumber(number);
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
