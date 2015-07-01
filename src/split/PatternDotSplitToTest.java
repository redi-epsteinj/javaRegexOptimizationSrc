package split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PatternDotSplitToTest implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new PatternDotSplitToTest().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }
  @Override
  public String[] getSplit() {
    final String input = "One, two, three. four/five six";
    final String regex = "[,./ ]+";

    return Pattern.compile(regex).split(input);
  }
}
