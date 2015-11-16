package regextalk.password.toshow;

public class Test {

   public static void main(String[] cmd_lineParams) {
      System.out.println(5/2);

      String[] ss = {"a", "b", "c", "d", "e"};
      for (int index = 0; index <= (ss.length / 2); index += 2) {
         System.out.print(ss[index] + ss[index + 1]);
         if(index < (ss.length / 2 - 1)) {
            System.out.print(", ");
         }
      }
   }
}
