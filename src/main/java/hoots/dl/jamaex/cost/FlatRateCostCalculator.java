package hoots.dl.jamaex.cost;

/**
 * With this calculator items are always the same price regardless of the
 * quantity.
 * 
 * @author David Hoots
 * @since 0.1
 */
public final class FlatRateCostCalculator implements CostCalculator {
  public FlatRateCostCalculator() {
  }

  @Override
  public long calculate(final int costPerItem, final int numItems) {
    return (long) numItems * costPerItem;
  }
}
