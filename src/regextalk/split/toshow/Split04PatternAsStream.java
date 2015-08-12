package regextalk.split.toshow;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.regex.Pattern;

import regextalk.split.AbstractSplit;

public class Split04PatternAsStream extends AbstractSplit {

   public Split04PatternAsStream(String regex) {
      super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new Split04PatternAsStream(REGEX_SPACE).setupRunBreakdown();
      new Split04PatternAsStream(REGEX_2_PLUS).setupRunBreakdown();
   }

   private static final String CLOSE_QT_COMMA_OPEN_QT = "\", \"";

   @Override
   public void runCodeToBeTimed() {
      Pattern pattern = Pattern.compile(getRegex());
      Arrays.stream(newInputs()).forEach(input -> {
         System.out.println(input);
         String splitsJoined = pattern.splitAsStream(input).collect(
               joining(CLOSE_QT_COMMA_OPEN_QT, "\"", "\""));
         System.out.println("\t" + splitsJoined);
      });
   }
}
