/**
 * 
 */
package j17.switchcase;

/**
 * <Description>
 * 
 * @author Somendu Maiti
 *
 * @since 08-Aug-2025
 */
public class WeekDays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		WeekDays weekDays = new WeekDays();

		System.out.println("Week day is : " + weekDays.getDayOfWeek(9));
	}

	public String getDayOfWeek(int day) {
		String dayOfWeek = switch (day) {
		case 1 -> "Monday";
		case 2 -> "Tuesday";

		default -> "unknown";

		};

		return dayOfWeek;
	}

}
