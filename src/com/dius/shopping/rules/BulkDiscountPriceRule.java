package com.dius.shopping.rules;

import java.math.BigDecimal;

import com.dius.shopping.api.ShoppingCart;

/**
 * If you buy enough quantity product with specific sku, all of them would be dropped to a fixed
 * lower price.
 * 
 * for ex: The brand new Super iPad, where the price will drop to $499.99 each, if someone buys more
 * than 4 new BulkDiscountPriceRule("ipd", 4, new BigDecimal("499.99"));
 * 
 * @author Eric
 *
 */
public class BulkDiscountPriceRule implements PriceRule {

  private String sku;
  private int some;
  private BigDecimal fixedPrice;

  public BulkDiscountPriceRule(String sku, int some, BigDecimal fixedPrice) {
    assert sku != null : "sku cannot be null";
    assert some > 0 : "count must be bigger than 0";
    assert fixedPrice != null && fixedPrice.doubleValue() > 0 : "invalid price";

    this.sku = sku;
    this.some = some;
    this.fixedPrice = fixedPrice;
  }

  @Override
  public void apply(ShoppingCart cart) {
    if (!meetConditions(cart))
      return;

    cart.getItems()
      .stream()
      .filter(item -> sku.equals(item.getProduct().getSku()))
      .forEach(item -> item.setFinalPrice(fixedPrice));
  }

  private boolean meetConditions(ShoppingCart cart) {
    if (cart.isEmpty())
      return false;

    return cart.getItems()
        .stream()
        .filter(item -> sku.equals(item.getProduct().getSku()))
        .count() > some;
  }
}
