/**
 * 
 */
package j17.nativeclass;

/**
 * <Description>
 * 
 * @author Somendu Maiti
 *
 * @since 08-Aug-2025
 */
public class NativeSum {
	// Load the shared library containing the native function
	static {
		System.loadLibrary("NativeSum");
	}

	// Declare the native method to add two integers
	public native int add(int a, int b);

	public static void main(String[] args) {
		NativeSum nativeSum = new NativeSum();
		int result = nativeSum.add(5, 7);
		System.out.println("Sum: " + result);
	}
}
