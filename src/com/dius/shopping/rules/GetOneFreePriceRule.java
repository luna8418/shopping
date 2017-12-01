package com.dius.shopping.rules;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import com.dius.shopping.api.ShoppingCart;

/**
 * If you buy enough quantity product with specific sku, one of them would be free.
 * 
 * for ex: 3 for 2 deal on Apple TVs.
 * new GetOneFreePriceRule("atv", 3);
 * 
 * @author Eric
 *
 */
public class GetOneFreePriceRule implements PriceRule {
  private String sku;
  private int some;
  
  public GetOneFreePriceRule(String sku, int some) {
    assert sku != null : "sku cannot be null";
    assert some > 0 : "count must be bigger than 0";
    
    this.sku = sku;
    this.some = some;
  }

  @Override
  public void apply(ShoppingCart cart) {
    if (cart.isEmpty()) return;
    
    AtomicInteger ordinal = new AtomicInteger(0);
    cart.getItems()
    .stream()
    .filter(item -> sku.equals(item.getProduct().getSku()))
    .forEach(item -> {
      if (ordinal.incrementAndGet() % some == 0) {
        item.setFinalPrice(BigDecimal.ZERO);
      }
    });
  }

}
