package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred06DigitOnesDigitDToBenchmark
 */
//@formatter:on
public class FourHundred06DigitOnesDigitDToBenchmark extends AbstractNumericRangeToBenchmark {

    public static final String ONE_TO_400 =
            "\\d|1\\d|2\\d|3\\d|4\\d|5\\d|6\\d|7\\d|8\\d|9\\d|" +
            "10\\d11\\d|12\\d|13\\d|14\\d|15\\d|16\\d|17\\d|18\\d|19\\d|" +
            "20\\d21\\d|22\\d|23\\d|24\\d|25\\d|26\\d|27\\d28\\d|29\\d|" +
            "30\\d|31\\d|32\\d|33\\d|34\\d|35\\d|36\\d|37\\d|38\\d|39\\d|" +
            "400";

    public static final String REGEX = "\\b(?:" +
                                       FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH +
                                       "|-?(?:" + ONE_TO_400 + "))\\b";

    public FourHundred06DigitOnesDigitDToBenchmark() {
        super(REGEX);
    }
}
