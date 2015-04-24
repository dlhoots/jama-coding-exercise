package hoots.dl.jamaex.market.item;

/**
 * Exception this is thrown whenever an item cannot be found in the
 * repository.
 * 
 * @author David Hoots
 * @since 0.1
 */
public class ItemNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -2502670075823773989L;

  private final String itemId;

  public ItemNotFoundException(final String itemId) {
    this.itemId = itemId;
  }

  public String getItemId() {
    return itemId;
  }
}
