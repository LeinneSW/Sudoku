package leinne.java.sudoku;

import leinne.java.sudoku.ui.window.LoginWindow;
import leinne.java.sudoku.ui.window.WindowManager;

public class Main{

    public static void main(String[] args){
        WindowManager.getInstance().setCurrentWindow(new LoginWindow());
    }
}
