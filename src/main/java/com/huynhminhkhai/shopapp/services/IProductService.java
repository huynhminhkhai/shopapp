package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.ProductDTO;
import com.huynhminhkhai.shopapp.models.Product;

import java.util.List;

public interface IProductService {
    //Thêm product
    Product createProduct(ProductDTO productDTO);
    // Lấy tất cả product
    List<Product> getAllProducts();
    //Lấy product theo id
    Product getProductById(Long productId);
    //Update product
    Product updateProduct(Long productId, ProductDTO productDTO);
    //Delete product
    void deleteProduct(Long productId);
    //Lấy product theo category
    List<Product> getProductsByCategoryId(Long categoryId);

}
