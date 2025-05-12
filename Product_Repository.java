/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.product_catalog.Repository;

import com.example.product_catalog.Model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author tanne
 */

@Repository
public interface Product_Repository extends JpaRepository<Product,Long>{
    
    Page<Product> findByCategoryContainingIgnoreCase(String category,Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
