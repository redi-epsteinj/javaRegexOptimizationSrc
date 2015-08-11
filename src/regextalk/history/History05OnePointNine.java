package regextalk.history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class History05OnePointNine {

   public static void main(String[] ignored) {
      String input = "one two three four five six seven eight";
      String regex = "\\b([a-z]+)\\b";

      Matcher matcher = Pattern.compile(regex).matcher(input);

      StringBuffer buffer = new StringBuffer();

      //Replace each word containing an 'e', with its all-cap version

      //Java < 1.4:
         while (matcher.find()) {
            final String match = matcher.group();
            final String replacement = match.contains("e") ? match.toUpperCase() : match;
            matcher.appendReplacement(buffer, replacement);
         }
         matcher.appendTail(buffer);

         System.out.println(buffer.toString());   //"ONE two THREE four FIVE six SEVEN EIGHT"

/*
      //Java 1.9
         String output = matcher.reset(input).
               matchesAsStream().forEach(match ->
                                     matcher.contains("e") ? matcher.toUpperCase() : matcher).
               collect.joining(" ");

         System.out.println(output);   //"ONE two THREE four FIVE six SEVEN EIGHT"
*/

   }
}
