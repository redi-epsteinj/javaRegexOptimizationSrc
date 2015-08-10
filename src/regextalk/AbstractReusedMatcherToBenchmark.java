package regextalk;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractReusedMatcherToBenchmark implements RegexToBenchmark {

   private final MatchOrFind matchOrFind;

   public AbstractReusedMatcherToBenchmark() {
       this(MatchOrFind.MATCHES);
   }
   
   public AbstractReusedMatcherToBenchmark(MatchOrFind match_orFind) {
      matchOrFind = match_orFind;
   }

   private Matcher matcher;

   protected void setMatcher(Matcher matcher) {
      this.matcher = matcher;
   }

   public MatchOrFind getMatchOrFind() {
      return matchOrFind;
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
      System.out.println(getMatchOrFind());
      int[] indexElem0 = {0};
      Arrays.stream(getInputs()).forEach(input -> {
         if (input == null) {
            String message = format("input element %d is null. All inputs: %s", indexElem0[0],
                                    Arrays.toString(getInputs()));
         }

         boolean inRange = (getMatchOrFind() == MatchOrFind.MATCHES)
                           ? getMatcher().reset(input).matches()
                           : getMatcher().reset(input).find();
         System.out.printf("* %s: %satched%n", input, (inRange ? "M" : "NOT m"));
         indexElem0[0]++;
      });
   }
}
