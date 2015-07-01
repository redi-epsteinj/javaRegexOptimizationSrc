package replaceall;

/**
 *
 */
public class StringDotReplaceAll implements ReplaceAllToTest {
  public static void main(String[] cmd_lineParams) {
    String s = new StringDotReplaceAll().getReplaced();
    System.out.println(s);
  }

  @Override
  public String getReplaced() {
    final String input = "One, two, three. four/five six";
    final String findWhatRegex = "[,./ ]+";
    final String replaceWithRegex = "---";

    return  input.replaceAll(findWhatRegex, replaceWithRegex);
  }
}
