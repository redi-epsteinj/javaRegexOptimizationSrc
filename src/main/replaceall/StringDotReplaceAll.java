package main.replaceall;

/**
 *
 */
public class StringDotReplaceAll implements ReplaceAllToTest {
  public static void main(String[] cmd_lineParams) {
    String s = new StringDotReplaceAll().getReplaced();
    System.out.println(s);
  }

  final String input = "One, two, three. four/five six";
  final String findWhatRegex = "[,./ ]+";
  final String replaceWithRegex = "---";

  @Override
  public String getReplaced() {
    return  input.replaceAll(findWhatRegex, replaceWithRegex);
  }
}
