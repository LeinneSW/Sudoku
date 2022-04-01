package leinne.java.sudoku.ui.window;

import leinne.java.sudoku.SudokuSystem;
import leinne.java.sudoku.ui.component.GamePanel;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class InGameWindow extends Window{

    private static final String TIMER_FORMAT = "%02d:%02d:%02d";

    private final Timer timer = new Timer(1000, null);
    private final AtomicInteger time = new AtomicInteger();
    private final GamePanel gamePanel = new GamePanel(new GridLayout(9, 9, 0, 0));

    public InGameWindow(){
        var main = getContentPane();
        main.setLayout(new BorderLayout());

        var topPanel = new JPanel();
        main.add(topPanel, BorderLayout.NORTH);
        main.add(gamePanel, BorderLayout.CENTER);

        // create timer, menu button
        var lblTimer = new JLabel("00:00:00");
        lblTimer.setFont(new Font("Dialog", Font.BOLD, 32));
        topPanel.add(lblTimer);

        timer.addActionListener((event) -> {
            var t = time.incrementAndGet();
            lblTimer.setText(String.format(TIMER_FORMAT, t / 3600, t / 60, t % 60));
        });
        timer.start();
    }

    public void open(){
        super.open();
        timer.start();
    }

    public void close(){
        super.close();
        time.set(0);
        timer.stop();
    }

    public void onClosing(){
        SudokuSystem.getInstance().getWindowManager().setCurrentWindow(new SelectProblemWindow());
    }

    public int getTime(){
        return time.get();
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public void saveRecode(){
        // TODO
    }
}
