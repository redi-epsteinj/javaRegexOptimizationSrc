package regextalk.password;

public class PasswordLogicThreeRules extends AbstractPasswordLogic {

   public PasswordLogicThreeRules() {
      super(3);
   }

   public static void main(String[] args) {
      new PasswordLogicThreeRules().runCodeToBeTimed();
   }
}
