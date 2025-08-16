package j17.newapiexamples;

public class PatternMatchingExample {

    static sealed interface Shape permits Rectangle, Triangle {}

    static final class Rectangle implements Shape {
        private final double length;
        private final double width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public double getWidth() {
            return width;
        }
    }

    static final class Triangle implements Shape {
        private final double base;
        private final double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        public double getBase() {
            return base;
        }

        public double getHeight() {
            return height;
        }
    }

    public static String calculateArea(Shape shape) {
        return switch (shape) {
            case Rectangle rectangle -> "Area of Rectangle: " + (rectangle.getLength() * rectangle.getWidth());
            case Triangle triangle -> "Area of Triangle: " + (0.5 * triangle.getBase() * triangle.getHeight());
            default -> "Unknown shape";
        };
    }
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 10);
        Shape triangle = new Triangle(4, 6);
        System.out.println(calculateArea(rectangle));
        System.out.println(calculateArea(triangle));
    }
}
