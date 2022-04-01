package leinne.java.sudoku.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public final class NumberTile extends JTextField{

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

    private int number = 0;
    private final int myIndex;
    private final HashSet<Integer> nesting = new HashSet<>();

    public NumberTile(int index, int problemNumber){
        super();
        myIndex = index;
        updateBackground();
        setProblemNumber(problemNumber);
        setHorizontalAlignment(JTextField.CENTER);
        addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                e.consume();
            }

            public void keyReleased(KeyEvent e){
                var c = e.getKeyChar();
                if('1' <= c && c <= '9'){
                    setNumber(c - 48);
                }else if(c == '0' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE){
                    setNumber(0);
                }
            }
        });
    }

    public boolean isNesting(){
        return !nesting.isEmpty();
    }

    public int getRow(){
        return myIndex / 9;
    }

    public int getColumn(){
        return myIndex % 9;
    }

    /**
     * This method is added for code intuition.
     */
    public boolean isProblemNumber(){
        return !isFocusable();
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        setNumber(number, true);
    }

    public void setNumber(int number, boolean verify){
        number = Math.max(number > 9 ? 0 : number, 0);
        if(verify){
            for(int i = 0; i < 9; ++i){
                checkNesting(getRow() * 9 + i, number); // i: 가로
                checkNesting(getColumn() + i * 9, number); // i: 세로
            }

            for(int row = getRow() / 3 * 3, rowEnd = row + 3; row < rowEnd; ++row){
                for(int col = getColumn() / 3 * 3, colEnd = col + 3; col < colEnd; ++col){ // 3 * 3
                    checkNesting(row * 9 + col, number);
                }
            }
        }else if(number == 0){
            nesting.clear();
            updateBackground();
        }
        this.number = number;
        setText(this.number == 0 ? "" : this.number + "");
    }

    public void setProblemNumber(int number){
        if(number < 1 || number > 9){
            setFont(Style.DEFAULT_FONT);
            return;
        }

        setFocusable(false);
        setNumber(number, false);
        setFont(Style.FIXED_FONT);
    }

    public void setFocusable(boolean focusable){
        super.setFocusable(focusable);
        updateBackground();
    }

    private void updateBackground(){
        var isEven = ((getRow() / 3 - 1) * 3 + getColumn() / 3) % 2 == 0;
        if(!isFocusable()){
            setBackground(isEven ? Style.FIXED_EVEN : Style.FIXED_ODD);
        }else if(nesting.isEmpty()){
            setBackground(isEven ? Style.DEFAULT_EVEN : Style.DEFAULT_ODD);
        }else{
            setBackground(isEven ? Style.WARNING_EVEN : Style.WARNING_ODD);
        }
    }

    private void addNesting(int index){
        nesting.add(index);
        updateBackground();
    }

    private void removeNesting(int index){
        nesting.remove(index);
        updateBackground();
    }

    private void checkNesting(int index, int newNumber){
        if(myIndex == index){
            return;
        }

        var otherTile = (NumberTile) getParent().getComponent(index);
        if(newNumber > 0 && newNumber == otherTile.number){
            addNesting(index);
            otherTile.addNesting(myIndex);
        }else if(otherTile.number == number){
            removeNesting(index);
            otherTile.removeNesting(myIndex);
        }
    }
}
