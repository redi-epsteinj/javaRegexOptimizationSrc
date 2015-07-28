package main.split;

import java.util.Arrays;

public class StringDotSplit implements SplitToTest {
  public static void main(String[] cmd_lineParams) {
    String[] strings = new StringDotSplit().getSplit();
    Arrays.stream(strings).forEach(System.out::println);
  }

  final String input = "One, two, three. four/five six";
  final String regex = "[,./ ]+";

  @Override
  public String[] getSplit() {
    return  input.split(regex);
  }
}
