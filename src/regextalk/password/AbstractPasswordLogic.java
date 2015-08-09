package regextalk.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regextalk.RegexToBenchmark;

public abstract class AbstractPasswordLogic implements PasswordToBenchmark {
   private final PasswordToBenchmarkComposer passwordComposer;

   private Matcher lowerCaseMatcher;
   private Matcher upperCaseMatcher;
   private Matcher digitMatcher;
   private Matcher symbolMatcher;
   private Matcher whitespaceMatcher;

   public static final int MIN_LENGTH = 8;

   private final int specialRuleCount;

   public AbstractPasswordLogic(int special_ruleCount) {
      passwordComposer = new PasswordToBenchmarkComposer();
      specialRuleCount = special_ruleCount;
   }

   public int getSpecialRuleCount() {
      return specialRuleCount;
   }

   public void setup() {
      lowerCaseMatcher =
            Pattern.compile("[a-z]").matcher(RegexToBenchmark.IGNORED_INPUT);
      upperCaseMatcher = Pattern.compile("[A-Z]").matcher(
            RegexToBenchmark.IGNORED_INPUT);
      digitMatcher = Pattern.compile("[0-9]").matcher(
            RegexToBenchmark.IGNORED_INPUT);
      symbolMatcher = Pattern.compile("[!@#$%^&+=_)(}{\\]\\[]").matcher(
            RegexToBenchmark.IGNORED_INPUT);
      whitespaceMatcher = Pattern.compile("\\s").matcher(
            RegexToBenchmark.IGNORED_INPUT);
   }

   //Additional rule very difficult to add to regex, but trivial to add to logic: Whitespace not
   //allowed. Well...whitespace already not allowed by regex, because space isn't a special character...  :(
   public boolean isPasswordValid(String password) {
      int
            specialRulesFollowed =
            (lowerCaseMatcher.reset(password).find() ? 1 : 0) +
            (upperCaseMatcher.reset(password).find() ? 1 : 0) +
            (digitMatcher.reset(password).find() ? 1 : 0) +
            (symbolMatcher.reset(password).find() ? 1 : 0);
      boolean hasWhitespace = whitespaceMatcher.reset(password).find();
      boolean isLongEnough = (password.length() >= MIN_LENGTH);

      return ((specialRulesFollowed >= getSpecialRuleCount()) && isLongEnough
              && !hasWhitespace);
   }

   @Override
   public void runCodeToBeTimed() {
      passwordComposer.runCodeToBeTimed(this);
   }
}
