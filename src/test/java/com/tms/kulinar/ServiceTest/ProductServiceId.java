package com.tms.kulinar.ServiceTest;

import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.domain.Products;
import com.tms.kulinar.repository.ProductsRepository;
import com.tms.kulinar.service.FeedbackService;
import com.tms.kulinar.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProductServiceId {

    @Mock
    private ProductsRepository productsRepository;

    private ProductsService productsService;

    private Products product;

    private List<Products> products;

    @BeforeEach
    void setProduct() {
        MockitoAnnotations.openMocks(this);
        productsService = new ProductsService(productsRepository);
        product = new Products(1,4,10,"Potato");
        products = new ArrayList<>();
        products.add(product);
        productsRepository.createProducts(product);
    }

    @Test
    void getFeedbackById() {
        when(productsRepository.getProductsById(product.getId())).thenReturn(product);
        Optional<Products> optionalArticle = Optional.ofNullable(productsService.getProductById(product.getId()));
        assertTrue(optionalArticle.isPresent());
        verify(productsRepository, times(1)).getProductsById(product.getId());
    }

    @Test
    void getAllFeedback() {
        when(productsRepository.getAllProducts()).thenReturn((ArrayList<Products>) products);
        Optional<List<Products>> optionalBooks = Optional.ofNullable(productsService.getAllProducts());
        assertTrue(optionalBooks.isPresent());
        verify(productsRepository, times(1)).getAllProducts();
    }

    @Test
    void createFeedback() {
        Products newProduct = new Products(1,4,10,"Potato");
        productsService.createProduct(newProduct);
        verify(productsRepository, times(2)).createProducts(ArgumentMatchers.any(Products.class));
    }
}
