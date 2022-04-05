package leinne.java.sudoku.ui.window;

import leinne.java.sudoku.SudokuSystem;
import leinne.java.sudoku.util.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public abstract class Window extends JFrame{

    protected boolean justCreate = true;

    public Window(){
        super("스도쿠");
        setSize(500, 600);
        setResizable(false);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                onClosing();
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    protected abstract void initDesign();

    public void open(){
        if(SudokuSystem.getInstance().getWindowManager().getCurrentWindow() != this){
            throw new RuntimeException("Window should only be managed through the WindowManager.");
        }

        if(justCreate){
            justCreate = false;
            initDesign();
        }
        setVisible(true);
    }

    public void close(){
        if(SudokuSystem.getInstance().getWindowManager().getCurrentWindow() != this){
            throw new RuntimeException("Window should only be managed through the WindowManager.");
        }

        setVisible(false);
    }

    public void onClosing(){
        if (Utils.showDialog(this, "게임종료", "정말로 게임을 종료하시겠습니까?") == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
