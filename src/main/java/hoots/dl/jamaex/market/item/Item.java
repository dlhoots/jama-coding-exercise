package hoots.dl.jamaex.market.item;

import hoots.dl.jamaex.cost.CostCalculator;
import hoots.dl.jamaex.cost.CostCalculators;
import hoots.dl.jamaex.market.Supermarket;

/**
 * Represents an item available for purchase at the {@link Supermarket}
 * 
 * @author David Hoots
 * @since 0.1
 */
public class Item {
  private static CostCalculators costCalculators = new CostCalculators();

  private String id;
  private int cost;
  private CostCalculator costCalculator;

  public Item() {
  }

  public Item(final String id, final int cost, final CostCalculator costCalculator) {
    this.id = id;
    this.cost = cost;
    this.costCalculator = costCalculator;
  }

  public String getId() {
    return this.id;
  }

  public int getCost() {
    return this.cost;
  }

  /**
   * Calculates the total cost of a group of these items.
   * 
   * @param count The number of these items
   * @return The total cost for <code>count</code> items
   */
  public long calculateCost(final int count) {
    return this.costCalculator.calculate(this.cost, count);
  }

  @Override
  public String toString() {
    return "Item [id=" + this.id + ", cost=" + this.cost + ", costCalculator=" + this.costCalculator + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Item other = (Item) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public static class Builder {
    private String id;
    private int cost;
    private String costCalculatorId;

    public Item.Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Item.Builder setCost(int cost) {
      this.cost = cost;
      return this;
    }

    public Item.Builder setCostCalculatorId(String costCalculatorId) {
      this.costCalculatorId = costCalculatorId;
      return this;
    }

    public Item build() {
      return new Item(this.id, this.cost, costCalculators.getCostCalculator(CostCalculators.ID
          .valueOf(this.costCalculatorId)));
    }
  }
}
