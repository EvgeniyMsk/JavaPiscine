package edu.school21;

import edu.school21.preprocessors.PreProcessor;
import edu.school21.preprocessors.PreProcessorToLowerImpl;
import edu.school21.preprocessors.PreProcessorToUpperImpl;
import edu.school21.printers.Printer;
import edu.school21.printers.PrinterWithPrefixImpl;
import edu.school21.renderers.Renderer;
import edu.school21.renderers.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithWithDateTimeRendererErrPreProcessorToUpper", Printer.class);
        printer.print("Hello");
    }

    public static void standart() {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix ("Prefix ");
        printer.print ("Hello!") ;
    }
}
