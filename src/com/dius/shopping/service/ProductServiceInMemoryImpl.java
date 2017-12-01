package com.dius.shopping.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.dius.shopping.api.Product;

public class ProductServiceInMemoryImpl implements ProductService {
  private Map<String, Product> productData;

  public ProductServiceInMemoryImpl() {
    productData = new HashMap<>();
    productData.put("ipd", new Product("ipd", "Super iPad", new BigDecimal("549.99")));
    productData.put("mbp", new Product("mbp", "MacBook Pro", new BigDecimal("1399.99")));
    productData.put("atv", new Product("atv", "Apple TV", new BigDecimal("109.50")));
    productData.put("vga", new Product("vga", "VGA adapter", new BigDecimal("30.00")));
  }

  @Override
  public Product findProductBySku(String sku) {
    return productData.get(sku);
  }
}
