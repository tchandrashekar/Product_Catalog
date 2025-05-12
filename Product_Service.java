
package com.example.product_catalog.Service;

import com.example.product_catalog.Model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.example.product_catalog.Repository.Product_Repository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Product_Service {
    
    @Autowired
    private Product_Repository repo;
    
    
    public Page<Product> getAllProducts(String category,String name,int page,int size,String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        if (category != null && !category.isEmpty()) {
            return repo.findByCategoryContainingIgnoreCase(category, pageable);
        }

        if (name != null && !name.isEmpty()) {
            return repo.findByNameContainingIgnoreCase(name, pageable);
        }

        return repo.findAll(pageable);
    }

   public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    public Product createProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return repo.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            return repo.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
    
    
}

