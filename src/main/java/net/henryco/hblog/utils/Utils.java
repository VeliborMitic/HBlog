package net.henryco.hblog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
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


	public static String saveMultiPartFile(MultipartFile file, String userName, String upload_path) {
		try {
			if (file.isEmpty()) return null;
			final int i = file.getOriginalFilename().lastIndexOf(".");
			final String hash = Integer.toString(userName.hashCode());
			final String time = Long.toString(System.currentTimeMillis());
			final String name = hash + time + ( i != -1 ? file.getOriginalFilename().substring(i) : "");
			Files.write(Paths.get(upload_path + name), file.getBytes());
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
