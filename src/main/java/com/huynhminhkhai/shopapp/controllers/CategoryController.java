package com.huynhminhkhai.shopapp.controllers;

import com.huynhminhkhai.shopapp.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<String> getAllCategories(){
        return ResponseEntity.ok("successfully");
    }

    @PostMapping()
    public  ResponseEntity<?> createCategories(
            @Valid
            @RequestBody CategoryDTO categoryDTO,
            BindingResult result ){
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return  ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(categoryDTO);
    }
}
