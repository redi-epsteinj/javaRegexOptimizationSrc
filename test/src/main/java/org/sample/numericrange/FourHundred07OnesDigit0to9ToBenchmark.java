package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred07OnesDigit0to9ToBenchmark
 */
//@formatter:on
public class FourHundred07OnesDigit0to9ToBenchmark extends AbstractNumericRangeToBenchmark {

    public static final String ONE_TO_400 =
            "[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9]|" +
            "10[0-9]11[0-9]|12[0-9]|13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9]|" +
            "20[0-9]21[0-9]|22[0-9]|23[0-9]|24[0-9]|25[0-9]|26[0-9]|27[0-9]28[0-9]|29[0-9]|" +
            "30[0-9]|31[0-9]|32[0-9]|33[0-9]|34[0-9]|35[0-9]|36[0-9]|37[0-9]|38[0-9]|39[0-9]|" +
            "400";

    public static final String REGEX = "\\b(?:" + FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH +
               "|-?(?:" + ONE_TO_400 + "))\\b";

    public FourHundred07OnesDigit0to9ToBenchmark() {
        super(REGEX);
    }
}
