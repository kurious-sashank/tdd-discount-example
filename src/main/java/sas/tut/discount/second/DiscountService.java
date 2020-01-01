package sas.tut.discount.second;

import sas.tut.discount.model.CustomerData;
import sas.tut.discount.model.ItemCart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountService {

  private ICustomerDataService customerDataService;

  private static final BigDecimal DISCOUNT_PERCENT =
      new BigDecimal(0.1).setScale(2, BigDecimal.ROUND_HALF_EVEN);

  private static final BigDecimal MONTHLY_PURCHASE_VALUE_THRESHOLD =
      new BigDecimal(100.00).setScale(2, BigDecimal.ROUND_HALF_EVEN);

  public void applyDiscount(ItemCart itemCart) {
    BigDecimal discountedPrice = calculateDiscountedPrice(itemCart);
    itemCart.setDiscountPercentage(DISCOUNT_PERCENT);
    itemCart.setPriceAfterDiscount(discountedPrice);
  }

  private BigDecimal calculateDiscountedPrice(ItemCart itemCart) {

    BigDecimal cartValue = getCartValue(itemCart);

    CustomerData customerData = customerDataService.findCustomerData(itemCart.getCustomerId());

    if (isAverageMonthlyPurchaseValueOverThreshold(customerData)) {
      cartValue = addDiscount(cartValue);
    }
    return cartValue;
  }

  private BigDecimal getCartValue(ItemCart itemCart) {
    return itemCart.getItems().stream()
        .map(item -> item.getPrice())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal addDiscount(BigDecimal cartValue) {
    return cartValue
        .subtract(cartValue.multiply(DISCOUNT_PERCENT))
        .setScale(2, BigDecimal.ROUND_HALF_EVEN);
  }

  private boolean isAverageMonthlyPurchaseValueOverThreshold(CustomerData customerData) {
    int totalMonths = customerData.getNumberMonthsSinceRegistration();
    BigDecimal totalPurchaseValue = customerData.getTotalPurchaseValue();
    BigDecimal avgPurchaseValue =
        totalPurchaseValue.divide(BigDecimal.valueOf(totalMonths), 2, RoundingMode.CEILING);
    return avgPurchaseValue.compareTo(MONTHLY_PURCHASE_VALUE_THRESHOLD) >= 0;
  }
}
