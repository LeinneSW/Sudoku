package leinne.java.sudoku.util;

import javax.swing.*;
import java.awt.*;

public class Utils{

    public static int parseInt(String num){
        try{
            return convertNumber(Integer.parseInt(num));
        }catch(NumberFormatException e){
            return 0;
        }
    }

    public static int convertNumber(int number){
        return number > 9 ? 0 : Math.max(number, 0);
    }

    public static int showYesNoDialog(String title, String message){
        return showYesNoDialog(null, title, message);
    }

    public static int showYesNoDialog(Component parent, String title, String message){
        return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
