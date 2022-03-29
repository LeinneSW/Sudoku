package leinne.java.sudoku.util;

public class Utils{

    public static int parseInt(String num){
        try{
            var number = Integer.parseInt(num);
            return number > 9 ? 0 : Math.max(number, 0);
        }catch(NumberFormatException e){
            return 0;
        }
    }
}
