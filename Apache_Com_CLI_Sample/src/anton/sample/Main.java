package anton.sample;

import org.apache.commons.cli.*;

// java Main -a 1 2 3 4 5
// java Main -m 1 2 3 4 5
public class Main {

    public static void main(String[] args) {
        try {
            firstCLIApp(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void firstCLIApp(String[] args) throws ParseException {
        Options options = new Options();

        String optionAdd = "a";
        String optionMultiply = "m";

        options.addOption(optionAdd, false, "add nums");
        options.addOption(optionMultiply, false, "multiply nums");

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption(optionAdd)) {
            System.out.println("sum = " + countSum(args));
        } else if (cmd.hasOption(optionMultiply)) {
            System.out.println("multiply = " + countMultiply(args));
        }
    }

    private static int countSum(String[] args) {
        int sum = 0;
        for (int i = 1; i < args.length; i++) {
            sum += Integer.parseInt(args[i]);
        }
        return sum;
    }

    private static int countMultiply(String[] args) {
        int mult = 1;
        for (int i = 1; i < args.length; i++) {
            mult *= Integer.parseInt(args[i]);
        }
        return mult;
    }

}
