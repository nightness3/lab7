package client.utility;

import java.util.Scanner;

public class Console {

    private Console() {
    }

    private static class ConsoleHolder {
        public static final Console HOLDER_INSTANCE = new Console();
    }

    public static Console getInstance() {
        return ConsoleHolder.HOLDER_INSTANCE;
    }

    public String getString(Scanner scanner, String inputName) {
        System.out.print("Введите " + inputName + " (String): ");
        String string = scanner.nextLine();
        return string;
    }

    public int getInt(Scanner scanner, String inputName) {
        System.out.print("Введите " + inputName + " (int): ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return getInt(scanner, inputName);
        }
    }

    public long getLong(Scanner scanner, String inputName) {
        System.out.print("Введите " + inputName + " (long): ");
        String input = scanner.nextLine();
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            return getLong(scanner, inputName);
        }
    }

    public double getDouble(Scanner scanner, String inputName) {
        System.out.print("Введите " + inputName + " (double): ");
        String input = scanner.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return getDouble(scanner, inputName);
        }
    }

    public float getFloat(Scanner scanner, String inputName) {
        System.out.print("Введите " + inputName + " (float): ");
        String input = scanner.nextLine();
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            return getFloat(scanner, inputName);
        }
    }

}
