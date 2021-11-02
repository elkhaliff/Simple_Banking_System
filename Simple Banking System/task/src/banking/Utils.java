package banking;

import java.util.Scanner;

public class Utils {

    public static void println(String string) { System.out.println(string); }

    public static int getInt(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string + " ");
        return scanner.nextInt();
    }

    public static String getString(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string + " ");
        return scanner.nextLine();
    }
}
