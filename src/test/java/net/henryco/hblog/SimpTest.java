package net.henryco.hblog;

import org.junit.Test;

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
}
