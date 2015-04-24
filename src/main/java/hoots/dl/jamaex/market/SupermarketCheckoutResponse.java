package hoots.dl.jamaex.market;

/**
 * Wraps a {@link CheckoutResult} to include the status/result of submitting
 * items for checkout to {@link Supermarket}.
 * 
 * @author David Hoots
 * @since 0.1
 */

public class SupermarketCheckoutResponse {
  private final int code;
  private final String status;
  private final String message;
  private final CheckoutResult checkoutResult;

  public SupermarketCheckoutResponse(int code, String status, String message, CheckoutResult checkoutResult) {
    super();
    this.code = code;
    this.status = status;
    this.message = message;
    this.checkoutResult = checkoutResult;
  }

  @Override
  public String toString() {
    return "ProductResponse [" + "code=" + code + ", status=" + status + ", message=" + message + ", checkoutResult="
        + checkoutResult + "]";
  }

  public int getCode() {
    return code;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public CheckoutResult getCheckoutResult() {
    return checkoutResult;
  }
}
