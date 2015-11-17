package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred09HundredsDigitTo0to9ToBenchmark
 */
//@formatter:on
public class FourHundred09HundredsDigitTo0to9ToBenchmark extends AbstractNumericRangeToBenchmark {

    public static final String ONE_TO_400 =
            "[0-9]|" +
            "[1-9][0-9]|" +
            "[1-3][0-9][0-9]|" +
            "400";

    public static final String REGEX = "\\b(?:" + regextalk.numericrange.FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
               ONE_TO_400 + "))\\b";

    public FourHundred09HundredsDigitTo0to9ToBenchmark() {
        super(REGEX);
    }
}
