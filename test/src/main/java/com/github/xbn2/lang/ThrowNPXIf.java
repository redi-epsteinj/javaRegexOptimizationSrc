package com.github.xbn2.lang;

import java.util.Objects;

/**
 * Functions that throw {@link NullPointerException}s if an object is null, where the entirety of
 * the message is the name of the object.
 */
public class ThrowNPXIf {

   /**
    * <p>If an object is null, crash--otherwise, <i>return</i> the causing error (which must be a
    * runtime exception).</p>
    *
    * @param toTest The object that may be null.
    * @param cause  May not be null.
    */
   public static final RuntimeException nullOrReturnCause(Object toTest, String objName,
                                                          RuntimeException cause) {
      Objects.requireNonNull(cause, "cause");
      nnull(Nullness.BAD, toTest, objName);
      return cause;
   }

   /**
    * <p>If an object is null, and that's a bad thing, crash. Otherwise, do nothing.</p>
    *
    * @param toTest The object to test.
    * @throws NullPointerException If {@code toTest} is null and {@code nullness} is {@link
    *                              Nullness#BAD BAD}
    * @see #nullOrReturnCause(Object, String, RuntimeException) 
    */
   public static final void nnull(Nullness nullness, Object toTest, String objName) {
      try {
         if (toTest == null && !nullness.isOk()) {
            throw new NullPointerException(objName);
         }
      } catch (NullPointerException x) {
         if (nullness == null) {
            throw new NullPointerException("nullness");
         }
         throw x;
      }
   }

   private ThrowNPXIf() {
      throw new IllegalStateException("Do not instantiate.");
   }
}
