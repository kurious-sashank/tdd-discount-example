package sas.tut.discount.first;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import sas.tut.discount.model.CheckoutItem;
import sas.tut.discount.model.ItemCart;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

  @InjectMocks private DiscountService discountService;

  @Test
  public void applyDiscount_whenPurchaseValueOverThreshold_thenApplyDiscount() {
    // arrange
    ItemCart itemCart = new ItemCart();
    CheckoutItem item1 = buildItem(1L, new BigDecimal(180));
    CheckoutItem item2 = buildItem(2L, new BigDecimal(100));

    List<CheckoutItem> items = Arrays.asList(item1, item2);
    itemCart.setItems(items);

    BigDecimal expectedPrice = new BigDecimal("266");
    // act
    discountService.applyDiscount(itemCart);
    // assert
    Assert.assertTrue(expectedPrice.compareTo(itemCart.getPriceAfterDiscount()) == 0);
  }

  @Test
  public void applyDiscount_whenItemCountAndValueUnderThreshold_thenDontApplyDiscount() {
    // arrange
    ItemCart itemCart = new ItemCart();
    CheckoutItem item1 = buildItem(1L, new BigDecimal(80));
    CheckoutItem item2 = buildItem(2L, new BigDecimal(10));

    List<CheckoutItem> items = Arrays.asList(item1, item2);
    itemCart.setItems(items);

    BigDecimal expectedPrice = new BigDecimal("90");
    // act
    discountService.applyDiscount(itemCart);
    // assert
    Assert.assertTrue(expectedPrice.compareTo(itemCart.getPriceAfterDiscount()) == 0);
  }

  private CheckoutItem buildItem(Long id, BigDecimal price) {
    CheckoutItem item = new CheckoutItem();
    item.setId(id);
    item.setPrice(price);
    return item;
  }
}
