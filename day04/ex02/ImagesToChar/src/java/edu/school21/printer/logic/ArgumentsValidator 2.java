package edu.school21.printer.logic;

public class ArgumentsValidator {
    public static boolean areArgumentsValid(String[] av) {
        if (av.length != 2)
            return false;
        if (av[0].length() != 1)
            return false;
        if (av[1].length() != 1)
            return false;
        return true;
    }
}