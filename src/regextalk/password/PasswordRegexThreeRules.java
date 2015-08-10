package regextalk.password;

import regextalk.AbstractReusedMatcherToBenchmark;

/**
 * http://stackoverflow.com/questions/28886707/how-to-create-regex-for-passwords-validate-with-length-8-24-and-contain-at-lea
 */
public class PasswordRegexThreeRules extends AbstractReusedMatcherToBenchmark implements PasswordToBenchmark {
   private final PasswordToBenchmarkComposer passwordComposer;

   public PasswordRegexThreeRules() {
      passwordComposer = new PasswordToBenchmarkComposer();
   }

   public static void main(String[] args) { new PasswordRegexThreeRules().setupRunBreakdown(); }

   private static final String SPECIAL_CHARS = PasswordRegexTwoRules.SPECIAL_CHARS;
   private static final String LKA_LOWER = PasswordRegexTwoRules.LKA_LOWER;
   private static final String LKA_UPPER = PasswordRegexTwoRules.LKA_UPPER;
   private static final String LKA_DIGIT = PasswordRegexTwoRules.LKA_DIGIT;
   private static final String LKA_SPECIAL = PasswordRegexTwoRules.LKA_SPECIAL;

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
   @Override
   public String getRegex() {
      return REGEX;
   }

   @Override
   public String[] getInputs() {
      return passwordComposer.getInputs();
   }

   public boolean isPasswordValid(String password) {
      return getMatcher().reset(password).matches();
   }

   @Override
   public void runCodeToBeTimed() {
      passwordComposer.runCodeToBeTimed(this);
   }
}
