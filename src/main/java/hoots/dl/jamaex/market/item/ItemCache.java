package hoots.dl.jamaex.market.item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Cache of items retrieved from the {@link ItemRepository}
 * 
 * @author David Hoots
 * @since 0.1
 */
@Service
public class ItemCache {
  @Autowired
  private ItemRepository itemRepository;

  private final Map<String, Item> allItems = new ConcurrentHashMap<>();

  /**
   * Get an item from the cache.  If the item is not currently in the cache it
   * will be loaded from the {@link ItemRepository}
   * 
   * @param itemId ID of the item to 
   * @return
   */
  public Item getItem(final String itemId) {
    Item item = allItems.get(itemId);
    if (item == null) {
      item = itemRepository.findItem(itemId);
      allItems.put(itemId, item);
    }
    return item;
  }

  public final void setItemRepository(final ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  /**
   * Remove all items from the cache
   */
  public final void clear() {
    allItems.clear();
  }
}
