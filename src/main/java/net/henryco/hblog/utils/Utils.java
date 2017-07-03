package net.henryco.hblog.utils;

import java.util.Arrays;

/**
 * @author Henry on 17/06/17.
 */
public abstract class Utils {

	public static String arrayToString(String[] array) {
		return Arrays.toString(array);
	}

	public static String[] stringToArray(String string) {
		return string.substring(1, string.length() - 1).split(", ");
	}

}
