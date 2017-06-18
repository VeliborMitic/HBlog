package net.henryco.hblog.utils;

import java.util.Arrays;

/**
 * @author Henry on 17/06/17.
 */
public final class Utils {

	public static final String REL_FILE_PATH = System.getProperty("user.dir");
	public static final String ABS_FILE_PATH = System.getProperty("user.home");

	public static String arrayToString(String[] array) {
		return Arrays.toString(array);
	}

	public static String[] stringToArray(String string) {
		return string.substring(1, string.length() - 1).split(", ");
	}

}
