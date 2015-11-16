package com.github.xbn2.number;

/**
 * Functions to catch basic errors, and throw an
 */
public class ThrowIAEIfInt {
   public static void lessThanOET(int toTest, String toTestName, int min) {
      if (toTest <= min) {
         throw new IllegalArgumentException(toTestName + "=" + toTest + ", min allowed is " + min + ".");
      }
   }
   public static void lessThan(int toTest, String toTestName, int min) {
      if (toTest < min) {
         throw new IllegalArgumentException(toTestName + "=" + toTest + ", min allowed is " + min + ".");
      }
   }

   private ThrowIAEIfInt() {
      throw new IllegalStateException("Do not instantiate.");
   }
}
