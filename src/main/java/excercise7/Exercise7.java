package excercise7;

import java.io.Serializable;
import java.util.List;

/** Solutions for the tasks in "javainheritance.docx". */
public final class Exercise7 {
    private Exercise7() {
    }

    public static class Atom implements Serializable {
        public String name;
        public int protons;
        public int electrons;

        public Atom(String name, int protons, int electrons) {
            this.name = name;
            this.protons = protons;
            this.electrons = electrons;
        }

        public void print() {
            System.out.printf("%s: protons=%d, electrons=%d%n", name, protons, electrons);
        }
    }

    public static final class Hydrogen extends Atom { public Hydrogen() { super("Hydrogen", 1, 1); } }
    public static final class Helium extends Atom { public Helium() { super("Helium", 2, 2); } }
    public static final class Lithium extends Atom { public Lithium() { super("Lithium", 3, 3); } }
    public static final class Carbon extends Atom { public Carbon() { super("Carbon", 6, 6); } }
    public static final class Nitrogen extends Atom { public Nitrogen() { super("Nitrogen", 7, 7); } }
    public static final class Oxygen extends Atom { public Oxygen() { super("Oxygen", 8, 8); } }
    public static final class Neon extends Atom { public Neon() { super("Neon", 10, 10); } }
    public static final class Sodium extends Atom { public Sodium() { super("Sodium", 11, 11); } }
    public static final class Chlorine extends Atom { public Chlorine() { super("Chlorine", 17, 17); } }
    public static final class Iron extends Atom { public Iron() { super("Iron", 26, 26); } }

    public abstract static class Figure implements Serializable {
        protected double perimeter;
        protected double area;

        public double getPerimeter() { return perimeter; }
        public double getArea() { return area; }

        public void print() {
            System.out.printf("%s: area=%.3f, perimeter=%.3f%n",
                    getClass().getSimpleName(), area, perimeter);
        }

        protected static double positive(double value, String name) {
            if (value <= 0) throw new IllegalArgumentException(name + " must be positive");
            return value;
        }
    }

    public static final class Circle extends Figure {
        public final double radius;
        public Circle(double radius) {
            this.radius = positive(radius, "radius");
            area = Math.PI * radius * radius;
            perimeter = 2 * Math.PI * radius;
        }
    }

    public static final class Semicircle extends Figure {
        public final double radius;
        public Semicircle(double radius) {
            this.radius = positive(radius, "radius");
            area = Math.PI * radius * radius / 2;
            perimeter = Math.PI * radius + 2 * radius;
        }
    }

    public static final class Rectangle extends Figure {
        public final double width;
        public final double height;
        public Rectangle(double width, double height) {
            this.width = positive(width, "width");
            this.height = positive(height, "height");
            area = width * height;
            perimeter = 2 * (width + height);
        }
    }

    public static final class Square extends Figure {
        public final double side;
        public Square(double side) {
            this.side = positive(side, "side");
            area = side * side;
            perimeter = 4 * side;
        }
    }

    public static final class Triangle extends Figure {
        public final double a;
        public final double b;
        public final double c;
        public Triangle(double a, double b, double c) {
            this.a = positive(a, "a");
            this.b = positive(b, "b");
            this.c = positive(c, "c");
            if (a + b <= c || a + c <= b || b + c <= a) {
                throw new IllegalArgumentException("The sides cannot form a triangle");
            }
            perimeter = a + b + c;
            double s = perimeter / 2;
            area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
    }

    public static final class Ellipse extends Figure {
        public final double semiMajorAxis;
        public final double semiMinorAxis;
        public Ellipse(double semiMajorAxis, double semiMinorAxis) {
            this.semiMajorAxis = positive(semiMajorAxis, "semi-major axis");
            this.semiMinorAxis = positive(semiMinorAxis, "semi-minor axis");
            area = Math.PI * semiMajorAxis * semiMinorAxis;
            double h = Math.pow(semiMajorAxis - semiMinorAxis, 2)
                    / Math.pow(semiMajorAxis + semiMinorAxis, 2);
            perimeter = Math.PI * (semiMajorAxis + semiMinorAxis)
                    * (1 + 3 * h / (10 + Math.sqrt(4 - 3 * h)));
        }
    }

    public static final class Trapezoid extends Figure {
        public final double baseA;
        public final double baseB;
        public final double legC;
        public final double legD;
        public final double height;
        public Trapezoid(double baseA, double baseB, double legC, double legD, double height) {
            this.baseA = positive(baseA, "base A");
            this.baseB = positive(baseB, "base B");
            this.legC = positive(legC, "leg C");
            this.legD = positive(legD, "leg D");
            this.height = positive(height, "height");
            area = (baseA + baseB) * height / 2;
            perimeter = baseA + baseB + legC + legD;
        }
    }

    public static final class Rhombus extends Figure {
        public final double side;
        public final double height;
        public Rhombus(double side, double height) {
            this.side = positive(side, "side");
            this.height = positive(height, "height");
            area = side * height;
            perimeter = 4 * side;
        }
    }

    public static final class Pentagon extends Figure {
        public final double side;
        public Pentagon(double side) {
            this.side = positive(side, "side");
            area = 5 * side * side / (4 * Math.tan(Math.PI / 5));
            perimeter = 5 * side;
        }
    }

    public static final class Parallelogram extends Figure {
        public final double base;
        public final double side;
        public final double height;
        public Parallelogram(double base, double side, double height) {
            this.base = positive(base, "base");
            this.side = positive(side, "side");
            this.height = positive(height, "height");
            area = base * height;
            perimeter = 2 * (base + side);
        }
    }

    public static class Planet implements Serializable {
        public final String name;
        public final double diameterKm;
        public final double massKg;
        public final String type;
        public Planet(String name, double diameterKm, double massKg, String type) {
            this.name = name;
            this.diameterKm = diameterKm;
            this.massKg = massKg;
            this.type = type;
        }
        public void print() {
            System.out.printf("%s: diameter=%.0f km, mass=%.4e kg, type=%s%n",
                    name, diameterKm, massKg, type);
        }
    }

    public static final class Mercury extends Planet { public Mercury() { super("Mercury", 4_879, 3.3011e23, "terrestrial"); } }
    public static final class Venus extends Planet { public Venus() { super("Venus", 12_104, 4.8675e24, "terrestrial"); } }
    public static final class Earth extends Planet { public Earth() { super("Earth", 12_742, 5.9724e24, "terrestrial"); } }
    public static final class Mars extends Planet { public Mars() { super("Mars", 6_779, 6.4171e23, "terrestrial"); } }
    public static final class Jupiter extends Planet { public Jupiter() { super("Jupiter", 139_820, 1.8982e27, "gas giant"); } }
    public static final class Saturn extends Planet { public Saturn() { super("Saturn", 116_460, 5.6834e26, "gas giant"); } }
    public static final class Uranus extends Planet { public Uranus() { super("Uranus", 50_724, 8.6810e25, "ice giant"); } }
    public static final class Neptune extends Planet { public Neptune() { super("Neptune", 49_244, 1.0241e26, "ice giant"); } }

    public static class Worker implements Serializable {
        public final String names;
        public final double salary;
        public final int yearsExperience;
        public final String specialty;
        public Worker(String names, double salary, int yearsExperience, String specialty) {
            this.names = names;
            this.salary = salary;
            this.yearsExperience = yearsExperience;
            this.specialty = specialty;
        }
        public void print() {
            System.out.printf("%s: salary=%.2f, experience=%d years, specialty=%s%n",
                    names, salary, yearsExperience, specialty);
        }
    }

    public static final class Cleaner extends Worker { public Cleaner(String n, double s, int y) { super(n, s, y, "cleaning"); } }
    public static final class Accountant extends Worker { public Accountant(String n, double s, int y) { super(n, s, y, "accounting"); } }
    public static final class Programmer extends Worker { public Programmer(String n, double s, int y) { super(n, s, y, "programming"); } }
    public static final class Manager extends Worker { public Manager(String n, double s, int y) { super(n, s, y, "management"); } }
    public static final class PublicRelations extends Worker { public PublicRelations(String n, double s, int y) { super(n, s, y, "public relations"); } }

    public static void main(String[] args) {
        List<Atom> atoms = List.of(new Hydrogen(), new Helium(), new Lithium(), new Carbon(),
                new Nitrogen(), new Oxygen(), new Neon(), new Sodium(), new Chlorine(), new Iron());
        atoms.forEach(Atom::print);

        List<Figure> figures = List.of(new Circle(2), new Semicircle(2), new Rectangle(3, 4),
                new Square(3), new Triangle(3, 4, 5), new Ellipse(4, 2),
                new Trapezoid(6, 4, 3, 3, 2.5), new Rhombus(4, 3),
                new Pentagon(3), new Parallelogram(5, 3, 2.5));
        figures.forEach(Figure::print);

        List<Planet> planets = List.of(new Mercury(), new Venus(), new Earth(), new Mars(),
                new Jupiter(), new Saturn(), new Uranus(), new Neptune());
        planets.forEach(Planet::print);

        List<Worker> workers = List.of(new Cleaner("Anna", 1500, 3),
                new Accountant("Boris", 2400, 6), new Programmer("Viktor", 3500, 5),
                new Manager("Diana", 4200, 8), new PublicRelations("Elena", 2800, 4));
        workers.forEach(Worker::print);
    }
}

