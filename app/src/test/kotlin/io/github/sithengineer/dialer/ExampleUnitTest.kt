package io.github.sithengineer.dialer

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
  private val x = 2
  private val y = 3

  @Test
  fun additionIsCorrect() {
    assertEquals(5, x + y)
    assertEquals(5, y + x)
  }

  @Test
  fun subtractionIsCorrect() {
    assertEquals(-1, x - y)
    assertEquals(1, y - x)
  }
}
