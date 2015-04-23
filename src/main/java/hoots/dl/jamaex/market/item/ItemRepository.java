package hoots.dl.jamaex.market.item;

import org.springframework.stereotype.Repository;

/**
 * Repository which holds {@link Item}s
 * 
 * @author David Hoots
 * @since 0.1
 */
@Repository
public interface ItemRepository {

  /**
   * Find an item in the repository.
   * 
   * @param id
   *          ID of the item to find
   * @return The item whose ID matches the one provided
   */
  Item findItem(String id);
}
