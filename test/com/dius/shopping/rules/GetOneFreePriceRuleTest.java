package com.dius.shopping.rules;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.dius.shopping.api.Product;

public class GetOneFreePriceRuleTest extends AbstractPriceRuleTest {

  /**
   * buy 3 Apple TVs, you will pay the price of 2 only 
   */
  @Override
  protected PriceRule createPriceRule() {
    return new GetOneFreePriceRule("atv", 3);
  }

  @Test
  public void testGetOneFree() {
    Product atv = productService.findProductBySku("atv");
    
    cart.addItem(atv);
    cart.addItem(atv);
    cart.addItem(atv);
    
    assertEquals(new BigDecimal("219.00"), cart.getFinalPrice());
  }
  
  @Test
  public void testGetMultipleFree() {
    Product atv = productService.findProductBySku("atv");
    
    cart.addItem(atv);
    cart.addItem(atv);
    cart.addItem(atv);
    
    cart.addItem(atv);
    cart.addItem(atv);
    cart.addItem(atv);
    
    cart.addItem(atv);
    cart.addItem(atv);
    cart.addItem(atv);
    
    assertEquals(new BigDecimal("657.00"), cart.getFinalPrice());
  }
  
  @Test
  public void testOneLessPurchasedSenario() {
    Product atv = productService.findProductBySku("atv");
    
    cart.addItem(atv);
    cart.addItem(atv);
    
    assertEquals(new BigDecimal("219.00"), cart.getFinalPrice());
  }
  
  @Test
  public void testOneMorePurchasedSenario() {
    Product atv = productService.findProductBySku("atv");
    
    cart.addItem(atv);
    cart.addItem(atv);
    cart.addItem(atv);
    cart.addItem(atv);
    
    assertEquals(new BigDecimal("328.50"), cart.getFinalPrice());
  }
}
