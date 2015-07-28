package other;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://stackoverflow.com/questions/28886707/how-to-create-regex-for-passwords-validate-with-length-8-24-and-contain-at-lea
 */
public class PasswordRegex {
   public static void main(String[] args) {
      new PasswordRegex().go();
   }

   private static final String SPECIAL_CHARS = "><?.,!@#$%^&*+=_)(\\}\\{\\]\\[";
   private static final String LKA_LOWER = "(?=.*[a-z])";    //LKA: lookahead
   private static final String LKA_UPPER = "(?=.*[A-Z])";
   private static final String LKA_DIGIT = "(?=.*[0-9])";
   private static final String LKA_SPECIAL = "(?=.*[" + SPECIAL_CHARS + "])";

   private static final String REGEX = "" +
      "^" +                                           //start of input
      "(?:" +                                         //non capturing group
         LKA_LOWER + LKA_UPPER + LKA_DIGIT +             //Option 1
            "|" +                                           //or
         LKA_LOWER + LKA_UPPER + LKA_SPECIAL +           //Option 2
            "|" +                                           //or
         LKA_LOWER + LKA_DIGIT + LKA_SPECIAL +           //Option 3
            "|" +                                           //or
         LKA_UPPER + LKA_DIGIT + LKA_SPECIAL +           //Option 4
      ")" +
      "[A-Za-z0-9" + SPECIAL_CHARS + "]" +
      "{8,24}" +                                       //8 to 24 chars
      "$";

   private static final Matcher matcher = Pattern.compile(REGEX).matcher("ignored input");

   public void go() {
      printCrashIfUnexpected(true, "*TE*$Hhu83hu");    //good
      printCrashIfUnexpected(false, "a");              //bad
      printCrashIfUnexpected(false, "asntuh#*$&");     //bad
      printCrashIfUnexpected(true, "1349873aA");       //good
      printCrashIfUnexpected(false, "abCD#*9");        //bad
      printCrashIfUnexpected(true, "abCD#*90");        //good

      System.out.println(REGEX);
   }

   public void printCrashIfUnexpected(boolean trueExpected, String password) {
      boolean isValid = isPasswordValid(password);
      System.out.println(isValid);
      assertEquals(trueExpected, isValid);
   }

   public boolean isPasswordValid(String password) {
      return matcher.reset(password).matches();
   }
}
