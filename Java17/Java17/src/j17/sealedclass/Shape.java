/**
 * 
 */
package j17.sealedclass;

/**
 * Sealed Class Example
 * 
 * @author Somendu Maiti
 *
 * @since 08-Aug-2025
 */
public sealed class Shape permits Circle, Square, Triangle {
	// Common properties and methods for all shapes
}

final class Circle extends Shape {
	// Circle-specific properties and methods
}

final class Square extends Shape {
	// Square-specific properties and methods
}

final class Triangle extends Shape {
	// Triangle-specific properties and methods
}
