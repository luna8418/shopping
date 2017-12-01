package com.dius.shopping.rules;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import com.dius.shopping.api.ShoppingCart;

/**
 * If you buy one product with specific sku, you will get another product with specific sku for free .
 * 
 * for ex: We will bundle in a free VGA adapter free of charge with every MacBook Pro sold
 * new FreeBundlePriceRule("mbp", "vga");
 * 
 * @author Eric
 *
 */
public class FreeBundlePriceRule implements PriceRule {
  private String sku;
  private String targetSku;
  
  public FreeBundlePriceRule(String sku, String targetSku) {
    assert sku != null : "sku cannot be null";
    assert targetSku != null : "target sku cannot be null";
    
    this.sku = sku;
    this.targetSku = targetSku;
  }

  @Override
  public void apply(ShoppingCart cart) {
    if (cart.isEmpty()) return;
    
    int count = (int) cart.getItems()
        .stream()
        .filter(item -> sku.equals(item.getProduct().getSku()))
        .count();
    
    if (count == 0) return;
    
    AtomicInteger ordinal = new AtomicInteger(count);
    cart.getItems()
      .stream()
      .filter(item -> targetSku.equals(item.getProduct().getSku()))
      .forEach(item -> {
        if (ordinal.getAndDecrement() > 0) {
          item.setFinalPrice(BigDecimal.ZERO);
        }
      });
  }

}
