package split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ReusedPatternSplitToTest implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new ReusedPatternSplitToTest().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }

  public static final String regex = "[,./ ]+";
  public static final Pattern pattern = Pattern.compile(regex);

  @Override
  public String[] getSplit() {
    final String input = "One, two, three. four/five six";

    return pattern.split(input);
  }
}
