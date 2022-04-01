package leinne.java.sudoku;

import com.formdev.flatlaf.FlatLightLaf;
import leinne.java.sudoku.db.DBManager;
import leinne.java.sudoku.ui.window.InGameWindow;
import leinne.java.sudoku.ui.window.LoginWindow;
import leinne.java.sudoku.ui.window.WindowManager;

public final class SudokuSystem{

    private static final SudokuSystem instance = new SudokuSystem();

    private final DBManager dbManager = new DBManager();
    private final WindowManager windowManager = new WindowManager();

    public static void main(String[] args){
        SudokuSystem.getInstance().startApp();
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

    public void startApp(){
        FlatLightLaf.setup();
        dbManager.connect();
        windowManager.setCurrentWindow(new LoginWindow());
    }

    public boolean isSolved(){
        if(!(windowManager.getCurrentWindow() instanceof InGameWindow window)){
            return false;
        }

        for(var tile : window.getGamePanel().getNumberTiles()){
            if(tile.isNesting()) return false;
        }
        return true;
    }
}
