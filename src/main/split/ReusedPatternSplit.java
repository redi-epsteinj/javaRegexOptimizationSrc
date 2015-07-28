package main.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ReusedPatternSplit implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new ReusedPatternSplit().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }

  public static final String regex = "[,./ ]+";
  public static final Pattern pattern = Pattern.compile(regex);

  final String input = "One, two, three. four/five six";

  @Override
  public String[] getSplit() {
    return pattern.split(input);
  }
}
