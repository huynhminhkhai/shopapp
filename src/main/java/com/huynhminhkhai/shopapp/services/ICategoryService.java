package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.CategoryDTO;
import com.huynhminhkhai.shopapp.models.Category;

import java.util.List;

public interface ICategoryService {
    //Thêm danh mục
    Category createCategory(CategoryDTO categoryDTO);
    // Lấy tất cả danh mục
    List<Category> getAllCategories();
    // Lấy danh mục theo ID
    Category getCategoryById(Long categoryId);
    // Cập nhật danh mục
    Category updateCategory(Long categoryId, CategoryDTO categoryDTO);
    // Xóa danh mục theo ID
    void deleteCategory(Long categoryId);
}
