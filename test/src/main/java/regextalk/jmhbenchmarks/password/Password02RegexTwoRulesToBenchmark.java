package regextalk.jmhbenchmarks.password;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA Password02RegexTwoRulesToBenchmark
 */
//@formatter:on
public class Password02RegexTwoRulesToBenchmark extends AbstractPasswordRegexToBenchmark {

    public static final String SPECIAL_CHARS = "><?.,!@#$%^&*+=_)(\\}\\{\\]\\[";
    public static final String LKA_LOWER = "(?=.*[a-z])";    //LKA: lookahead
    public static final String LKA_UPPER = "(?=.*[A-Z])";
    public static final String LKA_DIGIT = "(?=.*[0-9])";
    public static final String LKA_SPECIAL = "(?=.*[" + SPECIAL_CHARS + "])";

    //@formatter:off
   private static final String REGEX = "" +
      "^" +                                //start of input
         "(?:" +                           //non capturing group
            LKA_LOWER + LKA_UPPER +              //Option 1
            "|" +                                   //or
            LKA_LOWER + LKA_DIGIT +              //Option 2
            "|" +                                   //or
            LKA_LOWER + LKA_SPECIAL +            //Option 3
            "|" +                                   //or
            LKA_UPPER + LKA_DIGIT +              //Option 4
            "|" +                                   //or
            LKA_UPPER + LKA_SPECIAL +            //Option 5
            "|" +                                   //or
            LKA_UPPER + LKA_SPECIAL +            //Option 6
         ")" +                              //end of non-capturing group
      "[A-Za-z0-9" + SPECIAL_CHARS + "]" +  //All possible characters
      "{8,24}" +                            //8 to 24 chars
      "$";                                  //end of input
//@formatter:on

    public Password02RegexTwoRulesToBenchmark() {
        super(REGEX);
    }

}
