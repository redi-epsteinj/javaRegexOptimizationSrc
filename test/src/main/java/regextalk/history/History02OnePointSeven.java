package regextalk.history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class History02OnePointSeven {

   private static final String LINE_SEP = System.getProperty("line.separator", "\n");

   /*
      A good use of regular expressions: Extracting fields from well structured data
    */

   public static void main(String[] ignored) {
      String input = "Smith-43123: John Smith" + LINE_SEP +
                     "Simpson-28347: Bart Simpson" + LINE_SEP +
                     "Frog-20562: Kermit Frog" + LINE_SEP +
                     "Henson-10667: John Henson" + LINE_SEP;
      String regex = "^(?<lastname>\\w+)-(?<id>\\d+): (?<firstname>\\w+) " +
                     "\\k<lastname>$";

      Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
      Matcher matcher = pattern.matcher(input);

      StringBuffer buffer = new StringBuffer();

      while (matcher.find()) {
         final String replacement = matcher.group("firstname") + " " +
                                    matcher.group("lastname") + "'s " +
                                    "id is " + matcher.group("id") + ".";
         matcher.appendReplacement(buffer, replacement);
      }
      matcher.appendTail(buffer);

      System.out.println(buffer.toString());
   }
}
