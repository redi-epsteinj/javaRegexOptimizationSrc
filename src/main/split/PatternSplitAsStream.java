package main.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PatternSplitAsStream implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new PatternSplitAsStream().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }
  public static final String regex = "[,./ ]+";
  public static final Pattern pattern = Pattern.compile(regex);

  final String input = "One, two, three. four/five six";

  @Override
  public String[] getSplit() {
    return pattern.splitAsStream(input).toArray(String[]::new);
  }
}
