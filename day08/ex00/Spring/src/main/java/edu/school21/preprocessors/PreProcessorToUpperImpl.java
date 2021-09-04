package edu.school21.preprocessors;

public class PreProcessorToUpperImpl implements PreProcessor{
    public PreProcessorToUpperImpl() {
    }

    @Override
    public String prepareMessage(String message) {
        return message.toUpperCase();
    }
}
