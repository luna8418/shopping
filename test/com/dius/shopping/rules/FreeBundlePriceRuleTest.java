package com.dius.shopping.rules;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.dius.shopping.api.Product;

public class FreeBundlePriceRuleTest extends AbstractPriceRuleTest {

  /**
   * we will bundle in a free VGA adapter free of charge with every MacBook Pro sold
   */
  @Override
  protected PriceRule createPriceRule() {
    return new FreeBundlePriceRule("mbp", "vga");
  }

  @Test
  public void testFreeBundle() {
    Product mbp = productService.findProductBySku("mbp");
    Product vga = productService.findProductBySku("vga");
    
    cart.addItem(mbp);
    cart.addItem(vga);
    
    assertEquals(new BigDecimal("1399.99"), cart.getFinalPrice());
  }
  
  @Test
  public void testOneMoreTargetBundled() {
    Product mbp = productService.findProductBySku("mbp");
    Product vga = productService.findProductBySku("vga");
    
    cart.addItem(mbp);
    cart.addItem(vga);
    // need to pay one vga
    cart.addItem(vga);
    
    assertEquals(new BigDecimal("1429.99"), cart.getFinalPrice());
  }
  
  @Test
  public void testOneLessTargetBundled() {
    Product mbp = productService.findProductBySku("mbp");
    Product vga = productService.findProductBySku("vga");
    
    cart.addItem(mbp);
    cart.addItem(vga);
    
    cart.addItem(mbp);
    
    assertEquals(new BigDecimal("2799.98"), cart.getFinalPrice());
  }
  
}
