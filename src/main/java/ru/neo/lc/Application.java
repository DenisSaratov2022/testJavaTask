package ru.neo.lc;

import java.util.*;

public class Application {
    private static final Map<Integer, String> constants = new HashMap<>();

    static {
        constants.put(10, "a");
        constants.put(11, "b");
        constants.put(12, "c");
        constants.put(13, "d");
        constants.put(14, "e");
        constants.put(15, "f");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите число:");
            System.out.println("1 -  конвертация числа из 10-ричной системы счисления в 16-ричную.");
            System.out.println("2 -  конвертация числа из 10-ой в 2-ую");
            System.out.println("3 -  конвертация числа из 2-ой в 10-ую");
            System.out.println("-1 - для выхода");

            try {
                int inputNumber = scanner.nextInt();
                switch (inputNumber) {
                    case 1:
                    callConvertFromDecimalToHex(scanner);
                    break;
                    case 2:
                        callConvertFromDecimalToBinary(scanner);
                        break;
                    case 3:
                        callConvertFromBinaryToDecimal(scanner);
                        break;
                    case -1:
                        return;
                    default:
                        System.out.println("Вы ввели неккоректное число");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Вы ввели неккоректное число");
                scanner.next();
            }
        }
    }

    private static void callConvertFromBinaryToDecimal(Scanner scanner) {
        System.out.println("Введите число в двоичной системе счисления");

        try {
            String inputNumber = scanner.next();
            System.out.println("Результат: " + convertFromBinaryToDecimal(inputNumber));
            System.out.println("Эталонный результат: " + Integer.parseInt(inputNumber, 2));
        }
        catch (InputMismatchException e) {
            System.out.println("Вы ввели неккоректное число");
            scanner.next();
        }
        catch (Exception e){
            System.out.println("Вы ввели неккоректное число");
        }
    }

    private static void callConvertFromDecimalToBinary(Scanner scanner) {
        System.out.println("Введите число от 0 до 10000.");
        try {
            int inputNumber = scanner.nextInt();
            System.out.println("Результат: " + convertFromDecimalToBinary(inputNumber));
            System.out.println("Эталонный результат: " + Integer.toBinaryString(inputNumber));
        }
        catch (InputMismatchException e) {
            System.out.println("Вы ввели неккоректное число");
            scanner.next();
        }
    }

    private static void callConvertFromDecimalToHex(Scanner scanner) {
        System.out.println("Введите число от 0 до 10000.");
        try {
            int inputNumber = scanner.nextInt();
            System.out.println("Результат: " + convertFromDecimalToHex(inputNumber));
            System.out.println("Эталонный результат: " + Integer.toHexString(inputNumber));
        }
        catch (InputMismatchException e) {
            System.out.println("Вы ввели неккоректное число");
            scanner.next();
        }
    }

    public static String convertFromDecimalToHex(int number) {

        int wholePart = number;
        StringBuilder desiredNumber = new StringBuilder();

        do {
            int residualPart = wholePart % 16;
            if (constants.containsKey(residualPart)) {
                desiredNumber.append(constants.get(residualPart));

            } else {
                desiredNumber.append(residualPart);
            }
            wholePart /= 16;
        } while (wholePart > 0);

        return desiredNumber.reverse().toString();
    }

    public static String convertFromDecimalToBinary(int number) {

        int wholePart = number;
        StringBuilder desiredNumber = new StringBuilder();

        do {
            int residualPart = wholePart % 2;
            desiredNumber.append(residualPart);
            wholePart /= 2;
        } while (wholePart > 0);

        return desiredNumber.reverse().toString();
    }

    public static int convertFromBinaryToDecimal(String number) {

        String[] arraySymbols = number.split("");

        int desiredNumber = 0;
        int degree = arraySymbols.length - 1;

        for (String arraySymbol : arraySymbols) {
            if (!arraySymbol.equals("0")&&!arraySymbol.equals("1")){
                throw new IllegalArgumentException();
            }
            desiredNumber += Integer.parseInt(arraySymbol) * (int) Math.pow(2, degree);
            degree -= 1;
        }

        return desiredNumber;

    }
}
