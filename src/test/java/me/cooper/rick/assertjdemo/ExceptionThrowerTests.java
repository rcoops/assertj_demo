package me.cooper.rick.assertjdemo;

import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class ExceptionThrowerTests {

  private static final ExceptionThrower thrower = new ExceptionThrower();

  /*
      assertThatNullPointerException
      assertThatIllegalArgumentException
      assertThatIllegalStateException
      assertThatIOException
   */

  private static final NumberFormatException nfe = new NumberFormatException();

  @Test
  public void oldOldTestException() {
    try {
      throw new NullPointerException("this is an awesome exception...");
    } catch (NullPointerException e) {
      assertThat(e).isOfAnyClassIn(NullPointerException.class);
      assertThat(e).hasMessage("this is an awesome exception...");
      assertThat(e).hasNoCause();
    }
  }

  @Test
  public void oldTestException() {
    assertThatThrownBy(() -> { throw new Exception("this is an awesome exception..."); })
        .isInstanceOf(Exception.class)
        .hasMessageContaining("awesome");
  }

  @Test
  public void testExceptionAlternative() {
    assertThatExceptionOfType(Throwable.class)
        .isThrownBy(() -> { throw new Throwable("this is an awesome exception...", nfe); })
        .withMessageContaining("awesome")
        .withMessage("this is an awesome exception...")
        .withCauseInstanceOf(NumberFormatException.class)
        .withCause(nfe);
  }

  @Test
  public void testExceptionAlternativeWithSugar() {
    assertThatIllegalArgumentException() // needs 3.7 min
        .isThrownBy(() -> { throw new IllegalArgumentException(); });
  }

  @Test
  public void testExceptionBDD() {
    // given some preconditions

    // when
    Throwable thrown = catchThrowable(() -> { throw new Exception("boom!"); });

    // then
    assertThat(thrown).isInstanceOf(Exception.class)
        .hasMessageContaining("boom");
  }

  /** FAILED TESTS */

  @Test
  public void oldTestExceptionFail() {
    try {
      fail("it didn't happen as planned");
    } catch (NullPointerException e) {
      assertThat(e).isOfAnyClassIn(NullPointerException.class);
    }
  }

  @Test
  public void testFailAlternative() {
    assertThatNullPointerException()
        .as("it didn't happen as planned") // needs 3.9 min
        .isThrownBy(() -> {});
  }

}
