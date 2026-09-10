package excercise4;

import java.time.YearMonth;
import java.util.List;

/** Solutions for the tasks in "javaclasses.docx". */
public final class Exercise4 {
    private Exercise4() {
    }

    public static final class Rational {
        private final int enumerator;
        private final int denominator;

        public Rational(int enumerator, int denominator) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator must not be zero");
            }
            int sign = denominator < 0 ? -1 : 1;
            int divisor = greatestCommonDivisor(Math.abs(enumerator), Math.abs(denominator));
            this.enumerator = sign * enumerator / divisor;
            this.denominator = Math.abs(denominator) / divisor;
        }

        public Rational add(Rational other) {
            return new Rational(
                    enumerator * other.denominator + other.enumerator * denominator,
                    denominator * other.denominator);
        }

        public Rational subtract(Rational other) {
            return new Rational(
                    enumerator * other.denominator - other.enumerator * denominator,
                    denominator * other.denominator);
        }

        public int enumerator() {
            return enumerator;
        }

        public int denominator() {
            return denominator;
        }

        @Override
        public String toString() {
            return enumerator + "/" + denominator;
        }
    }

    public record Point(double x, double y) {
        public double distanceTo(Point other) {
            return Math.hypot(x - other.x, y - other.y);
        }
    }

    public record Student(String firstName, String lastName, double grade, String gender) {
        public Student {
            requireText(firstName, "First name");
            requireText(lastName, "Last name");
            requireText(gender, "Gender");
        }
    }

    public static final class Triangle {
        private final double a;
        private final double b;
        private final double c;
        private final double alpha;
        private final double beta;
        private final double gamma;

        public Triangle(double a, double b, double c) {
            if (!canFormTriangle(a, b, c)) {
                throw new IllegalArgumentException("The sides cannot form a triangle");
            }
            this.a = a;
            this.b = b;
            this.c = c;
            alpha = angleOpposite(a, b, c);
            beta = angleOpposite(b, a, c);
            gamma = 180.0 - alpha - beta;
        }

        public static boolean canFormTriangle(double a, double b, double c) {
            return a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a;
        }

        private static double angleOpposite(double side, double adjacent1, double adjacent2) {
            double cosine = (adjacent1 * adjacent1 + adjacent2 * adjacent2 - side * side)
                    / (2 * adjacent1 * adjacent2);
            return Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, cosine))));
        }

        public double a() { return a; }
        public double b() { return b; }
        public double c() { return c; }
        public double alpha() { return alpha; }
        public double beta() { return beta; }
        public double gamma() { return gamma; }

        @Override
        public String toString() {
            return "Triangle{sides=[%.2f, %.2f, %.2f], angles=[%.2f, %.2f, %.2f]}"
                    .formatted(a, b, c, alpha, beta, gamma);
        }
    }

    public record Time(int hour, int minute, int second, int millisecond) {
        public Time {
            if (hour < 0 || hour > 23 || minute < 0 || minute > 59
                    || second < 0 || second > 59 || millisecond < 0 || millisecond > 999) {
                throw new IllegalArgumentException("Invalid time");
            }
        }

        @Override
        public String toString() {
            return "%02d:%02d:%02d.%03d".formatted(hour, minute, second, millisecond);
        }
    }

    public record Date(int dayOfMonth, int month, String dayOfWeek, int year) {
        public Date {
            requireText(dayOfWeek, "Day of week");
            if (year < 1 || month < 1 || month > 12
                    || dayOfMonth < 1 || dayOfMonth > YearMonth.of(year, month).lengthOfMonth()) {
                throw new IllegalArgumentException("Invalid date");
            }
        }

        @Override
        public String toString() {
            return "%s, %02d.%02d.%04d".formatted(dayOfWeek, dayOfMonth, month, year);
        }
    }

    public record Receipt(Date date, double amount, double tax) {
        public Receipt {
            if (date == null || amount < 0 || tax < 0) {
                throw new IllegalArgumentException("Invalid receipt");
            }
        }
    }

    public record Laptop(int ram, double cpuClock, String cpuModel, String gpuModel) {
        public Laptop {
            if (ram <= 0 || cpuClock <= 0) {
                throw new IllegalArgumentException("RAM and CPU clock must be positive");
            }
            requireText(cpuModel, "CPU model");
            requireText(gpuModel, "GPU model");
        }

        /** RAM has priority; CPU clock decides only when RAM is equal. */
        public static int compare(Laptop first, Laptop second) {
            int byRam = Integer.compare(first.ram, second.ram);
            return byRam != 0 ? byRam : Double.compare(first.cpuClock, second.cpuClock);
        }
    }

    private static int greatestCommonDivisor(int first, int second) {
        while (second != 0) {
            int remainder = first % second;
            first = second;
            second = remainder;
        }
        return first == 0 ? 1 : first;
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    public static void main(String[] args) {
        Rational first = new Rational(1, 2);
        Rational second = new Rational(1, 3);
        System.out.println("Rational sum: " + first.add(second));
        System.out.println("Rational difference: " + first.subtract(second));
        System.out.println("Distance: " + new Point(0, 0).distanceTo(new Point(3, 4)));
        List<Student> students = List.of(
                new Student("Ivan", "Ivanov", 5.50, "male"),
                new Student("Maria", "Petrova", 5.75, "female"),
                new Student("Georgi", "Georgiev", 4.90, "male"),
                new Student("Elena", "Dimitrova", 6.00, "female"));
        students.forEach(System.out::println);
        System.out.println(new Triangle(3, 4, 5));
        System.out.println(new Time(14, 5, 9, 17));
        Date date = new Date(10, 9, "Thursday", 2026);
        System.out.println(date);
        System.out.println(new Receipt(date, 120.00, 24.00));
        Laptop laptop1 = new Laptop(16, 3.2, "CPU A", "GPU A");
        Laptop laptop2 = new Laptop(8, 4.0, "CPU B", "GPU B");
        System.out.println("Laptop comparison: " + Laptop.compare(laptop1, laptop2));
    }
}

