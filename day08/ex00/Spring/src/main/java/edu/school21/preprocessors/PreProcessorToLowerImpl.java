package edu.school21.preprocessors;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor{
    public PreProcessorToLowerImpl() {
    }

    @Override
    public String prepareMessage(String message) {
        return message.toLowerCase();
    }
}
