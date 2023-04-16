package com.tms.kulinar.service;

import com.tms.kulinar.domain.Products;
import com.tms.kulinar.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductsService {
    ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products getProductById(int id) {
        return productsRepository.getProductsById(id);
    }

    public ArrayList<Products> getAllProducts() {
        return productsRepository.getAllProducts();
    }

    public Products createProduct(Products products) {
        return productsRepository.createProducts(products);
    }

    public Products updateProduct(Products products) {
        return productsRepository.updateProducts(products);
    }

    public Products deleteProduct(Products products) {
        productsRepository.deleteProducts(products);
        return products;
    }
}
