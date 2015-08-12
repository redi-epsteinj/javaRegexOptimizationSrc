package benchmark;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AllSuiteResults {
   private final List<List<BenchmarkResult>> resultListList;

   public AllSuiteResults(List<List<BenchmarkResult>> resultLists) {
      Objects.requireNonNull(resultLists, "resultLists");
      this.resultListList = Collections.unmodifiableList(resultLists);
   }

   public List<List<BenchmarkResult>> getImmutableResultLists() {
      return resultListList;
   }

   public List<BenchmarkResult> getAveragedResultList() {
      List<List<BenchmarkResult>> lists = getImmutableResultLists();

      List<BenchmarkResult> firstList = lists.get(0);
      int taskCount = firstList.size();
      
      int suiteIters = lists.size();

      BigInteger[] totals = new BigInteger[taskCount];
      for (int idx = 0; idx < totals.length; idx++) {
         totals[idx] = BigInteger.ZERO;
      }
         
      lists.stream().forEach(resultList -> {
         for (int idx = 0; idx < taskCount; idx++) {
            long nanos = resultList.get(idx).getTotalNanos();
            BigInteger bigNanos = BigInteger.valueOf(nanos);
            totals[idx] = totals[idx].add(bigNanos);
         }
      });

      List<BenchmarkResult> resultList = new ArrayList<>(taskCount);
      BigInteger bigIntSuiteIters = BigInteger.valueOf(suiteIters);

      for (int idx = 0; idx < taskCount; idx++) {
         BenchmarkResult firstResultForThisTask = firstList.get(idx);
         long average = totals[idx].divide(bigIntSuiteIters).longValue();
         resultList.add(new BenchmarkResult(firstResultForThisTask.getTask(),
                                            firstResultForThisTask.getIterations(),
                                            average));
      }

      return resultList;
   }
}
