package leinne.java.sudoku.ui.window;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class Window extends JFrame{

    public Window(){
        super("스도쿠");
        setSize(500, 600);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                onClosing();
            }
        });
    }

    public void open(){
        setVisible(true);
    }

    public void close(){
        setVisible(false);
    }

    public void onClosing(){
        if (JOptionPane.showConfirmDialog(this,
                "정말로 게임을 종료하시겠습니까?",
                "게임 종료",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
