package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred10FurtherConsolidationToBenchmark
 */
//@formatter:on
public class FourHundred10FurtherConsolidationToBenchmark extends AbstractNumericRangeToBenchmark {

    public static final String ONE_TO_400 =
            "[1-9]?[0-9]|" +
            "[1-3][0-9]{2}|" +
            "400";

    public static final String REGEX = "(?:" + regextalk.numericrange.FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
               ONE_TO_400 + "))";

    public FourHundred10FurtherConsolidationToBenchmark() {
        super(REGEX);
    }
}
