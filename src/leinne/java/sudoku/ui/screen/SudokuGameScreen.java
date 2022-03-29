package leinne.java.sudoku.ui.screen;

import leinne.java.sudoku.util.SudokuUtils;
import leinne.java.sudoku.ui.component.NumberTile;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class SudokuGameScreen extends JFrame{

    private final JPanel timerPanel = new JPanel();
    private final JPanel gamePanel = new JPanel(new GridLayout(9, 9, 0, 0));

    public SudokuGameScreen(){
        super("스도쿠");

        var main = getContentPane();
        main.setLayout(new BorderLayout());

        main.add(timerPanel, BorderLayout.NORTH);
        main.add(gamePanel, BorderLayout.CENTER);

        // create timer, menu button
        var lblTimer = new JLabel("00:00:00");
        lblTimer.setFont(new Font("Dialog", Font.BOLD, 25));
        timerPanel.add(lblTimer);

        AtomicInteger time = new AtomicInteger();
        var timer = new Timer(1000, (event) -> {
            var t = time.incrementAndGet();
            var hour = t / 3600;
            var minute = t / 60;
            var second = t % 60;
            lblTimer.setText(String.format("%02d:%02d:%02d", hour, minute, second));
        });
        timer.start();

        // create number tile
        for(int i = 0; i < 81; ++i){
            gamePanel.add(new NumberTile(i));
        }

        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setProblem(String sudoku){
        var sudokuArray = SudokuUtils.parse(sudoku);
        if(!SudokuUtils.isValidProblem(sudokuArray)){
            return;
        }

        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                if(sudokuArray[i][j] > 0){
                    ((NumberTile) gamePanel.getComponent(i * 9 + j)).setProblemNumber(sudokuArray[i][j]);
                }
            }
        }
    }

    public boolean isSolved(){
        for(var tile : gamePanel.getComponents()){
            if(tile.getBackground() == NumberTile.WARNING) return false;
        }
        return true;
    }

    public void saveRecode(){
        // TODO
        timerPanel.getComponent(0);
    }
}
