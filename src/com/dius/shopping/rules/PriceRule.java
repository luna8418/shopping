package com.dius.shopping.rules;

import com.dius.shopping.api.ShoppingCart;

/**
 * Price Rule interface
 * It could apply to the whole shopping cart with different implementations
 * 
 * @author Eric
 *
 */
public interface PriceRule {
  
  /**
   * apply the specific price rule to the whole shopping cart
   * 
   * @param cart
   */
  void apply(ShoppingCart cart);
}
