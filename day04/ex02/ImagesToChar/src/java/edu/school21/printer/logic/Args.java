package edu.school21.printer.logic;
import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = { "--white" }, description = "color of white pixel")
    private String white;
    @Parameter(names = { "--black" }, description = "color of black pixel")
    private String black;
}
