package com.dius.shopping.rules;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.dius.shopping.api.Product;

public class BulkDiscountPriceRuleTest extends AbstractPriceRuleTest {

  /**
   * the brand new Super iPad will have a bulk discounted applied,
   * where the price will drop to $499.99 each, if someone buys more than 4
   */
  @Override
  protected PriceRule createPriceRule() {
    return new BulkDiscountPriceRule("ipd", 4, new BigDecimal("499.99"));
  }

  @Test
  public void testJustMeetBulkDiscount() {
    Product ipd = productService.findProductBySku("ipd");
    
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    
    assertEquals(new BigDecimal("2499.95"), cart.getFinalPrice());
  }
  
  /**
   * should use the original price
   * 549.99 * 4
   */
  @Test
  public void testOneLessToMeetBulkDiscount() {
    Product ipd = productService.findProductBySku("ipd");
    
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    
    assertEquals(new BigDecimal("2199.96"), cart.getFinalPrice());
  }
  
  /**
   * after meet bulk discount condition, the price will always be quantity * discount price
   */
  @Test
  public void testManyMoreAfterMeetBulkDiscount() {
    Product ipd = productService.findProductBySku("ipd");
    
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    cart.addItem(ipd);
    
    assertEquals(new BigDecimal("3999.92"), cart.getFinalPrice());
  }
}
