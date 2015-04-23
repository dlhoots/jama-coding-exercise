package hoots.dl.jamaex.market.item;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.Test;

public class ItemCacheTests {

  /**
   * Verifies that the cache correctly retrieves items from the repository.
   */
  @Test
  public void fetchFromRepository() {
    ItemRepository itemRepository = mock(ItemRepository.class);
    when(itemRepository.findItem(anyString())).thenReturn(new Item(null, 0, null));

    final ItemCache itemCache = new ItemCache();
    itemCache.setItemRepository(itemRepository);
    itemCache.getItem("A");
    itemCache.getItem("A");
    itemCache.getItem("A");
    itemCache.getItem("B");
    itemCache.getItem("C");

    verify(itemRepository, times(1)).findItem("A");
    verify(itemRepository, times(1)).findItem("B");
    verify(itemRepository, times(1)).findItem("C");
    verify(itemRepository, times(3)).findItem(anyString());
  }

  /**
   * Verifies that the cache puts the items into the cache correctly.
   */
  @Test
  public void storeInCache() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
      IllegalAccessException {
    ItemRepository itemRepository = mock(ItemRepository.class);
    when(itemRepository.findItem(anyString())).thenReturn(new Item(null, 0, null));

    final ItemCache itemCache = new ItemCache();
    itemCache.setItemRepository(itemRepository);
    itemCache.getItem("A");
    itemCache.getItem("A");
    itemCache.getItem("A");
    itemCache.getItem("B");
    itemCache.getItem("C");

    // Get a handle to the map that stores items
    Field allItemsField = itemCache.getClass().getDeclaredField("allItems");
    allItemsField.setAccessible(true);
    @SuppressWarnings("unchecked")
    Map<String, String> allItems = (Map<String, String>) allItemsField.get(itemCache);

    assertEquals(3, allItems.size());

    itemCache.clear();
    assertEquals(0, allItems.size());
  }
}
