package hoots.dl.jamaex.cost;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NForMCostCalculatorTests {

  /**
   * Creates a 7 for 4 calculator and verifies that the calculator returns 
   * the correct total cost.
   */
  @Test
  public void sevenForFourGetCost() {
    NForMCostCalculator calculator = new NForMCostCalculator(7, 4);
    assertEquals(0L, calculator.calculate(37, 0));
    assertEquals(37L, calculator.calculate(37, 1));
    assertEquals(74L, calculator.calculate(37, 2));
    assertEquals(111L, calculator.calculate(37, 3));
    assertEquals(148L, calculator.calculate(37, 4));
    assertEquals(148L, calculator.calculate(37, 5));
    assertEquals(148L, calculator.calculate(37, 6));
    assertEquals(148L, calculator.calculate(37, 7));
    assertEquals(185L, calculator.calculate(37, 8));
    assertEquals(222L, calculator.calculate(37, 9));
    assertEquals(259L, calculator.calculate(37, 10));
    assertEquals(296L, calculator.calculate(37, 11));
    assertEquals(296L, calculator.calculate(37, 12));
    assertEquals(296L, calculator.calculate(37, 13));
    assertEquals(296L, calculator.calculate(37, 14));
    assertEquals(333L, calculator.calculate(37, 15));
  }
}
