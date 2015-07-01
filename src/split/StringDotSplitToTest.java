package split;

import java.util.Arrays;

public class StringDotSplitToTest implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new StringDotSplitToTest().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }

  @Override
  public String[] getSplit() {
    final String input = "One, two, three. four/five six";
    final String regex = "[,./ ]+";

    return  input.split(regex);
  }
}
