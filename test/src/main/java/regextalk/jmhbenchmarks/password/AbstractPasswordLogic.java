package regextalk.jmhbenchmarks.password;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Pattern;

@State(Scope.Thread)
public abstract class AbstractPasswordLogic implements PasswordToBenchmark {

    private final Pattern lowerCasePattern = Pattern.compile("[a-z]");
    private final Pattern upperCasePattern = Pattern.compile("[A-Z]");
    private final Pattern digitPattern = Pattern.compile("[0-9]");
    private final Pattern symbolPattern = Pattern.compile("[!@#$%^&+=_)(}{\\]\\[]");
    private final Pattern whitespacePattern = Pattern.compile("\\s");
    public static final int MIN_LENGTH = 8;

    private final int specialRuleCount;

    public AbstractPasswordLogic(int special_ruleCount) {
        specialRuleCount = special_ruleCount;
    }

    public int getSpecialRuleCount() {
        return specialRuleCount;
    }

    public boolean isPasswordValid(String password) {
        int specialRulesFollowed =
                (lowerCasePattern.matcher(password).find() ? 1 : 0) +
                (upperCasePattern.matcher(password).find() ? 1 : 0) +
                (digitPattern.matcher(password).find() ? 1 : 0) +
                (symbolPattern.matcher(password).find() ? 1 : 0);
        boolean hasWhitespace = whitespacePattern.matcher(password).find();
        boolean isLongEnough = (password.length() >= MIN_LENGTH);

        return ((specialRulesFollowed >= getSpecialRuleCount()) && isLongEnough
                && !hasWhitespace);
    }

    @Override
    @Benchmark
    public void runCodeToBeTimed() {
        PasswordUtil.runCodeToBeTimed(this);
    }
}
