package anton.sample;

import org.apache.commons.cli.*;

// java PosixParserSample -D -A
// java PosixParserSample --D
@Deprecated
public class PosixParserSample {

    public static void main(String[] args) {
        try {
            posixParserCLIApp(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void posixParserCLIApp(String[] args) throws ParseException {
        Options posixOptions = new Options();

        posixOptions.addOption("D", false, "Display");
        posixOptions.addOption("A", false, "Act");

        CommandLineParser posixParser = new PosixParser();
        CommandLine cmd = posixParser.parse(posixOptions, args);

        if (cmd.hasOption("D")) {
            System.out.println("D option was used.");
        }
        if (cmd.hasOption("A")) {
            System.out.println("A option was used.");
        }
    }

}
