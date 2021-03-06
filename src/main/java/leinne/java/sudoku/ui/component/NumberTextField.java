package leinne.java.sudoku.ui.component;

import com.formdev.flatlaf.ui.FlatBorder;
import leinne.java.sudoku.entity.NumberTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class NumberTextField extends JTextField{

    private static boolean changeDesign = false;

    public static final class Style{
        public static final Color FIXED_ODD = new Color(230, 230, 230);
        public static final Color WARNING_ODD = new Color(241, 137, 137);
        public static final Color DEFAULT_ODD = new Color(255, 255, 197);

        public static final Color FIXED_EVEN = new Color(200, 200, 200);
        public static final Color WARNING_EVEN = new Color(225, 102, 102);
        public static final Color DEFAULT_EVEN = new Color(255, 255, 255);

        public static final Font FIXED_FONT = new Font("Dialog", Font.BOLD, 30);
        public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 30);

        private Style(){}
    }

    /**
     * true: even
     * false: odd
     */
    private final boolean colorType;
    private final NumberTile numberTile;

    public NumberTextField(NumberTile tile){
        numberTile = tile;
        colorType = ((tile.row / 3 - 1) * 3 + tile.column / 3) % 2 == 0;

        tile.setTextField(this);
        setHorizontalAlignment(JTextField.CENTER);
        addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                e.consume();
            }

            public void keyReleased(KeyEvent e){
                var c = e.getKeyChar();
                if('1' <= c && c <= '9'){
                    numberTile.setNumber(c - 48);
                }else if(c == '0' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE){
                    numberTile.setNumber(0);
                }
            }
        });

        /*
         * Hack: Using the reflection class to change the design of the TextField
         */
        try{
            if(changeDesign) return;
            changeDesign = true;

            var field = FlatBorder.class.getDeclaredField("innerFocusWidth");
            field.setAccessible(true);
            field.set(getBorder(), 3);

            field = FlatBorder.class.getDeclaredField("borderColor");
            field.setAccessible(true);
            field.set(getBorder(), new Color(185, 185, 185));
        }catch(Exception e){}
    }

    public void updateStyle(){
        Color color;
        if(numberTile.getNestingCount() > 0){
            color = colorType ? Style.WARNING_EVEN : Style.WARNING_ODD;
        }else if(numberTile.isProblem()){
            color = colorType ? Style.FIXED_EVEN : Style.FIXED_ODD;
        }else{
            color = colorType ? Style.DEFAULT_EVEN : Style.DEFAULT_ODD;
        }
        setBackground(color);
        setCaretColor(color); // invisible cursor
        setFont(numberTile.isProblem() ? Style.FIXED_FONT : Style.DEFAULT_FONT);
    }
}
