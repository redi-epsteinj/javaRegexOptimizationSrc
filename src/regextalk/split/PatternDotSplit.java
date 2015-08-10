package regextalk.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PatternDotSplit extends AbstractSplitToBenchmark {

   public PatternDotSplit(String regex) {
       super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new PatternDotSplit(REGEX_SPACE).setupRunBreakdown();
      new PatternDotSplit(REGEX_2_PLUS).setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
               Pattern.compile(getRegex()).split(input)
         ));
      });
   }
}
