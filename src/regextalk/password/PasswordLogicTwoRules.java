package regextalk.password;

public class PasswordLogicTwoRules extends AbstractPasswordLogic {

   public PasswordLogicTwoRules() {
      super(2);
   }

   public static void main(String[] args) {
      new PasswordLogicTwoRules().setupRunBreakdown();
   }
}
