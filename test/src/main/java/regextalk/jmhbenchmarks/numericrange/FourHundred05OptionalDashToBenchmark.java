package regextalk.jmhbenchmarks.numericrange;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred05OptionalDashToBenchmark
 */
//@formatter:on
public class FourHundred05OptionalDashToBenchmark extends AbstractNumericRangeToBenchmark {

    public static final String ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH = "(?<!-)0+";
    public static final String[] ONE_TO_400_PIECES =
            new String[]{FourHundred01EveryNumberInOrToBenchmark.ONE_TO_50,
                         FourHundred01EveryNumberInOrToBenchmark.FIFTY1_TO_100,
                         FourHundred01EveryNumberInOrToBenchmark.ONE01_TO_150,
                         FourHundred01EveryNumberInOrToBenchmark.ONE51_TO_200,
                         FourHundred01EveryNumberInOrToBenchmark.TWO01_TO_250,
                         FourHundred01EveryNumberInOrToBenchmark.TWO51_TO_300,
                         FourHundred01EveryNumberInOrToBenchmark.THREE01_TO_350,
                         FourHundred01EveryNumberInOrToBenchmark.THREE51_TO_400};
    public static final String ONE_TO_400 = Arrays.stream(ONE_TO_400_PIECES).
            collect(joining("|"));
    private static final String REGEX = "\\b(?:" + ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" + ONE_TO_400 + "))\\b";

    public FourHundred05OptionalDashToBenchmark() {
        super(REGEX);
    }
}
