package net.henryco.hblog;

import net.henryco.hblog.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HblogApplicationTests {

	@Test
	public void contextLoads() {
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
