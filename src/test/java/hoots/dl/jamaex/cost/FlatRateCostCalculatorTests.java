package hoots.dl.jamaex.cost;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlatRateCostCalculatorTests {

  /**
   * Verifies that the cost calculator doesn't vary with the item count.
   */
  @Test
  public void getCost() {
    FlatRateCostCalculator calculator = new FlatRateCostCalculator();
    assertEquals(0L, calculator.calculate(37, 0));
    assertEquals(37L, calculator.calculate(37, 1));
    assertEquals(74L, calculator.calculate(37, 2));
    assertEquals(111L, calculator.calculate(37, 3));
    assertEquals(148L, calculator.calculate(37, 4));
    assertEquals(185L, calculator.calculate(37, 5));
    assertEquals(222L, calculator.calculate(37, 6));
    assertEquals(259L, calculator.calculate(37, 7));
    assertEquals(296L, calculator.calculate(37, 8));
    assertEquals(333L, calculator.calculate(37, 9));
    assertEquals(370L, calculator.calculate(37, 10));
    assertEquals(407L, calculator.calculate(37, 11));
  }
}
