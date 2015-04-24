package hoots.dl.jamaex.market;

/**
 * Spring REST controller for managing the interaction with {@link Supermarket}s.
 * 
 * @author David Hoots
 * @since 0.1
 */

import hoots.dl.jamaex.market.item.ItemNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupermarketController {
  @Autowired
  private Supermarket supermarket;

  @RequestMapping(value = "/checkout", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<SupermarketCheckoutResponse> checkout(
      @RequestParam(value = "items") final String items) {

    try {
      final int totalCost = supermarket.checkout(items);
      return createResponse(new CheckoutResult(totalCost));
    } catch (ItemNotFoundException infe) {
      return createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid item: " + infe.getItemId());
    } catch (Throwable th) {
      th.printStackTrace(System.err);
      return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Exception handling request: " + th.getMessage());
    }
  }

  /**
   * Create a success response.
   * 
   * @param result The checkout result to wrap in an HTTP response.
   * @return A value suitable for including in an HTTP response.
   */
  private ResponseEntity<SupermarketCheckoutResponse> createResponse(final CheckoutResult result) {
    SupermarketCheckoutResponse transactionResponse = new SupermarketCheckoutResponse(200, "success",
        "Transaction successfully processed and returned.", result);

    return new ResponseEntity<SupermarketCheckoutResponse>(transactionResponse, HttpStatus.OK);
  }

  /**
   * Create an error response that includes error information.
   * 
   * @param httpStatus The HTTP status to include in the HTTP response
   * @param message An appropriate error message to include in the HTTP response
   * @return A value suitable for including in an HTTP response
   */
  private ResponseEntity<SupermarketCheckoutResponse> createErrorResponse(final HttpStatus httpStatus,
      final String message) {
    if (httpStatus.value() < 400) {
      throw new IllegalArgumentException("HttpStatus must be a failure or error");
    }

    final CheckoutResult failedResult = new CheckoutResult(-1);
    SupermarketCheckoutResponse transactionResponse;
    if (httpStatus.value() < 500) {
      transactionResponse = new SupermarketCheckoutResponse(httpStatus.value(), "error", message, failedResult);
    } else {
      transactionResponse = new SupermarketCheckoutResponse(httpStatus.value(), "fail", message, failedResult);
    }

    return new ResponseEntity<SupermarketCheckoutResponse>(transactionResponse, httpStatus);
  }

  public void setSupermarket(final Supermarket supermarket) {
    this.supermarket = supermarket;
  }
}
