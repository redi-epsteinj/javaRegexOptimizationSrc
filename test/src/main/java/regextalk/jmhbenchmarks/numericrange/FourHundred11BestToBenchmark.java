package regextalk.jmhbenchmarks.numericrange;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred11BestToBenchmark
 */
//@formatter:on
public class FourHundred11BestToBenchmark extends AbstractNumericRangeToBenchmark {

    //@formatter:off
      public static final String REGEX = "" +
         "\\b" +
         "(?:" +
            "(?<!-)0+|" +        //Zero or more "0"-s, as long as not preceded by dash
            "-?" +               //Optional dash, followed by 1..400
            "(?:" +
               "400|" +
               "[1-9]?[0-9]|" +
               "[1-3][0-9][0-9]" +
            ")" +
         ")\\b";
//@formatter:on

    public FourHundred11BestToBenchmark() {
        super(REGEX);
    }
}
