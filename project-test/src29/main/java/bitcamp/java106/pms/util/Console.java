package bitcamp.java106.pms.util;

import java.util.Scanner;

public class Console {
    public static Scanner keyScan;
    
    public static boolean confirm(String message) {
        System.out.printf("%s (y/N)", message);
        String input = keyScan.nextLine().toLowerCase();
        if (input.equals("y"))
            return true;
        else 
            return false;
    }
    
    public static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }
}
