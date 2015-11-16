package regextalk.history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnePointFourPreNamedCapturesEnum {

   private static final String LINE_SEP = System.getProperty("line.separator", "\n");

   //Trick to simulate named capturing groups...START
      private static enum CaptureGroupIndex { FIRST_NAME(3), LAST_NAME(1), ID(2);
         private final int groupIndex;

         CaptureGroupIndex(int groupIndex) {
            this.groupIndex = groupIndex;
         }
         public int index() {
            return groupIndex;
         }
      };
      private static final CaptureGroupIndex FIRST_NAME = CaptureGroupIndex.FIRST_NAME;
      private static final CaptureGroupIndex LAST_NAME = CaptureGroupIndex.LAST_NAME;
      private static final CaptureGroupIndex ID = CaptureGroupIndex.ID;
   //Trick to simulate named capturing groups...END

   private static final String INPUT = "Smith-43123: John Smith" + LINE_SEP +
                                       "Simpson-28347: Bart Simpson" + LINE_SEP +
                                       "Frog-20562: Kermit Frog" + LINE_SEP +
                                       "Henson-10667: John Henson" + LINE_SEP;

   private static final String REGEX = "^(\\w+)-(\\d+): (\\w+) \\1$";
   public static void main(String[] ignored) {
      Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
      Matcher matcher = pattern.matcher(INPUT);

      StringBuffer buffer = new StringBuffer();

      while (matcher.find()) {
         final String replacement = matcher.group(FIRST_NAME.index()) + " " +
         matcher.group(LAST_NAME.index()) + "'s id is " + matcher.group(ID.index()) + ".";
         matcher.appendReplacement(buffer, replacement);
      }
      matcher.appendTail(buffer);

      System.out.println(buffer.toString());
   }
}
