package net.henryco.hblog;

import net.henryco.hblog.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Henry on 04/07/17.
 */ 
public class SimpTest {

	@Test
	public void stringBuilderTest() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 12; i++)
			builder.append(i).append(", ");
		System.out.println(builder.toString());
		builder.delete(builder.length() - 2, builder.length());
		System.out.println(builder.toString());
	}

	@Test
	public void stringArrayTest() {
		String[] arr = {"one.png", "two.jpg", "wtfzdf.bmp", "wowo.png"};
		Assert.assertEquals(
				Arrays.toString(arr),
				Arrays.toString(Utils.stringToArray(Utils.arrayToString(arr)))
		);
	}
}
