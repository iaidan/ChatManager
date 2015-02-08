package net.aidantaylor.bukkit.core;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Formatter {
	public static String specialChar = "\u00A7";
	public static String replaceChar = "&";

	public static String translateCodes(String string) {
		string = translateColourCodes(string);
		string = translateFormatCodes(string);
		string = translateMagicCode(string);
		
		return string;
	}

	public static String translateColourCodes(String string) {
        return translateColourCodes(string, false);
    }

	public static String translateColourCodes(String string, boolean undo) {
		if (undo == false) {
        	string = Pattern.compile("(?i)" + replaceChar + "([0-9A-F])").matcher(string).replaceAll(specialChar + "$1");
        	string = Pattern.compile("(?i)" + replaceChar + "([R])").matcher(string).replaceAll(specialChar + "$1");
        	
        	return string;
		}
		
        if (string != null) {
        	string = Pattern.compile("(?i)" + specialChar + "([0-9A-F])").matcher(string).replaceAll(replaceChar + "$1");
        	string = Pattern.compile("(?i)" + specialChar + "([R])").matcher(string).replaceAll(replaceChar + "$1");
        }
        
        return string;
    }

	public static String translateFormatCodes(String string) {
        return translateFormatCodes(string, false);
    }

	public static String translateFormatCodes(String string, boolean undo) {
		if (undo == false) {
        	string = Pattern.compile("(?i)" + replaceChar + "([L])").matcher(string).replaceAll(specialChar + "$1");
        	string = Pattern.compile("(?i)" + replaceChar + "([M])").matcher(string).replaceAll(specialChar + "$1");
        	string = Pattern.compile("(?i)" + replaceChar + "([N])").matcher(string).replaceAll(specialChar + "$1");
        	string = Pattern.compile("(?i)" + replaceChar + "([O])").matcher(string).replaceAll(specialChar + "$1");
        	string = Pattern.compile("(?i)" + replaceChar + "([R])").matcher(string).replaceAll(specialChar + "$1");
        	
        	return string;
		}
		
        if (string != null) {
        	string = Pattern.compile("(?i)" + specialChar + "([L])").matcher(string).replaceAll(replaceChar + "$1");
        	string = Pattern.compile("(?i)" + specialChar + "([M])").matcher(string).replaceAll(replaceChar + "$1");
        	string = Pattern.compile("(?i)" + specialChar + "([N])").matcher(string).replaceAll(replaceChar + "$1");
        	string = Pattern.compile("(?i)" + specialChar + "([O])").matcher(string).replaceAll(replaceChar + "$1");
        	string = Pattern.compile("(?i)" + specialChar + "([R])").matcher(string).replaceAll(replaceChar + "$1");
        }
        
        return string;
    }

	public static String translateMagicCode(String string) {
        return translateMagicCode(string, false);
    }

	public static String translateMagicCode(String string, boolean undo) {
		if (undo == false) {
        	string = Pattern.compile("(?i)" + replaceChar + "([K])").matcher(string).replaceAll(specialChar + "$1");
        	string = Pattern.compile("(?i)" + replaceChar + "([R])").matcher(string).replaceAll(specialChar + "$1");
        	
        	return string;
		}
		
        if (string != null) {
        	string = Pattern.compile("(?i)" + specialChar + "([K])").matcher(string).replaceAll(replaceChar + "$1");
        	string = Pattern.compile("(?i)" + specialChar + "([R])").matcher(string).replaceAll(replaceChar + "$1");
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
