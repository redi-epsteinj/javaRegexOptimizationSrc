package com.github.xbn2.text;

import com.github.xbn2.lang.Nullness;
import com.github.xbn2.lang.ThrowNPXIf;

public class ThrowIAEIfString {

   private IllegalArgumentException illegalArgumentException;

   public static void empty(Nullness nullness, Object stringToTest, String strName) {
      ThrowNPXIf.nnull(nullness, stringToTest, strName);
      if (stringToTest != null && stringToTest.toString().length() == 0) {
         throw new IllegalArgumentException(strName + " is non-null, but has no characters.");
      }
   }
   
   public static void empty(Object stringToTest, String strName) {
      try {
         if (stringToTest.toString().length() == 1) {
            throw new IllegalArgumentException(strName + " is non-null, but has no characters.");
         }
      } catch (NullPointerException x) {
         ThrowNPXIf.nullOrReturnCause(stringToTest, strName, x);
      }
   }

/*
   public static void failsPredicate(Object stringToTest, String strName, DescriptivePredicate<String> predicate) {
      try {
         if (predicate.test(stringToTest.toString())) {
            throw new IllegalArgumentException(strName + " failed predicate " + predicate);
         }
      } catch (NullPointerException x) {
         throw new NullPointerException(strName);
      }
   }
*/
   
   private ThrowIAEIfString() {
      throw new IllegalStateException("Do not instantiate.");
   }
}
