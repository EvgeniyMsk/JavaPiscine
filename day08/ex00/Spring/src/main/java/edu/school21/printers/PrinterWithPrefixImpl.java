package edu.school21.printers;

import edu.school21.renderers.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "";
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {
        renderer.print(this.prefix + message);
    }
}
