package other;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordLogic {
   public static void main(String[] args) {
      new PasswordLogic().go();
   }

   public void go() {
      printCrashIfUnexpected(true, "*TE*$Hhu83hu");    //good
      printCrashIfUnexpected(false, "a");              //bad
      printCrashIfUnexpected(false, "asntuh#*$&");     //bad
      printCrashIfUnexpected(true, "1349873aA");       //good
      printCrashIfUnexpected(false, "abCD#*9");        //bad
      printCrashIfUnexpected(true, "abCD#*90");        //good

      printCrashIfUnexpected(false, "abc 012 &*(");     //bad

   }

   public void printCrashIfUnexpected(boolean trueExpected, String password) {
      boolean isValid = isPasswordValid(password);
      System.out.println(isValid);
      assertEquals(trueExpected, isValid);
   }

   Matcher lowerCaseMatcher = Pattern.compile("[a-z]").matcher("ignored input");
   Matcher upperCaseMatcher = Pattern.compile("[A-Z]").matcher("ignored input");
   Matcher digitMatcher = Pattern.compile("[0-9]").matcher("ignored input");
   Matcher symbolMatcher = Pattern.compile("[!@#$%^&+=_)(}{\\]\\[]").matcher("ignored input");
   Matcher whitespaceMatcher = Pattern.compile("\\s").matcher("ignored input");
   private static final int MIN_LENGTH = 8;

   //Additional rule very difficult to add to regex, but trivial to add to logic: Whitespace not
   //allowed. Well...whitespace already not allowed by regex, because space isn't a special character...  :(
   public boolean isPasswordValid(String password) {
      int specialRulesFollowed = (lowerCaseMatcher.reset(password).find() ? 1 : 0) +
                                 (upperCaseMatcher.reset(password).find() ? 1 : 0) +
                                 (digitMatcher.reset(password).find() ? 1 : 0) +
                                 (symbolMatcher.reset(password).find() ? 1 : 0);
      boolean hasWhitespace = whitespaceMatcher.reset(password).find();
      boolean isLongEnough = (password.length() >= MIN_LENGTH);

      return  ((specialRulesFollowed >= 3) && isLongEnough && !hasWhitespace);
   }
}
