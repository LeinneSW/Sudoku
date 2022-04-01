package leinne.java.sudoku.util;

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
}
