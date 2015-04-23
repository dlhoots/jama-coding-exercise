package hoots.dl.jamaex.market;

import hoots.dl.jamaex.market.item.Item;
import hoots.dl.jamaex.market.item.ItemCache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Represents a Supermarket.
 * 
 * @author David Hoots
 * @since 0.1
 */
@Service
public class Supermarket {
  @Autowired
  private ItemCache itemCache;

  /**
   * Accepts a list of items in the form of IDs concatenated into a single
   * string, calculates the cost for those items and then returns the sum total
   * of the cost for all of the items combined.
   * 
   * For Example, if the string contains "ABCA" then the cost for 2 of A, 1 of
   * B, and 1 of C will be added together and then returned as a single integer.
   * 
   * @param items
   *          The list of items for which to calculate the cost.
   * @return The total cost of all items.
   */
  public int checkout(final String items) {
    final int itemsLength = items.length();
    final Map<String, Integer> itemCounts = new HashMap<>();

    for (int i = 0; i < itemsLength; i++) {
      incrementItemCount(itemCounts, items.substring(i, i + 1));
    }

    return calculateTotalCostOfItems(itemCounts);
  }

  /**
   * Increment the number of a single item in checkout list by one.
   * 
   * @param itemCounts
   *          The counts for all items
   * @param itemId
   *          The item whose count will be incremented by 1
   */
  private void incrementItemCount(final Map<String, Integer> itemCounts, final String itemId) {
    final Integer itemCount = itemCounts.get(itemId);
    if (itemCount == null) {
      itemCounts.put(itemId, 1);
    } else {
      itemCounts.put(itemId, itemCount + 1);
    }
  }

  /**
   * Takes the counts for all items and passes them to their respective cost
   * calculators and returns the total cost for all items.
   * 
   * @param itemCounts
   *          The count of each item found
   * @return The total cost for all items
   */
  private int calculateTotalCostOfItems(final Map<String, Integer> itemCounts) {
    long totalCost = 0;
    for (Map.Entry<String, Integer> itemCount : itemCounts.entrySet()) {
      final Item item = itemCache.getItem(itemCount.getKey());
      totalCost += item.calculateCost(itemCount.getValue());

      if (totalCost > Integer.MAX_VALUE) {
        throw new ArithmeticException("The total cost of all items exceeds the value that can be stored in an int");
      }
    }

    return (int) totalCost;
  }

  public void setItemCache(final ItemCache itemCache) {
    this.itemCache = itemCache;
  }
}
