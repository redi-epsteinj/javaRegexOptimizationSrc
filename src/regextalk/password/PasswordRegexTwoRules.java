package regextalk.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://stackoverflow.com/questions/28886707/how-to-create-regex-for-passwords-validate-with-length-8-24-and-contain-at-lea
 */
public class PasswordRegexTwoRules implements PasswordToBenchmark {
   public static void main(String[] args) {
      new PasswordRegexTwoRules().runCodeToBeTimed();
   }

   public static final String SPECIAL_CHARS = "><?.,!@#$%^&*+=_)(\\}\\{\\]\\[";
   public static final String LKA_LOWER = "(?=.*[a-z])";    //LKA: lookahead
   public static final String LKA_UPPER = "(?=.*[A-Z])";
   public static final String LKA_DIGIT = "(?=.*[0-9])";
   public static final String LKA_SPECIAL = "(?=.*[" + SPECIAL_CHARS + "])";

   private static final String REGEX = "" +
      "^" +                                //start of input
      "(?:" +                              //non capturing group
         LKA_LOWER + LKA_UPPER +              //Option 1
            "|" +                                //or
         LKA_LOWER + LKA_DIGIT +              //Option 2
            "|" +                                //or
         LKA_LOWER + LKA_SPECIAL +            //Option 3
            "|" +                                //or
         LKA_UPPER + LKA_DIGIT +              //Option 4
            "|" +                                //or
         LKA_UPPER + LKA_SPECIAL +            //Option 5
            "|" +                                //or
         LKA_UPPER + LKA_SPECIAL +            //Option 6
      ")" +
      "[A-Za-z0-9" + SPECIAL_CHARS + "]" +
      "{8,24}" +                           //8 to 24 chars
      "$";

   private static final Matcher matcher = Pattern.compile(REGEX).matcher(IGNORED_INPUT);

   public boolean isPasswordValid(String password) {
      return matcher.reset(password).matches();
   }
}
