package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.models.Category;
import com.huynhminhkhai.shopapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    // Inject CategoryRepository thông qua constructor
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        // Lưu category mới vào database
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        // Truy vấn tất cả các danh mục từ database
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found"));
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(category.getName());
        return existingCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
