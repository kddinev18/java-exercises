package excercise6;

import java.util.Locale;

/** Solutions for the tasks in "javamethods.docx". */
public final class Exercise6 {
    private Exercise6() {
    }

    public static final class Triangle {
        private Triangle() {
        }

        public static double area(double a, double h) {
            return a > 0 && h > 0 ? a * h / 2.0 : -1;
        }
    }

    public static final class Point {
        private Point() {
        }

        public static double distance(double x1, double y1, double x2, double y2) {
            return Math.hypot(x2 - x1, y2 - y1);
        }
    }

    public static final class Employee {
        private Employee() {
        }

        public static void printEmployee(String firstName, String lastName, int age, String position) {
            if (age < 0) {
                System.out.println("Error: age cannot be negative");
                return;
            }
            System.out.println(formatEmployee(firstName, lastName, age, position));
        }

        public static String formatEmployee(String firstName, String lastName, int age, String position) {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            return "Name: %s %s%nAge: %d%nPosition: %s"
                    .formatted(firstName, lastName, age, position);
        }
    }

    public static final class Trip {
        private static final double SINGLE_BED_PRICE = 50.0;

        private Trip() {
        }

        public static double calcPrice(int days, int beds, double ticket) {
            if (days < 1 || beds < 1 || ticket < 0) {
                throw new IllegalArgumentException("Days and beds must be positive; ticket must not be negative");
            }
            int nights = Math.max(0, days - 1);
            return SINGLE_BED_PRICE * beds * nights + 2 * ticket;
        }
    }

    public static final class Order {
        private static final double DELIVERY_PRICE = 5.0;

        private Order() {
        }

        public static void calcPrice(double pizzaPrice, int pizzaAmount,
                                     double drinkPrice, int drinksAmount) {
            try {
                System.out.printf(Locale.ROOT, "Order price: %.2f%n",
                        calculatePrice(pizzaPrice, pizzaAmount, drinkPrice, drinksAmount));
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

        public static double calculatePrice(double pizzaPrice, int pizzaAmount,
                                            double drinkPrice, int drinksAmount) {
            if (pizzaPrice < 0 || pizzaAmount < 0 || drinkPrice < 0 || drinksAmount < 0) {
                throw new IllegalArgumentException("prices and quantities cannot be negative");
            }
            return pizzaPrice * pizzaAmount + drinkPrice * drinksAmount + DELIVERY_PRICE;
        }
    }

    public static final class Equation {
        private Equation() {
        }

        public static void makeEquation(double p, double q) {
            System.out.println(buildEquation(p, q));
        }

        public static String buildEquation(double p, double q) {
            double middleCoefficient = -(p + q);
            double constant = p * q;
            return "x^2 %s %.2fx %s %.2f = 0".formatted(
                    middleCoefficient < 0 ? "-" : "+", Math.abs(middleCoefficient),
                    constant < 0 ? "-" : "+", Math.abs(constant));
        }
    }

    public static final class Triplet {
        private Triplet() {
        }

        public static boolean checkP(int a, int b, int c) {
            return a > 0 && b > 0 && c > 0 && (long) c * c == (long) a * a + (long) b * b;
        }
    }

    public static final class Average {
        private Average() {
        }

        public static boolean checkAverage(double[] first, double[] second) {
            if (first.length != 10 || second.length != 10) {
                throw new IllegalArgumentException("Both arrays must contain exactly 10 elements");
            }
            return Math.abs(average(first) - average(second)) < 1e-9;
        }

        private static double average(double[] values) {
            double sum = 0;
            for (double value : values) {
                sum += value;
            }
            return sum / values.length;
        }
    }

    public static final class Series {
        private Series() {
        }

        /** One-based recursive Tribonacci sequence: 1, 1, 1, 3, 5, 9, 17, ... */
        public static long series(int n) {
            if (n < 1) {
                throw new IllegalArgumentException("n must be positive");
            }
            if (n <= 3) {
                return 1;
            }
            return Math.addExact(Math.addExact(series(n - 1), series(n - 2)), series(n - 3));
        }
    }

    public static void main(String[] args) {
        System.out.println("Triangle area: " + Triangle.area(6, 4));
        System.out.println("Point distance: " + Point.distance(0, 0, 3, 4));
        Employee.printEmployee("Ivan", "Ivanov", 34, "Manager");
        System.out.println("Trip price: " + Trip.calcPrice(5, 2, 80));
        Order.calcPrice(12, 2, 3, 3);
        Equation.makeEquation(2, 3);
        System.out.println("Pythagorean triplet: " + Triplet.checkP(3, 4, 5));
        double[] first = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] second = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("Equal averages: " + Average.checkAverage(first, second));
        System.out.println("Series element 7: " + Series.series(7));
    }
}

