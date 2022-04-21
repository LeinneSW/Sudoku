package leinne.java.sudoku.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintPasswordField extends JPasswordField{

    private boolean showingHint = true;

    public HintPasswordField(final String hint){
        super(hint);
        setEchoChar('\0');
        setForeground(Color.GRAY);

        addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                if(getPassword().length < 1){
                    setText("");
                    setEchoChar('•');
                    showingHint = false;
                    setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e){
                if(getPassword().length < 1) {
                    setEchoChar('\0');
                    setText(hint);
                    showingHint = true;
                    setForeground(Color.GRAY);
                }
            }
        });
    }

    public char[] getPassword(){
        return showingHint ? new char[0] : super.getPassword();
    }
}
