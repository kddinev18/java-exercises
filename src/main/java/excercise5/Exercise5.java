package excercise5;

import java.time.YearMonth;
import java.util.List;

/** Solutions for the tasks in "javaconstr.docx". */
public final class Exercise5 {
    private Exercise5() {
    }

    public static final class Rational {
        private int numerator;
        private int denominator;

        public Rational(int numerator, int denominator) {
            setValues(numerator, denominator);
        }

        public Rational(Rational other) {
            this(other.numerator, other.denominator);
        }

        public Rational add(Rational other) {
            return new Rational(numerator * other.denominator + other.numerator * denominator,
                    denominator * other.denominator);
        }

        public Rational subtract(Rational other) {
            return new Rational(numerator * other.denominator - other.numerator * denominator,
                    denominator * other.denominator);
        }

        public int getNumerator() { return numerator; }
        public int getDenominator() { return denominator; }
        public void setNumerator(int numerator) { setValues(numerator, denominator); }
        public void setDenominator(int denominator) { setValues(numerator, denominator); }

        private void setValues(int numerator, int denominator) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator must not be zero");
            }
            int sign = denominator < 0 ? -1 : 1;
            int divisor = gcd(Math.abs(numerator), Math.abs(denominator));
            this.numerator = sign * numerator / divisor;
            this.denominator = Math.abs(denominator) / divisor;
        }

        @Override
        public String toString() { return numerator + "/" + denominator; }
    }

    public static final class Point {
        private double x;
        private double y;

        public Point(double x, double y) { this.x = x; this.y = y; }
        public Point(Point other) { this(other.x, other.y); }
        public double getX() { return x; }
        public void setX(double x) { this.x = x; }
        public double getY() { return y; }
        public void setY(double y) { this.y = y; }
        public double distanceTo(Point other) { return Math.hypot(x - other.x, y - other.y); }
    }

    public static final class Student {
        private String firstName;
        private String lastName;
        private double grade;
        private String gender;

        public Student(String firstName, String lastName, double grade, String gender) {
            setFirstName(firstName);
            setLastName(lastName);
            setGrade(grade);
            setGender(gender);
        }

        public Student(Student other) {
            this(other.firstName, other.lastName, other.grade, other.gender);
        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = requireText(firstName, "First name"); }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = requireText(lastName, "Last name"); }
        public double getGrade() { return grade; }
        public void setGrade(double grade) { this.grade = grade; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = requireText(gender, "Gender"); }

        @Override
        public String toString() {
            return "%s %s, grade %.2f, gender %s".formatted(firstName, lastName, grade, gender);
        }
    }

    public static final class Triangle {
        private double a;
        private double b;
        private double c;

        public Triangle(double a, double b, double c) { setSides(a, b, c); }
        public Triangle(Triangle other) { this(other.a, other.b, other.c); }
        public double getA() { return a; }
        public double getB() { return b; }
        public double getC() { return c; }
        public void setA(double a) { setSides(a, b, c); }
        public void setB(double b) { setSides(a, b, c); }
        public void setC(double c) { setSides(a, b, c); }

        public double area() {
            double semiperimeter = (a + b + c) / 2;
            return Math.sqrt(semiperimeter * (semiperimeter - a)
                    * (semiperimeter - b) * (semiperimeter - c));
        }

        private void setSides(double a, double b, double c) {
            if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
                throw new IllegalArgumentException("The sides cannot form a triangle");
            }
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static final class Time {
        private int hour;
        private int minute;
        private int second;
        private int millisecond;

        public Time(int hour, int minute, int second, int millisecond) {
            setHour(hour);
            setMinute(minute);
            setSecond(second);
            setMillisecond(millisecond);
        }

        public Time(Time other) { this(other.hour, other.minute, other.second, other.millisecond); }
        public int getHour() { return hour; }
        public void setHour(int hour) { this.hour = range(hour, 0, 23, "hour"); }
        public int getMinute() { return minute; }
        public void setMinute(int minute) { this.minute = range(minute, 0, 59, "minute"); }
        public int getSecond() { return second; }
        public void setSecond(int second) { this.second = range(second, 0, 59, "second"); }
        public int getMillisecond() { return millisecond; }
        public void setMillisecond(int millisecond) { this.millisecond = range(millisecond, 0, 999, "millisecond"); }

        public void print() { System.out.println(this); }
        @Override public String toString() {
            return "%02d:%02d:%02d.%03d".formatted(hour, minute, second, millisecond);
        }
    }

    public static final class Date {
        private int dayOfMonth;
        private int month;
        private String dayOfWeek;
        private int year;

        public Date(int dayOfMonth, int month, String dayOfWeek, int year) {
            setDate(dayOfMonth, month, dayOfWeek, year);
        }

        public Date(Date other) { this(other.dayOfMonth, other.month, other.dayOfWeek, other.year); }
        public int getDayOfMonth() { return dayOfMonth; }
        public int getMonth() { return month; }
        public String getDayOfWeek() { return dayOfWeek; }
        public int getYear() { return year; }
        public void setDayOfMonth(int day) { setDate(day, month, dayOfWeek, year); }
        public void setMonth(int month) { setDate(dayOfMonth, month, dayOfWeek, year); }
        public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = requireText(dayOfWeek, "Day of week"); }
        public void setYear(int year) { setDate(dayOfMonth, month, dayOfWeek, year); }

        private void setDate(int day, int month, String dayOfWeek, int year) {
            if (year < 1 || month < 1 || month > 12
                    || day < 1 || day > YearMonth.of(year, month).lengthOfMonth()) {
                throw new IllegalArgumentException("Invalid date");
            }
            this.dayOfMonth = day;
            this.month = month;
            this.dayOfWeek = requireText(dayOfWeek, "Day of week");
            this.year = year;
        }

        public void print() { System.out.println(this); }
        @Override public String toString() {
            return "%s, %02d.%02d.%04d".formatted(dayOfWeek, dayOfMonth, month, year);
        }
    }

    public static final class Receipt {
        private Date date;
        private double amount;
        private double tax;

        public Receipt(Date date, double amount, double tax) {
            setDate(date);
            setAmount(amount);
            setTax(tax);
        }

        public Receipt(Receipt other) { this(new Date(other.date), other.amount, other.tax); }
        public Date getDate() { return new Date(date); }
        public void setDate(Date date) {
            if (date == null) throw new IllegalArgumentException("Date must not be null");
            this.date = new Date(date);
        }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = nonNegative(amount, "amount"); }
        public double getTax() { return tax; }
        public void setTax(double tax) { this.tax = nonNegative(tax, "tax"); }
        public void print() { System.out.printf("Receipt: %s, amount %.2f, tax %.2f%n", date, amount, tax); }
    }

    public static final class Laptop {
        private int ram;
        private double cpuClock;
        private String cpuModel;
        private String gpuModel;

        public Laptop(int ram, double cpuClock, String cpuModel, String gpuModel) {
            setRam(ram);
            setCpuClock(cpuClock);
            setCpuModel(cpuModel);
            setGpuModel(gpuModel);
        }

        public Laptop(Laptop other) { this(other.ram, other.cpuClock, other.cpuModel, other.gpuModel); }
        public int getRam() { return ram; }
        public void setRam(int ram) {
            if (ram <= 0) throw new IllegalArgumentException("RAM must be positive");
            this.ram = ram;
        }
        public double getCpuClock() { return cpuClock; }
        public void setCpuClock(double cpuClock) {
            if (cpuClock <= 0) throw new IllegalArgumentException("CPU clock must be positive");
            this.cpuClock = cpuClock;
        }
        public String getCpuModel() { return cpuModel; }
        public void setCpuModel(String cpuModel) { this.cpuModel = requireText(cpuModel, "CPU model"); }
        public String getGpuModel() { return gpuModel; }
        public void setGpuModel(String gpuModel) { this.gpuModel = requireText(gpuModel, "GPU model"); }

        public static int compare(Laptop first, Laptop second) {
            int byRam = Integer.compare(first.ram, second.ram);
            return byRam != 0 ? byRam : Double.compare(first.cpuClock, second.cpuClock);
        }

        public static String betterLaptop(Laptop first, Laptop second) {
            int comparison = compare(first, second);
            return comparison == 0 ? "The laptops are equally good"
                    : comparison > 0 ? "The first laptop is better" : "The second laptop is better";
        }
    }

    public static final class Delivery {
        private final double packageWeight;
        private final double packagePrice;
        private final boolean urgent;

        public Delivery(double packageWeight, double packagePrice, boolean urgent) {
            this.packageWeight = nonNegative(packageWeight, "package weight");
            this.packagePrice = nonNegative(packagePrice, "package price");
            this.urgent = urgent;
        }

        public void print() {
            System.out.printf("Delivery: weight %.2f, price %.2f, urgent %s%n",
                    packageWeight, packagePrice, urgent);
        }
    }

    public static final class Ship {
        private final String type;
        private final double weight;
        private final double length;
        private final String cargoType;
        private final String companyName;

        public Ship(String type, double weight, double length, String cargoType, String companyName) {
            this.type = requireText(type, "Type");
            this.weight = nonNegative(weight, "weight");
            this.length = nonNegative(length, "length");
            this.cargoType = requireText(cargoType, "Cargo type");
            this.companyName = requireText(companyName, "Company name");
        }

        public void print() {
            System.out.printf("Ship: %s, weight %.2f, length %.2f, cargo %s, company %s%n",
                    type, weight, length, cargoType, companyName);
        }
    }

    public static final class User {
        private final String userName;
        private final String password;
        private final String registrationDate;
        private final String gender;

        public User(String userName, String password, String registrationDate, String gender) {
            this.userName = requireText(userName, "Username");
            this.password = requireText(password, "Password");
            this.registrationDate = requireText(registrationDate, "Registration date");
            this.gender = requireText(gender, "Gender");
        }

        public void print() {
            System.out.printf("User: %s, registered %s, gender %s%n", userName, registrationDate, gender);
        }
    }

    private static int gcd(int first, int second) {
        while (second != 0) {
            int remainder = first % second;
            first = second;
            second = remainder;
        }
        return first == 0 ? 1 : first;
    }

    private static int range(int value, int minimum, int maximum, String fieldName) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("Invalid " + fieldName);
        }
        return value;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }

    private static double nonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must not be negative");
        }
        return value;
    }

    public static void main(String[] args) {
        Rational rational = new Rational(1, 2);
        System.out.println("Rational copy and sum: " + new Rational(rational).add(new Rational(1, 3)));
        System.out.println("Distance: " + new Point(0, 0).distanceTo(new Point(3, 4)));
        List<Student> students = List.of(
                new Student("Ivan", "Ivanov", 5.50, "male"),
                new Student("Maria", "Petrova", 5.75, "female"),
                new Student("Georgi", "Georgiev", 4.90, "male"),
                new Student("Elena", "Dimitrova", 6.00, "female"));
        students.forEach(System.out::println);
        System.out.printf("Triangle area: %.2f%n", new Triangle(3, 4, 5).area());
        new Time(14, 5, 9, 17).print();
        Date date = new Date(10, 9, "Thursday", 2026);
        date.print();
        new Receipt(date, 120, 24).print();
        Laptop first = new Laptop(16, 3.2, "CPU A", "GPU A");
        Laptop second = new Laptop(8, 4.0, "CPU B", "GPU B");
        System.out.println(Laptop.betterLaptop(first, second));
        new Delivery(2.5, 15.0, true).print();
        new Ship("Cargo", 50_000, 180, "Containers", "Example Shipping").print();
        new User("student", "secret", "2026-09-10", "unspecified").print();
    }
}

