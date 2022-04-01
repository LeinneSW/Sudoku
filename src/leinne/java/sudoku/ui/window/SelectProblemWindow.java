package leinne.java.sudoku.ui.window;

import javax.swing.*;
import java.awt.*;

public class SelectProblemWindow extends Window{

    public SelectProblemWindow(){
        var btn = new JButton("게임시작");
        btn.addActionListener((e) -> {
            var gameScreen = new InGameWindow();
            gameScreen.getGamePanel().setSudokuProblem("000806010060000000809000000010000000000050090050000001000000007000009000320001000");
            WindowManager.getInstance().setCurrentWindow(gameScreen);
        });
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(btn);
    }
}
