package regextalk.history;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Performance of regular expressions in Java
 * History, Anti-patterns, and step-by-step optimizations (with benchmarks to prove it)
 *
 * Sort files by name
 *
 * Poll at start: How familiar are you with regular expressions.
 *
 *
 * Slides: title, agenda
 *
 * Password last as "good use"
 *
 * extract fields from structured data.
 * 
 * Bounds PLUS matches() slowed by having to check redundant bounds.
 */
public class History01OnePointFour {
   public static void main(String[] ignored) {
      String regexContainsOneOrMoreEs = "\\w*?e\\w*";
      String input = "one two three four five six seven eight";

      Pattern pattern = Pattern.compile(regexContainsOneOrMoreEs);
      Matcher matcher = pattern.matcher(input);

      StringBuffer buffer = new StringBuffer();

      while (matcher.find()) {
         final String match = matcher.group();
         matcher.appendReplacement(buffer, match.toUpperCase());
      }
      matcher.appendTail(buffer);

      System.out.println(buffer.toString());    //"ONE two THREE four FIVE six SEVEN EIGHT"
   }
}
