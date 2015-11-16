package regextalk.history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class History03OnePointFourPreNamedCaptures {


   //Any change to the regex must be reflected in the below int constants.
   private static final String REGEX = "^(\\w+)-(\\d+): (\\w+) \\1$";

   //Simulates named capture groups
      private static final int FIRST_NAME = 3;
      private static final int LAST_NAME = 1;
      private static final int ID = 2;

   private static final String LINE_SEP = System.getProperty("line.separator", "\n");
   private static final String INPUT = "Smith-43123: John Smith" + LINE_SEP +
                                       "Simpson-28347: Bart Simpson" + LINE_SEP +
                                       "Frog-20562: Kermit Frog" + LINE_SEP +
                                       "Henson-10667: John Henson" + LINE_SEP;

   public static void main(String[] ignored) {
      Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
      Matcher matcher = pattern.matcher(INPUT);

      StringBuffer buffer = new StringBuffer();

      while (matcher.find()) {
         final String replacement = matcher.group(FIRST_NAME) + " " +
         matcher.group(LAST_NAME) + "'s id is " + matcher.group(ID) + ".";

         matcher.appendReplacement(buffer, replacement);
      }
      matcher.appendTail(buffer);

      System.out.println(buffer.toString());
   }
}
