package com.tms.kulinar.controller;

import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.domain.Products;
import com.tms.kulinar.repository.ProductsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Operation(description = "Ищет продукт по name", summary = "Ищет продукт")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Все ОК! Улыбаемся и машем"),
            @ApiResponse(responseCode = "404", description = "Куда ты жмал?!!? Ничего не нашел")
    })
    @GetMapping("/{productName}")
    @Tag(name = "byproductName", description = "ищём по productName")
    public ResponseEntity<Products> getProductsByProduct_name( @PathVariable String productName)  {
        Products products = productsRepository.getProductsByProduct_name(productName);
        return  new ResponseEntity<>(products,  HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Products>> getAllProducts() {
        return new ResponseEntity<>(productsRepository.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProducts(@RequestBody @Valid Products products) {
        productsRepository.createProducts(products);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateProducts(@RequestBody @Valid Products products, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productsRepository.updateProducts(products);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProducts(@RequestBody @Valid Products products, BindingResult bindingResult) {
        productsRepository.deleteProducts(products);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
