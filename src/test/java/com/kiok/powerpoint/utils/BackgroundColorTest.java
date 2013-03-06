package com.kiok.powerpoint.utils;

import org.junit.Assert;
import org.junit.Test;

public class BackgroundColorTest {

	@Test
	public void testGetColorCount() {
		
		Assert.assertEquals("Expected to have gotten 5 random colors", 5, BackgroundColor.getRandomColors(5).size());
		Assert.assertEquals("Expected to have gotten 15 random colors", 15, BackgroundColor.getRandomColors(15).size());
		Assert.assertEquals("Expected to have gotten 25 random colors", 25, BackgroundColor.getRandomColors(25).size());
	}
}
