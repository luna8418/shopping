package com.dius.shopping.api;

import java.math.BigDecimal;

/**
 * Every single item in the shopping cart
 * 
 * @author Eric
 *
 */
public class OrderItem {
  // original product
  private Product product;
  // final price after price rule, will return product original price if no price rule applied
  private BigDecimal finalPrice;
  // quantity default to 1 and could be enhanced in future
  private double quantity = 1.0D;
  
  public OrderItem(Product product) {
    this(product, null);
  }
  
  public OrderItem(Product product, BigDecimal finalPrice) {
    this.product = product;
    this.finalPrice = finalPrice;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public BigDecimal getFinalPrice() {
    return finalPrice == null ? product.getPrice() : finalPrice;
  }

  public void setFinalPrice(BigDecimal finalPrice) {
    this.finalPrice = finalPrice;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }
}
