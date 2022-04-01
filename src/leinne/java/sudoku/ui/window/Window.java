package leinne.java.sudoku.ui.window;

import javax.swing.*;

public abstract class Window extends JFrame{

    public Window(){
        super("스도쿠");
        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void open(){
        setVisible(true);
    }

    public void close(){
        setVisible(false);
    }
}
