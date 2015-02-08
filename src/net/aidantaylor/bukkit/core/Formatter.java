package net.aidantaylor.bukkit.core;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Formatter {
	private static String specialChar = "\u00A7$1";
	private static String replaceChar = "&";
	protected static Pattern chatColourPattern = Pattern.compile("(?i)" + replaceChar + "([0-9A-F])");
	protected static Pattern chatMagicPattern = Pattern.compile("(?i)" + replaceChar + "([K])");
	protected static Pattern chatBoldPattern = Pattern.compile("(?i)" + replaceChar + "([L])");
	protected static Pattern chatStrikethroughPattern = Pattern.compile("(?i)" + replaceChar + "([M])");
	protected static Pattern chatUnderlinePattern = Pattern.compile("(?i)" + replaceChar + "([N])");
	protected static Pattern chatItalicPattern = Pattern.compile("(?i)" + replaceChar + "([O])");
	protected static Pattern chatResetPattern = Pattern.compile("(?i)" + replaceChar + "([R])");

	public static String translateCodes(String string) {
		string = translateColourCodes(string);
		string = translateFormatCodes(string);
		string = translateMagicCode(string);
		
		return string;
	}

	public static String translateColourCodes(String string) {
        if (string != null) {
        	string = chatColourPattern.matcher(string).replaceAll(specialChar);
        }
        
        return string;
    }

	public static String translateFormatCodes(String string) {
        if (string != null) {
        	string = chatBoldPattern.matcher(string).replaceAll(specialChar);
        	string = chatStrikethroughPattern.matcher(string).replaceAll(specialChar);
            string = chatUnderlinePattern.matcher(string).replaceAll(specialChar);
            string = chatItalicPattern.matcher(string).replaceAll(specialChar);
            string = chatResetPattern.matcher(string).replaceAll(specialChar);
        }
        
        return string;
    }

	public static String translateMagicCode(String string) {
        if (string != null) {
        	string = chatMagicPattern.matcher(string).replaceAll(specialChar);
        }
        
        return string;
    }

	public static String replaceTime(String message) {
		Calendar calendar = Calendar.getInstance();
		String a = (calendar.get(Calendar.AM_PM) == 0) ? "am" : "pm";
		
		return message.replace("%h", formatTime(calendar.get(Calendar.HOUR)))
					  .replace("%H", formatTime(calendar.get(Calendar.HOUR_OF_DAY)))
					  .replace("%i", formatTime(calendar.get(Calendar.MINUTE)))
					  .replace("%s", formatTime(calendar.get(Calendar.SECOND)))
					  .replace("%a", a).replace("%A", a.toUpperCase());
	}

	private static String formatTime(int when) {
		return String.format("%02d", when);
	}
}
