package regextalk.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordLogicThreeRules implements PasswordToBenchmark {
   private final Matcher lowerCaseMatcher;
   private final Matcher upperCaseMatcher;
   private final Matcher digitMatcher;
   private final Matcher symbolMatcher;
   private final Matcher whitespaceMatcher;

   public static final int MIN_LENGTH = 8;
   public static final int SPECIAL_RULE_COUNT = 3;

   public PasswordLogicThreeRules() {
      lowerCaseMatcher =  Pattern.compile("[a-z]"                 ).matcher(IGNORED_INPUT);
      upperCaseMatcher =  Pattern.compile("[A-Z]"                 ).matcher(IGNORED_INPUT);
      digitMatcher =      Pattern.compile("[0-9]"                 ).matcher(IGNORED_INPUT);
      symbolMatcher =     Pattern.compile("[!@#$%^&+=_)(}{\\]\\[]").matcher(IGNORED_INPUT);
      whitespaceMatcher = Pattern.compile("\\s"                   ).matcher(IGNORED_INPUT);
   }

   public static void main(String[] args) {
      new PasswordLogicThreeRules().runCodeToBeTimed();
   }


   //Additional rule very difficult to add to regex, but trivial to add to logic: Whitespace not
   //allowed. Well...whitespace already not allowed by regex, because space isn't a special character...  :(
   public boolean isPasswordValid(String password) {
      int specialRulesFollowed = (lowerCaseMatcher.reset(password).find() ? 1 : 0) +
                                 (upperCaseMatcher.reset(password).find() ? 1 : 0) +
                                 (digitMatcher.reset(password).find() ? 1 : 0) +
                                 (symbolMatcher.reset(password).find() ? 1 : 0);
      boolean hasWhitespace = whitespaceMatcher.reset(password).find();
      boolean isLongEnough = (password.length() >= MIN_LENGTH);

      return  ((specialRulesFollowed >= SPECIAL_RULE_COUNT) && isLongEnough && !hasWhitespace);
   }
}
