package com.huynhminhkhai.shopapp.controllers;

import com.huynhminhkhai.shopapp.dtos.CategoryDTO;
import com.huynhminhkhai.shopapp.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    @GetMapping("") // /id=
    public ResponseEntity<String> getAllProducts(
    ){
        return  ResponseEntity.ok("Successfully");
    }

    @PostMapping()
    public  ResponseEntity<?> createProducts(
            @Valid
            @ModelAttribute ProductDTO productDTO,
            BindingResult result ){
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return  ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(productDTO);
    }
}
