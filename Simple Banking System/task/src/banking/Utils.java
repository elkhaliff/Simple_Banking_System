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

    public static boolean isLuhn(String pan) {
        int result = 0;
        for (int i = 0; i < pan.length(); i++) {
            int n = Character.getNumericValue(pan.charAt(i));
            if (i % 2 == 0) {
                int dblN = n * 2;
                dblN = dblN > 9 ? dblN - 9 : n * 2;
                result += dblN;
            } else
                result += n;
        }
        return result % 10 == 0;
    }
}
