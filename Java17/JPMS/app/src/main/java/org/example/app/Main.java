package org.example.app;
import org.example.core.StringTools;
import org.example.utils.TimeUtil;

/**
 * Main application class demonstrating the use of core and utils modules.
 */
public class Main {
    public static void main(String[] args) {
        var text = "JPMS + Gradle";
        System.out.println("Reverse: " + StringTools.reverse(text));
        System.out.println("Blank? " + StringTools.isBlank(" "));
        System.out.println("Now: " + TimeUtil.nowIso());
    }
}