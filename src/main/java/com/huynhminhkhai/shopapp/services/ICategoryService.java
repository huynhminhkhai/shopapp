package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.models.Category;

import java.util.List;

public interface ICategoryService {

    Category createCategory(Category category);
    // Lấy tất cả danh mục
    List<Category> getAllCategories();

    // Lấy danh mục theo ID
    Category getCategoryById(Long id);

    // Thêm hoặc cập nhật danh mục
    Category updateCategory(Long categoryId, Category category);

    // Xóa danh mục theo ID
    void deleteCategory(Long id);
}
