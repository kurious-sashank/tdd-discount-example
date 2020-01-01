package sas.tut.discount.second;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import sas.tut.discount.model.CheckoutItem;
import sas.tut.discount.model.CustomerData;
import sas.tut.discount.model.ItemCart;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

  @Mock private ICustomerDataService customerDataService;
  @InjectMocks private DiscountService discountService;

  @Test
  public void applyDiscount_whenAverageMonthlyPurchaseValueOverThreshold_thenApplyDiscount() {
    // arrange
    CustomerData customerData = new CustomerData();
    customerData.setId(1L);
    customerData.setNumberMonthsSinceRegistration(10);
    customerData.setTotalPurchaseValue(new BigDecimal(1200.00));

    ItemCart itemCart = new ItemCart();
    CheckoutItem item1 = buildItem(1L, new BigDecimal(180));
    CheckoutItem item2 = buildItem(2L, new BigDecimal(100));

    List<CheckoutItem> items = Arrays.asList(item1, item2);
    itemCart.setItems(items);

    Mockito.when(customerDataService.findCustomerData(itemCart.getCustomerId()))
        .thenReturn(customerData);

    BigDecimal expectedPrice = new BigDecimal("252");
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
