package main.java.util;

public class Utils {

    private Utils(){}

    public static boolean isDarkTile(int row, int col){
        return (row%2==0 && col%2==0) || (row%2==1 && col%2==1);
    }
}
