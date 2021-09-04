package edu.school21.printer.logic;
import com.beust.jcommander.*;

import java.util.Arrays;

@Parameters(separators = "=")
public class ArgumentsParser implements IParameterValidator {
    @Parameter(names = "--white", description = "white pixel color")
    private String whitePixelColor;
    @Parameter(names = "--black", description = "black pixel color")
    private String blackPixelColor;
    public String getBlackPixelColor() {
        return blackPixelColor;
    }
    public String getWhitePixelColor() {
        return whitePixelColor;
    }
    public void parsePixelColors(String[] cmdLineArgs) throws ParameterException {
        JCommander jc = new JCommander(this);
        jc.parse(cmdLineArgs);
        validate("--white", whitePixelColor);
        validate("--black", blackPixelColor);
    }
    @Override
    public void validate(String s, String s1) throws ParameterException {
        if (Printer.getAvailableColors().contains(s1) == false) {
            throw new ParameterException("Error: wrong color.\n Allowed colors are: " + Printer.getAvailableColors());
        }
    }
}