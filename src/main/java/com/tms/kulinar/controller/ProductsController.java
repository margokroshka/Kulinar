package com.tms.kulinar.controller;

import com.tms.kulinar.domain.Products;
import com.tms.kulinar.exception.CustomException;
import com.tms.kulinar.exception.ResourceNotFoundException;
import com.tms.kulinar.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable int id) {
        Products products = productsService.getProductById(id);
        return new ResponseEntity<>(products, products.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<Products>> getAllProducts() {
        if (productsService.getAllProducts().isEmpty()) {
            throw new ResourceNotFoundException("Not found any Products");
        }
        return new ResponseEntity<>(productsService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createProducts(@RequestBody @Valid Products products) {
        Products productsResult = productsService.createProduct(products);
        if (productsResult == null) {
            throw new CustomException("PRODUCTS_WAS_NOT_CREATED");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateProducts(@RequestBody @Valid Products products, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productsService.updateProduct(products);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteProducts(@RequestBody @Valid Products products, BindingResult bindingResult) {
        Products productsResult = productsService.deleteProduct(products);
        if (productsResult == null) {
            throw new CustomException("PRODUCTS_WAS_NOT_DELETED");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
