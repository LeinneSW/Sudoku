package leinne.java.sudoku.ui.window;

import leinne.java.sudoku.SudokuSystem;

import javax.swing.*;
import java.awt.*;

public class SelectProblemWindow extends Window{

    protected void initDesign(){
        var pane = getContentPane();
        pane.setLayout(new FlowLayout());

        var btn = new JButton("게임시작");
        btn.addActionListener((e) -> {
            var gameWindow = new InGameWindow();
            gameWindow.getGamePanel().setSudokuProblem("000806010060000000809000000010000000000050090050000001000000007000009000320001000");
            SudokuSystem.getInstance().getWindowManager().setCurrentWindow(gameWindow);
        });
        pane.add(btn);
    }
}
