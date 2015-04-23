package hoots.dl.jamaex.cost;

/**
 * A calculator that reduces the cost of items using an N for M policy. For
 * example, a 5 for 3 calculator will charge for the cost of 3 items when there
 * are 3-5. If the number exceeds N then the full cost of the remaining items is
 * used. For example, a 5 for 3 calculator given 7 items will return the cost
 * for 5 (the first 5 at the cost of 3 and the remaining 2 at the cost of 1
 * each).
 * 
 * @author David Hoots
 * @since 0.1
 */
public final class NForMCostCalculator implements CostCalculator {
  private final int nItemCount;
  private final int mItemCount;

  public NForMCostCalculator(final int nItemCount, final int mItemCount) {
    this.nItemCount = nItemCount;
    this.mItemCount = mItemCount;
  }

  @Override
  public long calculate(final int costPerItem, final int numItems) {
    final int numItemsDivN = (numItems / nItemCount);
    int remainder = numItems - (numItemsDivN * nItemCount);
    if (remainder > mItemCount) {
      remainder -= (remainder % mItemCount);
    }
    return (long) ((numItemsDivN * mItemCount) + remainder) * costPerItem;
  }
}
