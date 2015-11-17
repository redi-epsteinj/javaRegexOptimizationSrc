package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA Neg400To400BadToGood07EasiestOr1st
 */
//@formatter:on
public class Neg400To400BadToGood07EasiestOr1st extends AbstractNumericRangeToBenchmark {

//@formatter:off
    public static final String ONE_TO_400 =
         "400|" +
         "[1-9]?[0-9]|" +
         "[1-3][0-9]{2}";

   public static final String REGEX = "(?:" + regextalk.numericrange.FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
             ONE_TO_400 + "))";
//@formatter:on

    public Neg400To400BadToGood07EasiestOr1st() {
        super(REGEX);
    }
}
