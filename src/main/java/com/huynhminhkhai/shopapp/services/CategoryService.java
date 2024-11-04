package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.CategoryDTO;
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
    public Category createCategory(CategoryDTO categoryDTO) {
        // Lưu category mới vào database
        Category newCategory = Category.builder()
                .name(categoryDTO.getName())
                .imageUrl(categoryDTO.getImageUrl()).build();
        return categoryRepository.save(newCategory);
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
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        // Cập nhật tên mới
        existingCategory.setName(categoryDTO.getName());
        // Nếu imageUrl mới không phải là null, cập nhật imageUrl; ngược lại, giữ nguyên giá trị cũ
        if (categoryDTO.getImageUrl() != null) {
            existingCategory.setImageUrl(categoryDTO.getImageUrl());
        }
        // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {

        categoryRepository.deleteById(categoryId);
    }
}
