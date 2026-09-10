package excercise2;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

/** Solutions for the tasks in "javaarrays.docx". */
public final class Exercise2 {
    private Exercise2() {
    }

    public static int[] reverse(int[] values) {
        int[] result = values.clone();
        for (int left = 0, right = result.length - 1; left < right; left++, right--) {
            int temporary = result[left];
            result[left] = result[right];
            result[right] = temporary;
        }
        return result;
    }

    public record Statistics(long sum, long product, double average) {
    }

    public static Statistics statistics(int[] values) {
        requireNotEmpty(values);
        long sum = 0;
        long product = 1;
        for (int value : values) {
            sum += value;
            product = Math.multiplyExact(product, value);
        }
        return new Statistics(sum, product, (double) sum / values.length);
    }

    public static int[] positiveEvenNumbers(int[] values) {
        return IntStream.of(values).filter(value -> value > 0 && value % 2 == 0).toArray();
    }

    public record Multiples(int[] ofThree, int[] ofFive) {
        public Multiples {
            ofThree = ofThree.clone();
            ofFive = ofFive.clone();
        }
    }

    public static Multiples multiplesOfThreeAndFive(int[] values) {
        return new Multiples(
                IntStream.of(values).filter(value -> value % 3 == 0).toArray(),
                IntStream.of(values).filter(value -> value % 5 == 0).toArray());
    }

    public static Map<Integer, Integer> frequencies(int[] values) {
        Map<Integer, Integer> frequencies = new LinkedHashMap<>();
        for (int value : values) {
            frequencies.merge(value, 1, Integer::sum);
        }
        return frequencies;
    }

    public record MinMax(int minimum, int maximum) {
    }

    public static MinMax minMax(int[] values) {
        requireNotEmpty(values);
        int minimum = values[0];
        int maximum = values[0];
        for (int value : values) {
            minimum = Math.min(minimum, value);
            maximum = Math.max(maximum, value);
        }
        return new MinMax(minimum, maximum);
    }

    public static int[] elementWiseSums(int[] first, int[] second) {
        if (first.length != second.length) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }
        int[] result = new int[first.length];
        for (int index = 0; index < first.length; index++) {
            result[index] = Math.addExact(first[index], second[index]);
        }
        return result;
    }

    public static long countPrimes(int[] values) {
        return IntStream.of(values).filter(Exercise2::isPrime).count();
    }

    public static int[] replaceEvenWithZero(int[] values) {
        return IntStream.of(values).map(value -> value % 2 == 0 ? 0 : value).toArray();
    }

    public static int[] adjacentPairSums(int[] values) {
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("The array length must be even");
        }
        int[] result = new int[values.length / 2];
        for (int index = 0; index < values.length; index += 2) {
            result[index / 2] = Math.addExact(values[index], values[index + 1]);
        }
        return result;
    }

    private static boolean isPrime(int value) {
        if (value < 2) {
            return false;
        }
        for (int divisor = 2; divisor <= value / divisor; divisor++) {
            if (value % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    private static void requireNotEmpty(int[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("The array must not be empty");
        }
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5, 6, 6, 7, 10, 11};
        System.out.println("Reverse: " + Arrays.toString(reverse(values)));
        System.out.println("Statistics: " + statistics(values));
        System.out.println("Positive evens: " + Arrays.toString(positiveEvenNumbers(values)));
        Multiples multiples = multiplesOfThreeAndFive(values);
        System.out.println("Multiples of 3: " + Arrays.toString(multiples.ofThree()));
        System.out.println("Multiples of 5: " + Arrays.toString(multiples.ofFive()));
        System.out.println("Frequencies: " + frequencies(values));
        System.out.println("Min/max: " + minMax(values));
        System.out.println("Element-wise sums: " + Arrays.toString(elementWiseSums(values, values)));
        System.out.println("Prime count: " + countPrimes(values));
        System.out.println("Even values replaced: " + Arrays.toString(replaceEvenWithZero(values)));
        System.out.println("Adjacent pair sums: " + Arrays.toString(adjacentPairSums(values)));
    }
}

