package regextalk.password.toshow;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Password03LogicTwoRules {

   public static final int MIN_LENGTH = 8;
   public static final int RULE_COUNT = 2;
   public static final boolean WHITESPACE_OK = false;

   private Matcher lowerCaseMatcher =  matcherForRegex("[a-z]");
   private Matcher upperCaseMatcher =  matcherForRegex("[A-Z]");   
   private Matcher digitMatcher =      matcherForRegex("[0-9]");
   private Matcher symbolMatcher =     matcherForRegex(
         "[><?.,!@#$%^&*+=_)(\\}\\{\\]\\[]");
   private Matcher whitespaceMatcher = matcherForRegex("\\s");

   public boolean isPasswordValid(String password) {

      int rulesFollowed = getSpecialRulesFollowedCount(password);
      if(rulesFollowed < RULE_COUNT) { return false; }

      boolean longEnough = (password.length() >= MIN_LENGTH);
      if (!longEnough) { return false; }
      
      if (WHITESPACE_OK) { return true; }
      
      boolean whitespaceWasFoundAndIsBad = whitespaceMatcher.
            reset(password).find();
      return whitespaceWasFoundAndIsBad;
   }

   private int getSpecialRulesFollowedCount(String password) {
      int count = (lowerCaseMatcher.reset(password).find() ? 1 : 0) +
                  (upperCaseMatcher.reset(password).find() ? 1 : 0);

      //Short circuits, for when RULE_COUNT is two or greater.
      if (count >= RULE_COUNT) { return count; }

      count += (digitMatcher.reset(password).find() ? 1 : 0);

      if (count >= RULE_COUNT) { return count; }

      count += (symbolMatcher.reset(password).find() ? 1 : 0);
      return count;
   }
   private Matcher matcherForRegex(String regex) {
      return Pattern.compile(regex).matcher("ignored input");
   }
   public static void main(String[] ignored) {
      Password03LogicTwoRules validator = new Password03LogicTwoRules();  
      Arrays.stream(newInputStringArray()).forEach(input -> {
         boolean valid = validator.isPasswordValid(input);
         System.out.printf("\"%s\" is %s password.%n", input,
                           (valid ? "a VALID" : "an invalid"));
      });
   }

   private static String[] newInputStringArray() {
      return new String[] {
            "",                     //bad (bad rules, bad length)
            "abcdefghij",           //bad (bad rules, good length)
            "abc123",               //bad (good rules, bad length)
            "abc123abc",            //3 rules: bad, 2 rules: good
            "a1$A",                 //bad (good rules, bad length)
            "abc123$%^ABC",         //good
            "abcABC123$&*",         //good
            "abc ABC123$#$"         //bad (whitespace)
      };
   }
}
