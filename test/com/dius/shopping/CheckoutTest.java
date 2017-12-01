package com.dius.shopping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dius.shopping.Checkout;
import com.dius.shopping.rules.BulkDiscountPriceRule;
import com.dius.shopping.rules.FreeBundlePriceRule;
import com.dius.shopping.rules.GetOneFreePriceRule;
import com.dius.shopping.rules.PriceRule;

public class CheckoutTest {
  private List<PriceRule> rules;

  @Before
  public void setupRules() {
    rules = new ArrayList<>();
    rules.add(new GetOneFreePriceRule("atv", 3));
    rules.add(new BulkDiscountPriceRule("ipd", 4, new BigDecimal("499.99")));
    rules.add(new FreeBundlePriceRule("mbp", "vga"));
  }

  @Test
  public void testSenarioOne() {
    Checkout co = new Checkout(rules);
    co.scan("atv");
    co.scan("atv");
    co.scan("atv");
    co.scan("vga");
    assertEquals(new BigDecimal("249.00"), co.total());
  }
  
  @Test
  public void testSenarioTwo() {
    Checkout co = new Checkout(rules);
    co.scan("atv");
    co.scan("ipd");
    co.scan("ipd");
    co.scan("atv");
    co.scan("ipd");
    co.scan("ipd");
    co.scan("ipd");
    assertEquals(new BigDecimal("2718.95"), co.total());
  }
  
  @Test
  public void testSenarioThree() {
    Checkout co = new Checkout(rules);
    co.scan("mbp");
    co.scan("vga");
    co.scan("ipd");
    assertEquals(new BigDecimal("1949.98"), co.total());
  }
}
