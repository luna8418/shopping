package com.dius.shopping;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import com.dius.shopping.api.ShoppingCart;
import com.dius.shopping.rules.PriceRule;
import com.dius.shopping.service.ProductService;
import com.dius.shopping.service.ProductServiceInMemoryImpl;

public class Checkout {
  private ShoppingCart cart;
  private ProductService productService;
  
  public Checkout(List<PriceRule> rules) {
    this.cart = new ShoppingCart(rules);
    this.productService = new ProductServiceInMemoryImpl();
  }
  
  public void scan(String sku) {
    this.cart.addItem(productService.findProductBySku(sku));
  }
  
  public BigDecimal total() {
    return cart.getFinalPrice();
  }
  
  public String getTotalPriceInString() {
    return "Total expected: " + NumberFormat.getCurrencyInstance().format(cart.getFinalPrice());
  }
}
