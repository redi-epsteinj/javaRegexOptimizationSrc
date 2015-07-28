package main.replaceall;

import java.util.regex.Pattern;

/**
 *
 */
public class PatternDotReplaceAll implements ReplaceAllToTest {
  public static void main(String[] cmd_lineParams) {
    String s = new PatternDotReplaceAll().getReplaced();
    System.out.println(s);
  }

  final String input = "One, two, three. four/five six";
  final String findWhatRegex = "[,./ ]+";
  final String replaceWithRegex = "---";

  @Override
  public String getReplaced() {
    return Pattern.compile(findWhatRegex).matcher(input).replaceAll(replaceWithRegex);
  }
}
