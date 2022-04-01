package leinne.java.sudoku.ui.window;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public abstract class Window extends JFrame{

    protected boolean justCreate = true;

    public Window(){
        super("스도쿠");
        setSize(500, 600);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                onClosing();
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    protected abstract void initDesign();

    public void open(){
        if(justCreate){
            justCreate = false;
            initDesign();
        }
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
