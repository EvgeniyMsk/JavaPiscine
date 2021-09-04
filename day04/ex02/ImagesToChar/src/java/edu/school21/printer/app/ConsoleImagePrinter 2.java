package edu.school21.printer.app;
import edu.school21.printer.logic.*;
import com.beust.jcommander.*;

import java.io.IOException;
import java.io.InputStream;

public class ConsoleImagePrinter {
    public static void main(String[] av) {
        ArgumentsParser argParser = new ArgumentsParser();
        try {
            argParser.parsePixelColors(av);
        } catch (ParameterException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
        try (InputStream is = ConsoleImagePrinter.class.getResourceAsStream("/resources/it.bmp")) {
            int[][] imagePixelMap = BmpToPixelMapConverter.convert(is);
            Printer.print(imagePixelMap, argParser.getWhitePixelColor(), argParser.getBlackPixelColor());  
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private static void printWrongArgumentsErrorMessage() {
        System.err.println("Error: wrong number or type of arguments to program");
        System.err.println("Program requires 2 arguments of type:");
        System.err.println(" --white=RED --black=WHITE");
    }
}