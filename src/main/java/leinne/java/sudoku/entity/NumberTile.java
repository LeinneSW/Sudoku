package leinne.java.sudoku.entity;

import leinne.java.sudoku.SudokuSystem;
import leinne.java.sudoku.ui.component.NumberTextField;
import leinne.java.sudoku.util.Utils;

import java.util.HashSet;

public class NumberTile{

    public final int row;
    public final int column;

    private int number = 0;
    private boolean problem = false;
    private NumberTextField textField = null;
    private final HashSet<Integer> nesting = new HashSet<>();

    public NumberTile(int row, int column){
        this.row = row;
        this.column = column;
    }

    public boolean isValid(){
        return SudokuSystem.getInstance().getNumberTile(row, column) == this;
    }

    public boolean isNesting(){
        return !nesting.isEmpty();
    }

    public boolean isProblem(){
        return problem;
    }

    public int getIndex(){
        return row * 9 + column;
    }

    public int getNumber(){
        return number;
    }

    public void setTextField(NumberTextField textField){
        this.textField = textField;
        syncTextField();
    }

    public void setNumber(int number){
        setNumber(number, false);
    }

    public void setNumber(int number, boolean isProblem){
        number = Utils.convertNumber(number);
        this.problem = isProblem && number > 0;
        SudokuSystem.getInstance().checkVerifyNumbers(this, number);
        this.number = number;

        if(textField != null){
            textField.setFocusable(!problem);
            textField.setText(this.number == 0 ? "" : this.number + "");
            textField.updateStyle();
        }
    }

    public void clear(){
        clear(false);
    }

    public void clear(boolean force){
        if(!problem || force){
            nesting.clear();
            setNumber(0);
        }
    }

    public void addNesting(int index){
        nesting.add(index);
        if(textField != null){
            textField.updateStyle();
        }
    }

    public void removeNesting(int index){
        nesting.remove(index);
        if(textField != null){
            textField.updateStyle();
        }
    }

    public int getNestingCount(){
        return nesting.size();
    }

    private void syncTextField(){
        if(textField == null){
            return;
        }

        textField.setFocusable(!problem);
        textField.setText(this.number == 0 ? "" : this.number + "");
        textField.updateStyle();
    }
}
