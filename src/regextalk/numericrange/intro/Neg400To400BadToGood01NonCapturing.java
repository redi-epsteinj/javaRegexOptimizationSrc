package regextalk.numericrange.intro;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg400To400BadToGood01NonCapturing extends AbstractReusedMatcherToBenchmark {

   public static void main(String[] ignored) {
      new Neg400To400BadToGood01NonCapturing().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() {
      List<String> intStrList = IntStream.of(-401, 402).boxed().map(Object::toString).
            collect(Collectors.toList());
      Arrays.stream(Neg400To400Good400Last.INPUTS).forEach(num -> {
         String strNum = new Integer(num).toString();
         if (!intStrList.contains(strNum)) {
            intStrList.add(strNum);
         }
      });

      return intStrList.toArray(new String[intStrList.size()]);
   }

   @Override
   public String getRegex() {
      //No bounds, captured, redundancies, unmaintainable.
      //But! Obviously correct, so a good starting point.
      return "(?:" + Neg400To400Bad.NEG_400_TO_400 + ")";
   }
}
