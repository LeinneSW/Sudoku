package leinne.java.sudoku.ui.component;

import leinne.java.sudoku.ui.component.listener.ClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickableLabel extends JLabel{

    private ClickListener clickListener = (e) -> {};

    public ClickableLabel(String text){
        super(text);
        setForeground(new Color(75, 75, 75));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("NanumGothic", Font.PLAIN, 16));
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                clickListener.mouseClicked(e);
            }
        });
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
}
