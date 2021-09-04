package edu.school21.renderers;

import edu.school21.preprocessors.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String message) {
        System.out.println(preProcessor.prepareMessage(message));
    }
}
