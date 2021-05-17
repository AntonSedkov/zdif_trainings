package anton.sample;

import org.apache.commons.cli.*;

import java.util.Calendar;

// java BooleanOptSample
// java BooleanOptSample -t
public class BooleanOptSample {

    public static void main(String[] args) {
        try {
            booleanOptCLIApp(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void booleanOptCLIApp(String[] args) throws ParseException {
        Options options = new Options();

        String optionTime = "t";
        options.addOption(optionTime, false, "display time");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        Calendar date = Calendar.getInstance();
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);
        int hour = date.get(Calendar.HOUR);
        int min = date.get(Calendar.MINUTE);
        int sec = date.get(Calendar.SECOND);
        System.out.printf("%s/%s/%s ", day, month, year);

        if (cmd.hasOption(optionTime)) {
            System.out.printf("%s:%s:%s", hour, min, sec);
        }
    }

}