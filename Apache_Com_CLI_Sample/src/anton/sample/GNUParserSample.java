package anton.sample;

import org.apache.commons.cli.*;

// java PosixParserSample -p -g -n 10
@Deprecated
public class GNUParserSample {

    public static void main(String[] args) {
        try {
            gnuParserCLIApp(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void gnuParserCLIApp(String[] args) throws ParseException {
        Options gnuOptions = new Options();

        gnuOptions.addOption("p", "print", false, "Send print request to printer.")
                .addOption("g", "gui", false, "Show GUI Application.")
                .addOption("n", true, "No. of copies to print.");

        CommandLineParser gnuParser = new GnuParser();
        CommandLine cmd = gnuParser.parse(gnuOptions, args);

        if (cmd.hasOption("p")) {
            System.out.println("p option was used.");
        }
        if (cmd.hasOption("g")) {
            System.out.println("g option was used.");
        }
        if (cmd.hasOption("n")) {
            System.out.println("Value passed: " +cmd.getOptionValue("n"));
        }
    }

}