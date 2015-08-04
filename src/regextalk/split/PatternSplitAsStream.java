package regextalk.split;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PatternSplitAsStream implements SplitToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new PatternSplitAsStream().runCodeToBeTimed();
   }

   private static final String CLOSE_QT_COMMA_OPEN_QT = "\", \"";

   @Override
   public void runCodeToBeTimed() {
      Pattern pattern = Pattern.compile(REGEX);
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         String splitsJoined = pattern.splitAsStream(input).collect(
               joining(CLOSE_QT_COMMA_OPEN_QT, "\"", "\""));
         System.out.println("\t" + splitsJoined);
      });
   }
}
