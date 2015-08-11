package regextalk.password;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordLogicTwoRulesToShow {

   public static final int MIN_LENGTH = 8;
   public static final int RULE_COUNT = 2;

   private Matcher lowerCaseMatcher;
   private Matcher upperCaseMatcher;
   private Matcher digitMatcher;
   private Matcher symbolMatcher;
   private Matcher whitespaceMatcher;

   public PasswordLogicTwoRulesToShow() {
      lowerCaseMatcher = matcherForRegex("[a-z]");
      upperCaseMatcher = matcherForRegex("[A-Z]");
      digitMatcher = matcherForRegex("[0-9]");
      symbolMatcher = matcherForRegex("[!@#$%^&+=_)(}{\\]\\[]");
      whitespaceMatcher = matcherForRegex("\\s");
   }

   private Matcher matcherForRegex(String regex) {
      return Pattern.compile(regex).matcher("ignored input");
   }

   public boolean isPasswordValid(String password) {
      int specialRulesFollowed =
            (lowerCaseMatcher.reset(password).find() ? 1 : 0) +   //SHORT CIRCUIT    //Add 100,
            // 000 to graphs
            (upperCaseMatcher.reset(password).find() ? 1 : 0) +
            (digitMatcher.reset(password).find() ? 1 : 0) +
            (symbolMatcher.reset(password).find() ? 1 : 0);

      boolean hasWhitespace = whitespaceMatcher.reset(password).find();
      boolean isLongEnough = (password.length() >= MIN_LENGTH);

      return ((specialRulesFollowed >= RULE_COUNT) && isLongEnough
              && !hasWhitespace);
   }

   public static void main(String[] ignored) {
      String[] inputs = new String[]{
            "",                     //bad (bad rules, bad length)
            "abcdefghij",           //bad (bad rules, good length)
            "abc123",               //bad (good rules, bad length)
            "abc123abc",            //3 rules: bad, 2 rules: good
            "a1$A",                 //bad (good rules, bad length)
            "abc123$%^ABC",         //good
            "abcABC123$&*",         //good
            "abc ABC123$#$"         //bad (whitespace)
      };

      final PasswordLogicTwoRulesToShow validator = new PasswordLogicTwoRulesToShow();

      Arrays.stream(inputs).forEach(input -> {
         boolean valid = validator.isPasswordValid(input);
         System.out.println("\"" + input + "\" is a valid password: " + valid);
      });
   }
}
