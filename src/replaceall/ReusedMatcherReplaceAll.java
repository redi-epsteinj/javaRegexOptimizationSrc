package replaceall;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class ReusedMatcherReplaceAll implements ReplaceAllToTest {
  public static void main(String[] cmd_lineParams) {
    String s = new ReusedMatcherReplaceAll().getReplaced();
    System.out.println(s);
  }

  public static final String findWhatRegex = "[,./ ]+";
  public static final Matcher matcher = Pattern.compile(findWhatRegex).matcher("ignored input");

  @Override
  public String getReplaced() {
    final String input = "One, two, three. four/five six";
    final String replaceWithRegex = "---";

    return matcher.reset(input).replaceAll(replaceWithRegex);
  }
}
