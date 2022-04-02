package leinne.java.sudoku.ui.component;

import leinne.java.sudoku.SudokuSystem;
import leinne.java.sudoku.entity.NumberTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class NumberTextField extends JTextField{

    public static final class Style{
        public static final Color FIXED_ODD = new Color(227, 227, 227);
        public static final Color WARNING_ODD = new Color(241, 137, 137);
        public static final Color DEFAULT_ODD = new Color(255, 255, 197);

        public static final Color FIXED_EVEN = new Color(190, 190, 190);
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
                    SudokuSystem.getInstance().setNumber(numberTile, c - 48);
                }else if(c == '0' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE){
                    SudokuSystem.getInstance().setNumber(numberTile, 0);
                }
            }
        });
    }

    public void updateStyle(){
        Font font = Style.DEFAULT_FONT;
        Color color;
        if(numberTile.isProblem()){
            font = Style.FIXED_FONT;
            color = colorType ? Style.FIXED_EVEN : Style.FIXED_ODD;
        }else if(numberTile.getNestingCount() == 0){
            color = colorType ? Style.DEFAULT_EVEN : Style.DEFAULT_ODD;
        }else{
            color = colorType ? Style.WARNING_EVEN : Style.WARNING_ODD;
        }
        setFont(font);
        setBackground(color);
        setCaretColor(color); // invisible cursor
    }
}
