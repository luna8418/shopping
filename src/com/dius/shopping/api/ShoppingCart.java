package com.dius.shopping.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.dius.shopping.rules.PriceRule;

/**
 * Shopping Cart
 * Before initializing a new shopping cart, it need set up all the price rules, 
 * so it will apply all the rules to items in the cart and get the final total price
 * 
 * @author Eric
 *
 */
public class ShoppingCart {
  private List<OrderItem> items;
  private List<PriceRule> rules;
  
  public ShoppingCart(List<PriceRule> rules) {
    this.rules = rules;
    this.items = new ArrayList<>();
  }
  
  public void addItem(Product product) {
    items.add(new OrderItem(product));
  }
  
  public List<OrderItem> getItems() {
    return this.items;
  }
  
  public void applyPriceRules() {
    if (rules == null || rules.isEmpty()) return;
    
    rules.stream().forEach(rule -> rule.apply(this));
  }
  
  public BigDecimal getFinalPrice() {
    if (isEmpty()) {
      return BigDecimal.ZERO;
    }
    
    this.applyPriceRules();
    return items.stream().map(item -> item.getFinalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
  
  public boolean isEmpty() {
    return items == null || items.isEmpty();
  }
}
