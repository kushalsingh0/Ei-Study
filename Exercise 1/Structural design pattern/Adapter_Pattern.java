interface Printer {
    void print(String text);
}

class OldPrinter {
    public void printText(String text) {
        System.out.println("Printing: " + text);
    }
}

class OldPrinterAdapter implements Printer {
    private OldPrinter oldPrinter;

    public OldPrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String text) {
        oldPrinter.printText(text);
    }
}

public class Adapter_Pattern{
    public static void main(String[] args) {
        OldPrinter oldPrinter = new OldPrinter();
        Printer printer = new OldPrinterAdapter(oldPrinter);
        printer.print("Hello");
    }
}