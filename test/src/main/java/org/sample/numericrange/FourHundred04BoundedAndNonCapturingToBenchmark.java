package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred04BoundedAndNonCapturingToBenchmark
 */
//@formatter:on
public class FourHundred04BoundedAndNonCapturingToBenchmark extends AbstractNumericRangeToBenchmark {

    private static final String REGEX = "\\b(?:" + FourHundred01EveryNumberInOrToBenchmark.REGEX + ")\\b";

    public FourHundred04BoundedAndNonCapturingToBenchmark() {
        super(REGEX);
    }
}
