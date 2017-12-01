package com.dius.shopping.rules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.dius.shopping.api.ShoppingCart;
import com.dius.shopping.service.ProductService;
import com.dius.shopping.service.ProductServiceInMemoryImpl;

public abstract class AbstractPriceRuleTest {
  protected ShoppingCart cart;
  protected ProductService productService;

  @Before
  public void setupRules() {
    List<PriceRule> rules = new ArrayList<>();
    rules.add(createPriceRule());
    cart = new ShoppingCart(rules);
    
    productService = new ProductServiceInMemoryImpl();
  }

  protected abstract PriceRule createPriceRule();
}
