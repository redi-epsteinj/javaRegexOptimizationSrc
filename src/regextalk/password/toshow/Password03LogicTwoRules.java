package regextalk.password.toshow;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password03LogicTwoRules {

   public static final int MIN_LENGTH = 8;
   public static final int RULE_COUNT = 2;
   public static final boolean WHITESPACE_OK = false;

   private Matcher lowerCaseMatcher = matcherForRegex("[a-z]");
   private Matcher upperCaseMatcher = matcherForRegex("[A-Z]");    //Give a minute to grok
   private Matcher digitMatcher = matcherForRegex("[0-9]");
   private Matcher symbolMatcher = matcherForRegex("[!@#$%^&+=_)(}{\\]\\[]");
   private Matcher whitespaceMatcher = matcherForRegex("\\s");

   private Matcher matcherForRegex(String regex) {
      return Pattern.compile(regex).matcher("ignored input");
   }

   public boolean isPasswordValid(String password) {

      int specialRulesFollowedCount = getSpecialRulesFollowedCount(password);
      if(specialRulesFollowedCount < RULE_COUNT) { return false; }

      boolean longEnough = (password.length() >= MIN_LENGTH);
      if(!longEnough) { return false; }
      
      boolean hasWhitespace = whitespaceMatcher.reset(password).find();
      boolean whitespaceBad = !WHITESPACE_OK;
      boolean whitespaceRuleViolated = (hasWhitespace && whitespaceBad);
      
      if (whitespaceRuleViolated) { return false; }

      return true;
   }

   private int getSpecialRulesFollowedCount(String password) {
      int count = (lowerCaseMatcher.reset(password).find() ? 1 : 0) +
                  (upperCaseMatcher.reset(password).find() ? 1 : 0);

      //Short circuiting, for when RULE_COUNT happens to be two or greater.
      if (count < RULE_COUNT) {
         count = (digitMatcher.reset(password).find() ? 1 : 0);
      }

      if (count < RULE_COUNT) {
         count = (symbolMatcher.reset(password).find() ? 1 : 0);
      }

      return count;
   }

   public static void main(String[] ignored) {
      
      String[] inputs = Password01RegexThreeRules.newInputs();
      
      Password03LogicTwoRules validator = new Password03LogicTwoRules();
      
      Arrays.stream(inputs).forEach(input -> {

         boolean valid = validator.isPasswordValid(input);
         System.out.printf("\"%s\" is %s password.%n", input, (valid ? "a VALID" : "an invalid"));
      });
   }
}
