package main.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PatternDotSplit implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new PatternDotSplit().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }

  final String input = "One, two, three. four/five six";
  final String regex = "[,./ ]+";

  @Override
  public String[] getSplit() {
    return Pattern.compile(regex).split(input);
  }
}
