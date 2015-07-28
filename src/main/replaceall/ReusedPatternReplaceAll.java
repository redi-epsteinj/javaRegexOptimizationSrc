package main.replaceall;

import java.util.regex.Pattern;

/**
 *
 */
public class ReusedPatternReplaceAll implements ReplaceAllToTest {
  public static void main(String[] cmd_lineParams) {
    String s = new ReusedPatternReplaceAll().getReplaced();
    System.out.println(s);
  }

  public static final String findWhatRegex = "[,./ ]+";
  public static final Pattern pattern = Pattern.compile(findWhatRegex);

  final String input = "One, two, three. four/five six";
  final String replaceWithRegex = "---";

  @Override
  public String getReplaced() {
    return pattern.matcher(input).replaceAll(replaceWithRegex);
  }
}
