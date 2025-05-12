/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.product_catalog.Controller;

import com.example.product_catalog.Model.Product;
import com.example.product_catalog.Service.Product_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")

public class Product_Controller {
    
    @Autowired
    private Product_Service ser;
    
    @GetMapping
    public String getAll(@RequestParam(defaultValue="") String category,
                         @RequestParam(defaultValue="") String name,
                         @RequestParam(defaultValue="0")int page,
                         @RequestParam(defaultValue="10")int size,
                         @RequestParam(defaultValue="pid") String sortBy,
                         Model model){
        Page<Product> productPage = ser.getAllProducts(category, name, page, size, sortBy);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "products";
        
    }
    
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {
        ser.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = ser.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        ser.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        ser.deleteProduct(id);
        return "redirect:/products";
    }
    
    
    
}
