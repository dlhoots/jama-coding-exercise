package hoots.dl.jamaex.market;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hoots.dl.jamaex.cost.CostCalculators;
import hoots.dl.jamaex.market.item.Item;
import hoots.dl.jamaex.market.item.ItemCache;
import hoots.dl.jamaex.market.item.ItemRepository;

import org.junit.Test;

public class SupermarketTests {
  private CostCalculators costCalculators = new CostCalculators();

  /**
   * Basic checkout case where everything works as expected.
   */
  @Test
  public void checkout() {
    ItemCache itemCache = mock(ItemCache.class);
    when(itemCache.getItem("A")).thenReturn(
        new Item("A", 20, costCalculators.getCostCalculator(CostCalculators.ID.FLAT)));
    when(itemCache.getItem("B")).thenReturn(
        new Item("B", 50, costCalculators.getCostCalculator(CostCalculators.ID.N4M5F3)));
    when(itemCache.getItem("C")).thenReturn(
        new Item("C", 30, costCalculators.getCostCalculator(CostCalculators.ID.FLAT)));

    final Supermarket supermarket = new Supermarket();
    supermarket.setItemCache(itemCache);

    assertEquals(0, supermarket.checkout(""));
    assertEquals(20, supermarket.checkout("A"));
    assertEquals(100, supermarket.checkout("ABC"));
    assertEquals(240, supermarket.checkout("ABBACBBAB"));
  }

  /**
   * Verifies that an exception is thrown when the total cost is greater than
   * can be stored in an int.
   */
  @Test(expected = ArithmeticException.class)
  public void checkoutExceedsMaxInt() {
    ItemRepository itemRepository = mock(ItemRepository.class);
    when(itemRepository.findItem("A")).thenReturn(
        new Item("A", Integer.MAX_VALUE, costCalculators.getCostCalculator(CostCalculators.ID.FLAT)));

    final ItemCache itemCache = new ItemCache();
    itemCache.setItemRepository(itemRepository);

    final Supermarket supermarket = new Supermarket();
    supermarket.setItemCache(itemCache);

    supermarket.checkout("AA");
  }
}
