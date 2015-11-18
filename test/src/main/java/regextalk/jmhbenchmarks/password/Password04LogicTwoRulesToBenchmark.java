package regextalk.jmhbenchmarks.password;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA Password04LogicTwoRulesToBenchmark
 */
//@formatter:on
public class Password04LogicTwoRulesToBenchmark extends AbstractPasswordLogic {

    public Password04LogicTwoRulesToBenchmark() {
        super(2);
    }
}
