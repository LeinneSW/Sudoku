package leinne.java.sudoku.ui.screen;

import leinne.java.sudoku.ui.component.GamePanel;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class InGameScreen extends JFrame{

    private final GamePanel gamePanel = new GamePanel(new GridLayout(9, 9, 0, 0));

    public InGameScreen(){
        super("스도쿠");

        var main = getContentPane();
        main.setLayout(new BorderLayout());

        var topPanel = new JPanel();
        main.add(topPanel, BorderLayout.NORTH);
        main.add(gamePanel, BorderLayout.CENTER);

        // create timer, menu button
        var lblTimer = new JLabel("00:00:00");
        lblTimer.setFont(new Font("Dialog", Font.BOLD, 25));
        topPanel.add(lblTimer);

        AtomicInteger time = new AtomicInteger();
        var timer = new Timer(1000, (event) -> {
            var t = time.incrementAndGet();
            var hour = t / 3600;
            var minute = t / 60;
            var second = t % 60;
            lblTimer.setText(String.format("%02d:%02d:%02d", hour, minute, second));
        });
        timer.start();

        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public boolean isSolved(){
        for(var tile : gamePanel.getNumberTiles()){
            if(tile.isNesting()) return false;
        }
        return true;
    }

    public void saveRecode(){
        // TODO
    }
}
