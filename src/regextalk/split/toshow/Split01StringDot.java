package regextalk.split.toshow;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

public class Split01StringDot {

   public static void main(String[] ignored) {
      String regex = "[,./ ]+";
      String[] inputs = newInputs();
      
      Arrays.stream(inputs).forEach(input -> {
         
         String[] splits = input.split(regex);
         
         String joined = Arrays.stream(splits).collect(joining(" "));
         
         System.out.println('"' + input + "\" -> \"" + joined + '"');
      });
   }
   
   public static final String[] newInputs() {
      return new String[]{
            "",
            ",./ ,. /. ,./. ",
            "One, two, three. four/five six",
            "The snow glows white on the mountain tonight",
            "abcde",
            "a b c d//e"
      };
   }
}
