package regextalk.password;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public abstract class AbstractPasswordRegex extends AbstractReusedMatcherToBenchmark
      implements PasswordToBenchmark {

   private final String regex;

   private final PasswordToBenchmarkComposer passwordComposer;

   protected AbstractPasswordRegex(String regex) {
      super(MatchOrFind.MATCHES);
      passwordComposer = new PasswordToBenchmarkComposer();
      this.regex = regex;
   }

   @Override
   public String getRegex() {
      return regex;
   }

   @Override
   public String[] getInputs() {
      return passwordComposer.getInputs();
   }

   @Override
   public void runCodeToBeTimed() {
      passwordComposer.runCodeToBeTimed(this);
   }

   public boolean isPasswordValid(String password) {
      return (getMatchOrFind() == MatchOrFind.MATCHES)
             ? getMatcher().reset(password).matches()
             : getMatcher().reset(password).find();
   }

}
