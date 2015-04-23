package hoots.dl.jamaex.cost;

/**
 * Calculates the total cost of a number if identically priced items.
 * Implementers will use different formulas for calculating the total cost of
 * the items.
 * 
 * @author David Hoots
 * @since 0.1
 */
public interface CostCalculator {

  /**
   * Calculates the total cost of identically priced items.
   * 
   * @param cost
   *          The cost of each item
   * @param numItems
   *          The number of items
   * @return The total cost of all items
   */
  public long calculate(final int cost, final int numItems);
}
