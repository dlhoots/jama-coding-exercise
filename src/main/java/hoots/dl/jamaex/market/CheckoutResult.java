package hoots.dl.jamaex.market;


/**
 * The result of checking out at the {@link Supermarket}.  This doesn't do
 * much right now, but it seems like you'd want to get more than just the
 * total cost from the checkout process.
 * 
 * @author David Hoots
 * @since 0.1
 */
public class CheckoutResult {
  private final int totalCost;

  public CheckoutResult(final int totalCost) {
    this.totalCost = totalCost;
  }

  public int getTotalCost() {
    return totalCost;
  }

  @Override
  public String toString() {
    return "CheckoutResult [totalCost=" + totalCost + "]";
  }
}
