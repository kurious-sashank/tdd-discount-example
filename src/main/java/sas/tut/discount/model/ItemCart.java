package sas.tut.discount.model;

import java.math.BigDecimal;
import java.util.List;

public class ItemCart {

  private Long customerId;
  private List<CheckoutItem> items;
  private BigDecimal discountPercentage;
  private BigDecimal priceAfterDiscount;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public List<CheckoutItem> getItems() {
    return items;
  }

  public void setItems(List<CheckoutItem> items) {
    this.items = items;
  }

  public BigDecimal getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(BigDecimal discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

  public BigDecimal getPriceAfterDiscount() {
    return priceAfterDiscount;
  }

  public void setPriceAfterDiscount(BigDecimal priceAfterDiscount) {
    this.priceAfterDiscount = priceAfterDiscount;
  }
}
