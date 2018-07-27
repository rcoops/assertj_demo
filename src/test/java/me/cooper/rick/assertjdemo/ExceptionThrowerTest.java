package me.cooper.rick.assertjdemo;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExceptionThrowerTest {

  private static final ExceptionThrower thrower = new ExceptionThrower();

  /*
      assertThatNullPointerException
      assertThatIllegalArgumentException
      assertThatIllegalStateException
      assertThatIOException
   */
  @Test
  public void throwsAnExceptionTest() {
    assertThatExceptionOfType(CustomException.class)
        .isThrownBy(thrower::throwCustomException)
        .withMessage("this is an awesome exception...")
        .withCause(NullPointerException);
  }

  /*
      assertThatNullPointerException
      assertThatIllegalArgumentException
      assertThatIllegalStateException
      assertThatIOException
   */
  @Test
  public void throwsASpecificExceptionTest() {
    assertThatNullPointerException()
        .isThrownBy(thrower::throwCustomException)
        .withMessage("this is an awesome exception...")
        .withNoCause();
  }

}
