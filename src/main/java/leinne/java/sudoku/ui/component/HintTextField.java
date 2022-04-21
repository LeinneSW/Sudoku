package leinne.java.sudoku.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextField extends JTextField{

    private boolean showingHint = true;

    public HintTextField(final String hint){
        super(hint);
        setForeground(Color.GRAY);

        addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                if(getText().isEmpty()){
                    setText("");
                    showingHint = false;
                    setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e){
                if(getText().isEmpty()) {
                    setText(hint);
                    showingHint = true;
                    setForeground(Color.GRAY);
                }
            }
        });
    }

    public String getText(){
        return showingHint ? "" : super.getText();
    }
}
