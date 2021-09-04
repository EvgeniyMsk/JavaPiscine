package edu.school21.printer.logic;
import com.diogonunes.jcolor.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Printer {
    private static Map<String, Attribute> colorsForColorNames;
    static {
        colorsForColorNames = new HashMap<>();
        colorsForColorNames.put("RED", Attribute.RED_BACK());
        colorsForColorNames.put("WHITE", Attribute.WHITE_BACK());
        colorsForColorNames.put("GREEN", Attribute.BRIGHT_GREEN_BACK());
    }
    public static void print(int[][] pixelMap, String whitePxlColor, String blackPixelColor) {
        for (int h = 0; h < pixelMap.length; h++)
        {
            for (int w = 0; w < pixelMap[h].length; w++) {
                if (pixelMap[h][w] == 1) {
                    Attribute bkgColor = colorsForColorNames.get(blackPixelColor);
                    System.out.print(Ansi.colorize(" ", bkgColor));
                } else {
                    Attribute bkgColor = colorsForColorNames.get(whitePxlColor);
                    System.out.print(Ansi.colorize(" ", bkgColor));
                }
            }
            System.out.println();
        }
    }
    public static Set<String> getAvailableColors() {
        return colorsForColorNames.keySet();
    }
}