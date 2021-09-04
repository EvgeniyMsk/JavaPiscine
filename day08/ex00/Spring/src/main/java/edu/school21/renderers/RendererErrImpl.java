package edu.school21.renderers;

import edu.school21.preprocessors.PreProcessor;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String message) {
        System.err.println(preProcessor.prepareMessage(message));
    }
}
