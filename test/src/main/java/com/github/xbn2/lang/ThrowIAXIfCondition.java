package com.github.xbn2.lang;

import java.util.function.Predicate;

public class ThrowIAXIfCondition {
   public static <T> void met(T toTest, String toTestName, Predicate<T> condition, String conditionDescription) {
      try {
         if (condition.test(toTest)) {
            throw new IllegalArgumentException(toTestName + " matches bad condition: " + conditionDescription);
         }
      } catch(NullPointerException x) {
         throw new NullPointerException("condition");
      }
   }
   public static <T> void notMet(T toTest, String toTestName, Predicate<T> condition, String conditionDescription) {
      try {
         if (!condition.test(toTest)) {
            throw new IllegalArgumentException(toTestName + " does not meet condition: " + conditionDescription);
         }
      } catch(NullPointerException x) {
         throw new NullPointerException("condition");
      }
   }
}
