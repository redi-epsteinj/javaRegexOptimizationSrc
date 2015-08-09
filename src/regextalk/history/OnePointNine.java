package regextalk.history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnePointNine {
   private static final String INPUT = "one two three four five six seven "
                                       + "eight";
   private static final String REGEX = "\\b([a-z]+)\\b";
   private static final int MIN_MATCH = 2;
   private static final int MAX_MATCH = 4;

   public static void main(String[] ignored) {
      Pattern pattern = Pattern.compile(REGEX);
      Matcher matcher = pattern.matcher(INPUT);

      int idx = 0;
      StringBuffer buffer = new StringBuffer();
      
      while (matcher.find()) {
         idx++;
         String replacement = (idx >= MIN_MATCH && idx <= MAX_MATCH)
               ? matcher.group().toUpperCase() : matcher.group();

         matcher.appendReplacement(buffer, replacement);
      }
      matcher.appendTail(buffer);

      System.out.println(buffer.toString());
   }
}
