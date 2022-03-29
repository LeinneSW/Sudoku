package leinne.java.sudoku.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public final class NumberTile extends JTextField{

    public static final Color FIXED = new Color(215, 215, 215);
    public static final Color WARNING = new Color(227, 108, 108);

    private static final Font DEFAULT_FONT = new Font("Dialog", Font.BOLD, 22);

    private int number = 0;
    private final int myIndex;
    private final HashSet<Integer> nesting = new HashSet<>();

    public NumberTile(int index){
        this(index, 0);
    }

    public NumberTile(int index, int number){
        super();
        myIndex = index;
        setNumber(number);

        setFont(DEFAULT_FONT);
        setHorizontalAlignment(JTextField.CENTER);
        addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                e.consume();
            }

            public void keyReleased(KeyEvent e){
                var c = e.getKeyChar();
                if('1' <= c && c <= '9'){
                    changeNumber(c - 48);
                }else if(c == '0' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE){
                    changeNumber(0);
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

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
        setText(number <= 0 ? "" : (number > 9 ? "" : number + ""));
    }

    public void setProblemNumber(int number){
        setNumber(number);
        setFocusable(false);
    }

    public void setFocusable(boolean focusable){
        super.setFocusable(focusable);
        updateBackground();
    }

    private void changeNumber(int newNumber){
        for(int i = 0; i < 9; ++i){
            checkNesting(getRow() * 9 + i, newNumber); // i: 가로
            checkNesting(getColumn() + i * 9, newNumber); // i: 세로
        }

        for(int row = getRow() / 3 * 3, rowEnd = row + 3; row < rowEnd; ++row){
            for(int col = getColumn() / 3 * 3, colEnd = col + 3; col < colEnd; ++col){ // 3 * 3
                checkNesting(row * 9 + col, newNumber);
            }
        }
        setNumber(newNumber);
    }

    private void updateBackground(){
        if(!isFocusable()){
            setBackground(FIXED);
        }else if(nesting.isEmpty()){
            setBackground(Color.WHITE);
        }else{
            setBackground(WARNING);
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
