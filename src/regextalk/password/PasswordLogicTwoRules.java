package regextalk.password;

public class PasswordLogicTwoRules extends AbstractPasswordLogic {

   public PasswordLogicTwoRules() {
      super(2);
   }

   @Override
   public void setup() {
      System.out.println(getClass().getSimpleName());
   }

   public static void main(String[] args) {
      new PasswordLogicTwoRules().setupRunBreakdown();
   }
}
