package leinne.java.sudoku.ui.window;

import leinne.java.sudoku.SudokuSystem;
import leinne.java.sudoku.ui.component.GamePanel;

import java.awt.*;
import javax.swing.*;

public class InGameWindow extends Window{

    private static final String TIMER_FORMAT = "%02d:%02d:%02d'%02d              ";

    private long startTime;
    private final Timer timer = new Timer(20, null);
    private final GamePanel gamePanel = new GamePanel(new GridLayout(9, 9, 0, 0));

    protected void initDesign(){
        var main = getContentPane();
        main.setLayout(new BorderLayout());

        var topPanel = new JPanel();
        main.add(topPanel, BorderLayout.NORTH);
        main.add(gamePanel, BorderLayout.CENTER);

        // create timer, menu button
        var lblTimer = new JLabel("00:00:00'00              ");
        lblTimer.setFont(new Font("Dialog", Font.BOLD, 32));
        topPanel.add(lblTimer);

        timer.addActionListener((event) -> {
            var time = getTime();
            var second = time / 100;
            lblTimer.setText(String.format(TIMER_FORMAT, second / 3600, second / 60 % 60, second % 60, time % 100));
        });

        var btnClear = new JButton("초기화");
        btnClear.addActionListener((event) -> {
            for(var tile : gamePanel.getNumberTiles()){
                if(!tile.isProblemNumber()){
                    tile.setNumber(0, false);
                }
            }
        });
        topPanel.add(btnClear);

        var btnStop = new JButton("중단");
        btnStop.addActionListener((event) -> SudokuSystem.getInstance().getWindowManager().setCurrentWindow(new SelectProblemWindow()));
        topPanel.add(btnStop);
    }

    public void open(){
        super.open();
        startTime = System.nanoTime();
        timer.start();
    }

    public void close(){
        super.close();
        timer.stop();
    }

    public void onClosing(){
        SudokuSystem.getInstance().getWindowManager().setCurrentWindow(new SelectProblemWindow());
    }

    public long getTime(){
        return (System.nanoTime() - startTime) / 10000000;
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public void saveRecode(){
        // TODO
    }
}
