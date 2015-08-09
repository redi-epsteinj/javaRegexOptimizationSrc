package regextalk.password;

public class PasswordLogicThreeRules extends AbstractPasswordLogic {
   public PasswordLogicThreeRules() {
      super(3);
   }

   @Override
   public void setup() {
      System.out.println(getClass().getSimpleName());
   }

   public static void main(String[] args) {
      new PasswordLogicThreeRules().setupRunBreakdown();
   }
}
