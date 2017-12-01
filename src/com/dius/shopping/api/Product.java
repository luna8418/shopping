package com.dius.shopping.api;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The original product object that we are actually selling
 * The data should be fetched from database and read only while using
 * 
 * @author Eric
 *
 */
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  private String sku;
  private String name;
  private BigDecimal price;

  public Product(String sku, String name, BigDecimal price) {
    this.sku = sku;
    this.name = name;
    this.price = price;
  }

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
