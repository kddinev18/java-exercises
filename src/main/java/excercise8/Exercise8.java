package excercise8;

import java.io.Serializable;
import java.util.List;

/** Solutions for the tasks in "javamultiinherianceexercise.docx". */
public final class Exercise8 {
    private Exercise8() {
    }

    public static class Animal implements Serializable {
        protected String species;
        protected String animalClass;

        public Animal(String species, String animalClass) {
            this.species = species;
            this.animalClass = animalClass;
        }

        public void show() {
            System.out.printf("Species: %s, class: %s%n", species, animalClass);
        }
    }

    public static class Cat extends Animal {
        protected String breed;

        public Cat(String breed) {
            super("cat", "mammal");
            this.breed = breed;
        }

        @Override
        public void show() {
            System.out.printf("Species: %s, class: %s, breed: %s%n",
                    species, animalClass, breed);
        }
    }

    public abstract static class NamedCat extends Cat {
        protected String name;
        protected int age;

        protected NamedCat(String breed, String name, int age) {
            super(breed);
            if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
            this.name = name;
            this.age = age;
        }

        @Override
        public void show() {
            System.out.printf("Species: %s, class: %s, breed: %s, name: %s, age: %d%n",
                    species, animalClass, breed, name, age);
        }
    }

    public static final class SiameseCat extends NamedCat {
        public SiameseCat(String name, int age) { super("Siamese", name, age); }
    }

    public static final class PersianCat extends NamedCat {
        public PersianCat(String name, int age) { super("Persian", name, age); }
    }

    public static final class MaineCoonCat extends NamedCat {
        public MaineCoonCat(String name, int age) { super("Maine Coon", name, age); }
    }

    public static class Account implements Serializable {
        protected String holderNames;
        protected String holderEgn;
        protected String currency;
        protected String type;

        public Account(String holderNames, String holderEgn, String currency, String type) {
            this.holderNames = holderNames;
            this.holderEgn = holderEgn;
            this.currency = currency;
            this.type = type;
        }

        public void showDetails() {
            System.out.printf("Holder: %s, EGN: %s, currency: %s, type: %s%n",
                    holderNames, holderEgn, currency, type);
        }
    }

    public static class SavingAccount extends Account {
        protected double minimumSum;
        protected double tax;
        protected String status;

        public SavingAccount(String holderNames, String holderEgn, String currency,
                             double minimumSum, double tax, String status) {
            super(holderNames, holderEgn, currency, "saving");
            this.minimumSum = nonNegative(minimumSum, "minimum sum");
            this.tax = nonNegative(tax, "tax");
            this.status = status;
        }

        @Override
        public void showDetails() {
            super.showDetails();
            System.out.printf("Minimum sum: %.2f, tax: %.2f, status: %s%n",
                    minimumSum, tax, status);
        }
    }

    public static class PaymentAccount extends Account {
        protected double minimumSum;
        protected double tax;
        protected String status;

        public PaymentAccount(String holderNames, String holderEgn, String currency,
                              double minimumSum, double tax, String status) {
            super(holderNames, holderEgn, currency, "payment");
            this.minimumSum = nonNegative(minimumSum, "minimum sum");
            this.tax = nonNegative(tax, "tax");
            this.status = status;
        }

        @Override
        public void showDetails() {
            super.showDetails();
            System.out.printf("Minimum sum: %.2f, tax: %.2f, status: %s%n",
                    minimumSum, tax, status);
        }
    }

    public static final class BalanceS extends SavingAccount {
        private double amount;

        public BalanceS(String holderNames, String holderEgn, String currency,
                        double minimumSum, double tax, String status, double amount) {
            super(holderNames, holderEgn, currency, minimumSum, tax, status);
            this.amount = nonNegative(amount, "amount");
        }

        public void showBalance() { System.out.printf("Balance: %.2f %s%n", amount, currency); }
        public void depositMoney(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
            this.amount += amount;
        }
        public double getAmount() { return amount; }
    }

    public static final class BalanceP extends PaymentAccount {
        private double amount;

        public BalanceP(String holderNames, String holderEgn, String currency,
                        double minimumSum, double tax, String status, double amount) {
            super(holderNames, holderEgn, currency, minimumSum, tax, status);
            this.amount = nonNegative(amount, "amount");
        }

        public void showBalance() { System.out.printf("Balance: %.2f %s%n", amount, currency); }
        public void depositMoney(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
            this.amount += amount;
        }
        public void pay(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("Payment must be positive");
            if (this.amount - amount < minimumSum) {
                throw new IllegalStateException("Payment would reduce the balance below the minimum");
            }
            this.amount -= amount;
        }
        public double getAmount() { return amount; }
    }

    public static class Vessel implements Serializable {
        protected String type;
        public Vessel(String type) { this.type = type; }
    }

    public static class Ship extends Vessel {
        public Ship(String type) { super(type); }
    }

    public static final class CargoShip extends Ship {
        private final String company;
        private final double capacity;
        private final String name;
        public CargoShip(String company, double capacity, String name) {
            super("cargo ship");
            this.company = company;
            this.capacity = nonNegative(capacity, "capacity");
            this.name = name;
        }
        public void show() {
            System.out.printf("%s %s, company: %s, capacity: %.2f tons%n",
                    type, name, company, capacity);
        }
    }

    public static final class PassengerShip extends Ship {
        private final String company;
        private final int capacity;
        private final String name;
        public PassengerShip(String company, int capacity, String name) {
            super("passenger ship");
            if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be negative");
            this.company = company;
            this.capacity = capacity;
            this.name = name;
        }
        public void show() {
            System.out.printf("%s %s, company: %s, capacity: %d passengers%n",
                    type, name, company, capacity);
        }
    }

    private static double nonNegative(double value, String fieldName) {
        if (value < 0) throw new IllegalArgumentException(fieldName + " cannot be negative");
        return value;
    }

    public static void main(String[] args) {
        List<NamedCat> cats = List.of(new SiameseCat("Luna", 3),
                new PersianCat("Milo", 4), new MaineCoonCat("Leo", 5));
        cats.forEach(Animal::show);

        BalanceS saving = new BalanceS("Ivan Ivanov", "0000000000", "BGN", 100, 2, "active", 500);
        BalanceP payment = new BalanceP("Maria Petrova", "1111111111", "EUR", 0, 1, "active", 300);
        saving.showDetails();
        saving.depositMoney(100);
        saving.showBalance();
        payment.showDetails();
        payment.depositMoney(50);
        payment.pay(120);
        payment.showBalance();

        new CargoShip("Ocean Cargo", 50_000, "Atlas").show();
        new PassengerShip("Sea Travel", 2_500, "Aurora").show();
    }
}

