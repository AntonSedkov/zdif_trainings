package anton.sample;

import org.apache.commons.cli.*;

import java.util.Properties;

// java OptionArgSample -DrollNo=1 -Dclass=VI -Dname=Nick
public class OptionPropertySample {

    public static void main(String[] args) {
        try {
            optionPropertyCLIApp(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void optionPropertyCLIApp(String[] args) throws ParseException {
        Options options = new Options();

        Option propertyOpt = Option.builder()
                .longOpt("D")
                .argName("property=value")
                .hasArgs()
                .valueSeparator()
                .numberOfArgs(2)
                .desc("use value for given property")
                .build();
        options.addOption(propertyOpt);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("D")) {
            Properties properties = cmd.getOptionProperties("D");
            System.out.println("Class: "+ properties.getProperty("class"));
            System.out.println("Roll No: "+ properties.getProperty("rollNo"));
            System.out.println("Name: "+ properties.getProperty("name"));
        }
    }

}
