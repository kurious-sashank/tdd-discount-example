package sas.tut.discount.first;

import sas.tut.discount.model.ItemCart;

import java.math.BigDecimal;

public class DiscountService {

  private static final BigDecimal DISCOUNT_PERCENT =
      new BigDecimal(0.05).setScale(2, BigDecimal.ROUND_HALF_EVEN);

  private static final BigDecimal PURCHASE_VALUE_THRESHOLD =
      new BigDecimal(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN);

  public void applyDiscount(ItemCart itemCart) {
    BigDecimal discountedPrice = calculateDiscountedPrice(itemCart);
    itemCart.setDiscountPercentage(DISCOUNT_PERCENT);
    itemCart.setPriceAfterDiscount(discountedPrice);
  }

  private BigDecimal calculateDiscountedPrice(ItemCart itemCart) {

    BigDecimal cartValue =
        itemCart.getItems().stream()
            .map(item -> item.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    if (isCartValueOverThreshold(cartValue)) {
      cartValue = addDiscount(cartValue);
    }
    return cartValue;
  }

  private BigDecimal addDiscount(BigDecimal cartValue) {
    return cartValue
        .subtract(cartValue.multiply(DISCOUNT_PERCENT))
        .setScale(2, BigDecimal.ROUND_HALF_EVEN);
  }

  private boolean isCartValueOverThreshold(BigDecimal cartValue) {
    return cartValue.compareTo(PURCHASE_VALUE_THRESHOLD) >= 0;
  }
}
