package j17.demo;

public class DemoMain {

    public static void main(String[] args) {
        var personName = "Somendu";
        Person person = new Person(personName, 30);
        System.out.println("Person Name: " + person.name());

        int dayOfWeek = 3;
        String mood = switch (dayOfWeek)
        {
            case 1 -> "Happy";
            case 2 -> "Sad";
            case 3 -> "Excited";
            case 4 -> "Bored";
            case 5 -> "Relaxed";
            case 6 -> "Joyful";
            case 7 -> "Content";
            default -> "Unknown Mood";
        };
        System.out.println("Mood for day " + dayOfWeek + ": " + mood);

        // Java 13: Text block
        String html = """
                <html>
                    <body>
                        <h1>Hello, Java 17!</h1>
                    </body>
                </html>
                """;
        System.out.println("Text block HTML:\n" + html);

        Object obj = "Hello, Java 17!";
        if (obj instanceof String str) {
            System.out.println("String value: " + str);
        } else {
            System.out.println("Object is not a String");
        }


        Shape shape = new Circle();
        Shape shape2 = new Square();

        System.out.println("Shapes created: " + shape.getClass().getSimpleName()
                + " and " + shape2.getClass().getSimpleName());


    }
}
