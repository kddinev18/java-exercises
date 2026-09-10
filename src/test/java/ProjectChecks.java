import excercise1.Exercise1;
import excercise2.Exercise2;
import excercise3.Exercise3;
import excercise4.Exercise4;
import excercise5.Exercise5;
import excercise6.Exercise6;
import excercise7.Exercise7;
import excercise8.Exercise8;
import excercise9.Exercise9;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/** Lightweight checks runnable with java -ea; no test dependency is required. */
public final class ProjectChecks {
    private ProjectChecks() {
    }

    public static void main(String[] args) throws Exception {
        check(Exercise1.daysInMonth(2) == 28, "February days");
        check(Exercise1.sumTo(10) == 55, "sum to n");
        check(Exercise1.productTo(5) == 120, "product to n");
        check(Exercise1.countMultiplesOfSeven(-30) == 4, "negative multiples of seven");
        check(Exercise1.numbersWhoseSquareIsLessThan(30).equals(List.of(1, 2, 3, 4, 5)),
                "squares below limit");
        check(Exercise1.consecutivePairMembers(17).equals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                "consecutive pair members");

        int[] values = {1, 2, 3, 4};
        check(Arrays.equals(Exercise2.reverse(values), new int[]{4, 3, 2, 1}), "reverse array");
        check(Exercise2.statistics(values).sum() == 10, "array sum");
        check(Arrays.equals(Exercise2.positiveEvenNumbers(values), new int[]{2, 4}), "positive evens");
        check(Exercise2.countPrimes(new int[]{-1, 0, 1, 2, 3, 4, 5}) == 3, "prime count");
        check(Arrays.equals(Exercise2.adjacentPairSums(values), new int[]{3, 7}), "pair sums");

        Exercise3.SplitResult<Integer> split =
                Exercise3.splitAroundFirst(List.of(1, 4, 7, 6, 5, 2, 1, 3), 7);
        check(split.before().equals(List.of(1, 4)), "split before");
        check(split.after().equals(List.of(6, 5, 2, 1, 3)), "split after");
        check(Exercise3.polynomialValues(2).equals(List.of(-2, 0, 0)), "polynomial values");
        check(Exercise3.powersOfTwo(4).equals(List.of(1L, 2L, 4L, 8L, 16L)), "powers of two");

        Exercise4.Rational rational = new Exercise4.Rational(1, 2).add(new Exercise4.Rational(1, 3));
        check(rational.enumerator() == 5 && rational.denominator() == 6, "class rational sum");
        check(Math.abs(new Exercise4.Point(0, 0).distanceTo(new Exercise4.Point(3, 4)) - 5) < 1e-9,
                "class point distance");
        check(Math.abs(new Exercise4.Triangle(3, 4, 5).gamma() - 90) < 1e-9,
                "triangle angles");

        Exercise5.Rational mutable = new Exercise5.Rational(2, 4);
        check("1/2".equals(mutable.toString()), "constructor rational normalization");
        check(Math.abs(new Exercise5.Triangle(3, 4, 5).area() - 6) < 1e-9, "triangle area");
        Exercise5.Laptop first = new Exercise5.Laptop(16, 2.5, "A", "A");
        Exercise5.Laptop second = new Exercise5.Laptop(8, 5.0, "B", "B");
        check(Exercise5.Laptop.compare(first, second) > 0, "RAM-priority laptop comparison");

        check(Exercise6.Triangle.area(6, 4) == 12, "method triangle area");
        check(Exercise6.Triangle.area(-1, 4) == -1, "invalid triangle area");
        check(Math.abs(Exercise6.Point.distance(0, 0, 3, 4) - 5) < 1e-9,
                "method point distance");
        check(Exercise6.Triplet.checkP(3, 4, 5), "Pythagorean triplet");
        check(Exercise6.Series.series(7) == 17, "recursive series");

        check(new Exercise7.Oxygen().protons == 8, "atom inheritance");
        check(Math.abs(new Exercise7.Circle(2).getArea() - 4 * Math.PI) < 1e-9,
                "figure inheritance");
        check("Earth".equals(new Exercise7.Earth().name), "planet inheritance");

        Exercise8.BalanceP account = new Exercise8.BalanceP(
                "Test User", "0000000000", "BGN", 0, 0, "active", 100);
        account.depositMoney(50);
        account.pay(20);
        check(account.getAmount() == 130, "account inheritance");

        Path tempDirectory = Files.createTempDirectory("project-checks-");
        Exercise9.FileUserRegistry textRegistry =
                new Exercise9.FileUserRegistry(tempDirectory.resolve("text"));
        textRegistry.register("test-user", "password");
        check(textRegistry.login("test-user", "password"), "text-file login");
        Exercise9.SerializedUserRegistry objectRegistry =
                new Exercise9.SerializedUserRegistry(tempDirectory.resolve("objects"));
        objectRegistry.register(new Exercise9.User("serialized-user", "password"));
        check(objectRegistry.login("serialized-user", "password"), "serialized login");

        System.out.println("All project checks passed.");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError("Check failed: " + description);
        }
    }
}
