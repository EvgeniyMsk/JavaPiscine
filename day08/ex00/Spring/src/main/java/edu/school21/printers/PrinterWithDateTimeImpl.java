package edu.school21.printers;

import edu.school21.renderers.Renderer;

import java.util.Date;

public class PrinterWithDateTimeImpl implements Printer {
    Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.print(new Date() + " " + message);
    }
}
