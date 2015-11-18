package regextalk.jmhbenchmarks.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred08TensDigitTo0to9ToBenchmark
 */
//@formatter:on
public class FourHundred08TensDigitTo0to9ToBenchmark extends AbstractNumericRangeToBenchmark {

    public static final String ONE_TO_400 =
            "[0-9]|" +
            "[1-9][0-9]|" +
            "1[0-9][0-9]|" +
            "2[0-9][0-9]|" +
            "3[0-9][0-9]|" +
            "400";

    public static final String REGEX = "\\b(?:" + FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH +
               "|-?(?:" + ONE_TO_400 + "))\\b";

    public FourHundred08TensDigitTo0to9ToBenchmark() {
        super(REGEX);
    }
}
