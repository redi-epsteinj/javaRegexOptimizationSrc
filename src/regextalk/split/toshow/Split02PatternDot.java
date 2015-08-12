package regextalk.split.toshow;

import java.util.Arrays;
import java.util.regex.Pattern;

import regextalk.split.AbstractSplit;

public class Split02PatternDot extends AbstractSplit {

   public Split02PatternDot(String regex) {
       super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new Split02PatternDot(REGEX_SPACE).setupRunBreakdown();
      new Split02PatternDot(REGEX_2_PLUS).setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(newInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
               Pattern.compile(getRegex()).split(input)
         ));
      });
   }
}
