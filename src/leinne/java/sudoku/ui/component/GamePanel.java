package leinne.java.sudoku.ui.component;

import leinne.java.sudoku.util.SudokuUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GamePanel extends JPanel{

    public GamePanel(LayoutManager layout){
        super(layout);
    }

    public Component add(Component comp){
        if(getComponentCount() != 81 && comp instanceof NumberTile){
            super.add(comp);
        }
        return comp;
    }

    public NumberTile getNumberTile(int index){
        return (NumberTile) getComponent(index);
    }

    public NumberTile getNumberTile(int row, int column){
        return (NumberTile) getComponent(row * 9 + column);
    }

    public NumberTile[] getNumberTiles(){
        var all = getComponents();
        return Arrays.copyOf(all, all.length, NumberTile[].class);
    }

    public void setSudokuProblem(String sudoku){
        var sudokuArray = SudokuUtils.parse(sudoku);
        if(!SudokuUtils.isValidProblem(sudokuArray)){
            return;
        }

        var create = getComponentCount() != 81;
        for(int row = 0; row < 9; ++row){
            for(int column = 0; column < 9; ++column){
                if(create){
                    add(new NumberTile(row * 9 + column, sudokuArray[row][column]));
                }else{
                    getNumberTile(row, column).setProblemNumber(sudokuArray[row][column]);
                }
            }
        }
    }
}
