package j17.newapiexamples;

public class TranslateEscapesExample {
    public static void main(String[] args) {
        String text = "Hello, World!\nThis is a test string with a tab\tand a newline.\n";
        System.out.println("Original Text:");
        System.out.println(text);

        // Using the translateEscapes method to convert escape sequences
        String translatedText = text.translateEscapes();
        System.out.println("Translated Text:");
        System.out.println(translatedText);
    }
}
