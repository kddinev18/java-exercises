package excercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/** Solutions for the tasks in "ArraList.docx". */
public final class Exercise3 {
    private Exercise3() {
    }

    public record SplitResult<T>(List<T> before, List<T> after) {
        public SplitResult {
            before = List.copyOf(before);
            after = List.copyOf(after);
        }
    }

    public static <T> SplitResult<T> splitAroundFirst(List<T> values, T separator) {
        int index = values.indexOf(separator);
        if (index < 0) {
            throw new IllegalArgumentException("The separator is not present in the list");
        }
        return new SplitResult<>(values.subList(0, index), values.subList(index + 1, values.size()));
    }

    public static List<Integer> polynomialValues(int endInclusive) {
        if (endInclusive < 0) {
            throw new IllegalArgumentException("The ending x value must not be negative");
        }
        List<Integer> values = new ArrayList<>();
        for (int x = 0; x <= endInclusive; x++) {
            values.add(-x * x + 3 * x - 2);
        }
        return values;
    }

    public static List<Long> powersOfTwo(int highestExponent) {
        if (highestExponent < 0 || highestExponent > 62) {
            throw new IllegalArgumentException("The exponent must be between 0 and 62");
        }
        List<Long> values = new ArrayList<>();
        long power = 1;
        for (int exponent = 0; exponent <= highestExponent; exponent++) {
            values.add(power);
            power *= 2;
        }
        return values;
    }

    public static <T> List<T> insertBeforeEach(List<T> values, T insertedValue) {
        List<T> result = new ArrayList<>(values.size() * 2);
        for (T value : values) {
            result.add(insertedValue);
            result.add(value);
        }
        return result;
    }

    public record Student(String names, int age, double grade, String gender) {
        public Student {
            if (names == null || names.isBlank()) {
                throw new IllegalArgumentException("Names must not be blank");
            }
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be positive");
            }
        }

        public void showStudent() {
            System.out.printf("%s, age %d, grade %.2f, gender %s%n", names, age, grade, gender);
        }
    }

    public static List<Student> findStudentsByName(List<Student> students, String query) {
        String normalizedQuery = query.toLowerCase(Locale.ROOT);
        return students.stream()
                .filter(student -> student.names().toLowerCase(Locale.ROOT).contains(normalizedQuery))
                .collect(Collectors.toList());
    }

    public record Book(String author, String title, int year) {
        public Book {
            if (author == null || author.isBlank() || title == null || title.isBlank()) {
                throw new IllegalArgumentException("Author and title must not be blank");
            }
        }

        public void printInfo() {
            System.out.printf("%s - %s (%d)%n", author, title, year);
        }
    }

    public static void printBooks(List<Book> books) {
        books.forEach(Book::printInfo);
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 4, 7, 6, 5, 2, 1, 3);
        System.out.println("Split around 7: " + splitAroundFirst(numbers, 7));
        System.out.println("Polynomial: " + polynomialValues(5));
        System.out.println("Powers of two: " + powersOfTwo(4));
        System.out.println("Insert 2: " + insertBeforeEach(List.of(1, 3, 2, 4), 2));

        List<Student> students = List.of(
                new Student("Ivan Ivanov", 17, 5.50, "male"),
                new Student("Maria Petrova", 18, 5.80, "female"));
        findStudentsByName(students, "ivan").forEach(Student::showStudent);

        printBooks(List.of(
                new Book("George Orwell", "1984", 1949),
                new Book("Douglas Adams", "The Hitchhiker's Guide to the Galaxy", 1979)));
    }
}

