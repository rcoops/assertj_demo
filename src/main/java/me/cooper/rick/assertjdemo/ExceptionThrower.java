package me.cooper.rick.assertjdemo;

/**
 * Guess
 */
public class ExceptionThrower {

  public void throwCustomException() throws Exception {
    try {
      throw new NullPointerException();
    } catch (final NullPointerException e) {
      throw new CustomException("this is an awesome exception...");
    }

  }

}
