package regextalk;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractReusedMatcherToBenchmark implements RegexToBenchmark {

   private Matcher matcher;

   protected void setMatcher(Matcher matcher) {
      this.matcher = matcher;
   }

   public Matcher getMatcher() {
      return matcher;
   }


   @Override
   public void setup() {
      setMatcherOutputIntro(getRegex());
   }

   protected void setMatcherOutputIntro(String regex) {
      System.out.println(getClass().getSimpleName() + ": " + regex);
      matcher = Pattern.compile(regex).matcher(IGNORED_INPUT);
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         boolean inRange = getMatcher().reset(input).matches();
         System.out.printf("* %s: %satched%n", input, (inRange ? "M" : "NOT m"));
      });
   }
}
