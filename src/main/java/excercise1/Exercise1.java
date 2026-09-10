package excercise1;

import java.util.ArrayList;
import java.util.List;

/** Solutions for the tasks in "Задачи за упражнение.docx". */
public final class Exercise1 {
    private Exercise1() {
    }

    public static double expression1() {
        return Math.pow(2.15, 3.1415927) + Math.pow(3.756, 2.134);
    }

    public static double expression2() {
        return Math.pow(3.1415, 3) - Math.pow(2.71828, 2);
    }

    public static double expression3() {
        return (Math.pow(2, 2.65) - Math.pow(1.27, 6.75))
                / (Math.pow(-2, 4) - Math.pow(2.7, 3.45));
    }

    public static double expression4() {
        return Math.pow(Math.pow(2.18, 2) + Math.pow(3.18, 7.15), 2.138 - 3.1);
    }

    public static double expression5() {
        return Math.pow(2, -1) + Math.pow(-3, -1);
    }

    public static boolean expression6() {
        return Math.pow(1 + 2 + 3, 2) == Math.pow(4 - 3 - 2, 2);
    }

    public static boolean expression7() {
        return 7 % 4 == 6 % 3;
    }

    public static boolean expression8() {
        return !(true && false);
    }

    public static boolean expression9() {
        return !true && !false;
    }

    public static int daysInMonth(int month) {
        return switch (month) {
            case 2 -> 28;
            case 4, 6, 9, 11 -> 30;
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            default -> throw new IllegalArgumentException("Month must be between 1 and 12");
        };
    }

    public static long sumTo(int n) {
        requirePositive(n);
        return (long) n * (n + 1) / 2;
    }

    public static long productTo(int n) {
        requirePositive(n);
        long product = 1;
        for (int number = 2; number <= n; number++) {
            product = Math.multiplyExact(product, number);
        }
        return product;
    }

    public static int countMultiplesOfSeven(int negativeNumber) {
        if (negativeNumber >= 0) {
            throw new IllegalArgumentException("The number must be negative");
        }
        int count = 0;
        for (int number = negativeNumber; number <= -1; number++) {
            if (number % 7 == 0) {
                count++;
            }
        }
        return count;
    }

    public static List<Integer> numbersWhoseSquareIsLessThan(int limit) {
        requirePositive(limit);
        List<Integer> result = new ArrayList<>();
        for (int number = 1; (long) number * number < limit; number++) {
            result.add(number);
        }
        return result;
    }

    /** Returns all numbers taking part in consecutive pairs 1+2, 2+3, ... whose sum is <= limit. */
    public static List<Integer> consecutivePairMembers(int limit) {
        requirePositive(limit);
        List<Integer> result = new ArrayList<>();
        int number = 1;
        while (number + (number + 1) <= limit) {
            if (result.isEmpty()) {
                result.add(number);
            }
            result.add(number + 1);
            number++;
        }
        return result;
    }

    public static String hollowTriangle(int rows, char symbol) {
        if (rows < 2) {
            throw new IllegalArgumentException("Rows must be at least 2");
        }
        StringBuilder triangle = new StringBuilder();
        for (int row = 1; row <= rows; row++) {
            if (row == rows) {
                triangle.append(String.valueOf(symbol).repeat(rows));
            } else if (row == 1) {
                triangle.append(symbol);
            } else {
                triangle.append(symbol).append(" ".repeat(row - 2)).append(symbol);
            }
            if (row < rows) {
                triangle.append(System.lineSeparator());
            }
        }
        return triangle.toString();
    }

    private static void requirePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("The number must be positive");
        }
    }

    public static void main(String[] args) {
        System.out.println("Expressions 1-9:");
        System.out.printf("%.8f%n", expression1());
        System.out.printf("%.8f%n", expression2());
        System.out.printf("%.8f%n", expression3());
        System.out.printf("%.8f%n", expression4());
        System.out.printf("%.8f%n", expression5());
        System.out.println(expression6());
        System.out.println(expression7());
        System.out.println(expression8());
        System.out.println(expression9());
        System.out.println("Days in February: " + daysInMonth(2));
        System.out.println("Sum to 10: " + sumTo(10));
        System.out.println("Product to 5: " + productTo(5));
        System.out.println("Multiples of 7 from -30 to -1: " + countMultiplesOfSeven(-30));
        System.out.println("Squares below 30: " + numbersWhoseSquareIsLessThan(30));
        System.out.println("Consecutive pair members for 17: " + consecutivePairMembers(17));
        System.out.println(hollowTriangle(6, 'o'));
    }
}

