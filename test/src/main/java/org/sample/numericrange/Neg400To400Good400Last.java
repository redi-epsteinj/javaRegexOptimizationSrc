package org.sample.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA Neg400To400Good400Last
 */
//@formatter:on
public class Neg400To400Good400Last extends AbstractNumericRangeToBenchmark {

    public static final String REGEX = "-?\\b(?:[1-3]?\\d{1,2}|400)\\b";

    public Neg400To400Good400Last() {
        super(REGEX);
    }
}
