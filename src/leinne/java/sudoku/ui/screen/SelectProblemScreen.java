package leinne.java.sudoku.ui.screen;

import javax.swing.*;
import java.awt.*;

public class SelectProblemScreen extends JFrame{

    public SelectProblemScreen(){
        super("스도쿠");
        var btn = new JButton("게임시작");
        btn.addActionListener((e) -> {
            setVisible(false);
            var gameScreen = new InGameScreen();
            gameScreen.getGamePanel().setSudokuProblem("000806010060000000809000000010000000000050090050000001000000007000009000320001000");
            gameScreen.setVisible(true);
        });
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(btn);

        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
