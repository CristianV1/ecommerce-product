package com.linkticExam.ecommerce.controllers;

import com.linkticExam.ecommerce.models.Product;
import com.linkticExam.ecommerce.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Get product by ID", description = "Returns a product by its ID")
    @ApiResponse(responseCode = "200", description = "Product found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Create a new product", description = "Creates a new product in the system")
    @ApiResponse(responseCode = "201", description = "Product created")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @Operation(summary = "Update product by ID", description = "Updates the details of an existing product by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product details provided")
    })
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @Operation(summary = "Delete product by ID", description = "Deletes a product by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
