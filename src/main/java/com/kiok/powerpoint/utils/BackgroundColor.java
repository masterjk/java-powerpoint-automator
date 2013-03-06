package com.kiok.powerpoint.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum BackgroundColor {

	BLUE(new Color(51, 56, 134)),
	MAGENTA(new Color(123, 26, 122)),
	TEAL(new Color(39, 89, 121)),
	GOLD(new Color(87, 82, 34)),
	GREEN(new Color(37, 68, 28)),
	MAROON(new Color(132, 3, 0)),
	PURPLE(new Color(51, 0, 102)),
	BROWN(new Color(99, 80, 19));

	private Color color;

	private static List<BackgroundColor> list;

	static {
		list = new ArrayList<BackgroundColor>();
		list.add(BLUE);
		list.add(MAGENTA);
		list.add(TEAL);
		list.add(GOLD);
		list.add(GREEN);
		list.add(MAROON);
		list.add(PURPLE);
		list.add(BROWN);
	}

    BackgroundColor(Color color) {
    	this.color = color;
    }

    /**
     * Return the color.
     * @return color
     */
    public Color getColor() {
    	return this.color;
    }
    
    /**
     * Returns [n] numbers of randomly selected background colors.
     * @param number
     * @return background colors
     */
    public static List<Color> getRandomColors(int number) {
		List<Color> result = new ArrayList<Color>();

		while (result.size() < number) {
			for(int i=0; i<list.size(); i++) {
				result.add(list.get(i).getColor());
			}
		}

		// Shuffle it for good measure
		Collections.shuffle(result);

		return result.subList(0, number);
    }
}
