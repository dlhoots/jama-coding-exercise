package hoots.dl.jamaex.market;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hoots.dl.jamaex.market.item.ItemNotFoundException;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SupermarketControllerTests {

  /**
   * Basic checkout case where everything works as expected.
   */
  @Test
  public void checkout() {
    Supermarket supermarket = mock(Supermarket.class);
    when(supermarket.checkout(anyString())).thenReturn(137);

    SupermarketController controller = new SupermarketController();
    controller.setSupermarket(supermarket);

    ResponseEntity<SupermarketCheckoutResponse> responseEntity = controller.checkout("ABCABC");
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(200, responseEntity.getBody().getCode());
    assertEquals("success", responseEntity.getBody().getStatus());
    assertEquals(137, responseEntity.getBody().getCheckoutResult().getTotalCost());
  }

  /**
   * Checkout case where the item list contains an unknown item ID.
   */
  @Test
  public void checkoutInvalidItem() {
    Supermarket supermarket = mock(Supermarket.class);
    when(supermarket.checkout(anyString())).thenThrow(new ItemNotFoundException("A"));

    SupermarketController controller = new SupermarketController();
    controller.setSupermarket(supermarket);

    ResponseEntity<SupermarketCheckoutResponse> responseEntity = controller.checkout("ABCABC");
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getCode());
    assertEquals("Invalid item: A", responseEntity.getBody().getMessage());
    assertEquals("error", responseEntity.getBody().getStatus());
  }
}
