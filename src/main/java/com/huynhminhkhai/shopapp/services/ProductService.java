package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.ProductDTO;
import com.huynhminhkhai.shopapp.models.Category;
import com.huynhminhkhai.shopapp.models.Product;
import com.huynhminhkhai.shopapp.repositories.CategoryRepository;
import com.huynhminhkhai.shopapp.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        // Kiểm tra xem category có tồn tại không
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException ("Cannot find category with id: " + productDTO.getCategoryId()));

        // Tạo sản phẩm mới từ DTO
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .imageUrl(productDTO.getImageUrl())
                .category(category)
                .build();

        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setImageUrl(productDTO.getImageUrl());

        // Kiểm tra xem category có tồn tại không và cập nhật nếu có
        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Cannot find category with id: " + productDTO.getCategoryId()));
            existingProduct.setCategory(category);
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        // Lấy danh sách sản phẩm theo categoryId
        return productRepository.findByCategoryId(categoryId);
    }
}
