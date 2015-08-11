package regextalk.numericrange.toshow;

import java.util.stream.IntStream;

/**
 * From http://java-buddy.blogspot.com/
 */
public class AllUnicodeDigits {

   public static void main(String[] args) {
      int maxCodePoint = Character.MAX_CODE_POINT;
      System.out.printf("MAX_CODE_POINT = %x\n", maxCodePoint);

      IntStream.range(0, Character.MAX_CODE_POINT).forEach(point -> {
         if(Character.isDigit(point)) {
            System.out.printf("Unicode: %x\t%c\tName:%s\n",
                              point, point, Character.getName(point));
         }
      });
   }
}
