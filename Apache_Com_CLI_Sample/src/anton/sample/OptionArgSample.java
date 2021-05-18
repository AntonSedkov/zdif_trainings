package anton.sample;

import org.apache.commons.cli.*;

// java OptionArgSample --logfile test.log
public class OptionArgSample {

    public static void main(String[] args) {
        try {
            optionArgCLIApp(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void optionArgCLIApp(String[] args) throws ParseException {
        Options options = new Options();

        String optionLogFile = "logfile";
        Option logfile = Option.builder()
                .longOpt(optionLogFile)
                .argName("file")
                .hasArg()
                .desc("use given file for log")
                .build();
        options.addOption(logfile);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption(optionLogFile)) {
            System.out.println(cmd.getOptionValue(optionLogFile));
        }
    }

}