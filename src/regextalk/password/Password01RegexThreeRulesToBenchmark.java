package regextalk.password;

/**
 * http://stackoverflow.com/questions/28886707/how-to-create-regex-for-passwords-validate-with-length-8-24-and-contain-at-lea
 */
public class Password01RegexThreeRulesToBenchmark extends AbstractPasswordRegex {
   public Password01RegexThreeRulesToBenchmark() {
       super(REGEX);
   }
   public static void main(String[] args) { new Password01RegexThreeRulesToBenchmark().setupRunBreakdown(); }

   private static final String SPECIAL_CHARS = Password02RegexTwoRulesToBenchmark.SPECIAL_CHARS;
   private static final String LKA_LOWER = Password02RegexTwoRulesToBenchmark.LKA_LOWER;
   private static final String LKA_UPPER = Password02RegexTwoRulesToBenchmark.LKA_UPPER;
   private static final String LKA_DIGIT = Password02RegexTwoRulesToBenchmark.LKA_DIGIT;
   private static final String LKA_SPECIAL = Password02RegexTwoRulesToBenchmark.LKA_SPECIAL;

   private static final String REGEX = "" +
      "^" +                                       //start of input
         "(?:" +                                     //non capturing group
            LKA_LOWER + LKA_UPPER + LKA_DIGIT +         //Option 1
            "|" +                                          //or
            LKA_LOWER + LKA_UPPER + LKA_SPECIAL +       //Option 2
            "|" +                                          //or
            LKA_LOWER + LKA_DIGIT + LKA_SPECIAL +       //Option 3
            "|" +                                          //or
            LKA_UPPER + LKA_DIGIT + LKA_SPECIAL +       //Option 4
         ")" +                                       //end non-capturing group
         "[A-Za-z0-9" + SPECIAL_CHARS + "]" +     //all legal characters
         "{8,24}" +                               //8 to 24 chars
      "$";                                        //end of input
}
