package hoots.dl.jamaex.market.item;

import static org.junit.Assert.assertEquals;
import hoots.dl.jamaex.cost.CostCalculators;

import org.junit.Test;

public class ItemTests {

  /**
   * Verifies that the getters return the values set.
   */
  @Test
  public void getters() {
    final Item.Builder builder = new Item.Builder();
    builder.setId("AAA").setCost(37).setCostCalculatorId(CostCalculators.ID.FLAT.toString());
    final Item item = builder.build();

    assertEquals("AAA", item.getId());
    assertEquals(37, item.getCost());
  }
  
  /**
   * Verifies that the item correctly calls the CostCalculator.
   */
  @Test
  public void calculateCost() {
    final Item.Builder builder = new Item.Builder();
    builder.setId("AAA").setCost(37).setCostCalculatorId(CostCalculators.ID.FLAT.toString());
    final Item item = builder.build();

    assertEquals(407, item.calculateCost(11));
  }
  
  /**
   * Verifies that an exception is thrown when an item is given an unknown
   * CostCalculator ID.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidCostCalculatorId() {
    final Item.Builder builder = new Item.Builder();
    builder.setId("AAA").setCost(37).setCostCalculatorId("**UNKNOWN**");
    final Item item = builder.build();

    assertEquals(407, item.calculateCost(11));
  }
}
