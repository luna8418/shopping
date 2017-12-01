package com.dius.shopping.service;

import com.dius.shopping.api.Product;

/**
 * Product related Services
 * 
 * @author Eric
 *
 */
public interface ProductService {

  Product findProductBySku(String sku);
}
