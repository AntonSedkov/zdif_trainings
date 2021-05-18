package anton.sample;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.PrintWriter;

public class UsageHelpSample {

    public static void main(String[] args) {
        System.out.println("Help example:");
        helpCLIApp();
        System.out.println("\nUsage example:");
        usageCLIApp();
    }

    private static void helpCLIApp() {
        Options options = new Options();

        options.addOption("p", "print", false, "Send print request to printer.")
                .addOption("g", "gui", false, "Show GUI Application.")
                .addOption("n", true, "No. of copies to print.");

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("helpCLIApp", options);
    }

    private static void usageCLIApp() {
        Options options = new Options();

        options.addOption("p", "print", false, "Send print request to printer.")
                .addOption("g", "gui", false, "Show GUI Application.")
                .addOption("n", true, "No. of copies to print.");

        HelpFormatter formatter = new HelpFormatter();

        final PrintWriter writer = new PrintWriter(System.out);
        formatter.printUsage(writer, 80, "usageCLIApp", options);
        writer.flush();
    }
}
