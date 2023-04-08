package com.tms.kulinar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tms.kulinar.controller.ProductsController;
import com.tms.kulinar.domain.Products;
import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    MockMvc mvc;

    @Mock
    ProductsRepository productsRepository;

    @InjectMocks
    ProductsController productsController;

    private final Products products = new Products();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(productsController).build();
        products.setProduct_name("Potato");
        products.setCount(5);
        products.setQuantity(3);
    }

    @Test
    void findAllProducts() throws Exception {
        ArrayList<Products> list = new ArrayList<>();
        list.add(new Products());
        when(productsRepository.getAllProducts()).thenReturn(list);
        MvcResult result = mvc.perform(get("/products/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(productsRepository, times(1)).getAllProducts();
    }

    @Test
    void findRecipeById() throws Exception {
        when(productsRepository.getProductsByProduct_name(anyString())).thenReturn(products);
        MvcResult result = mvc.perform(get("/products/{productName}", anyLong()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(productsRepository, times(1)).getProductsByProduct_name(anyString());
    }

    @Test
    void createRecipe() throws Exception {
        when(productsRepository.createProducts(any())).thenReturn(products);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new RegistrationUser());
        MvcResult result = mvc.perform(post("/products/create").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(productsRepository, times(1)).createProducts(any());
    }

    @Test
    void updateRecipe() throws Exception {
        when(productsRepository.updateProducts(any(Products.class))).thenReturn(products);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new User());
        MvcResult result = mvc.perform(put("/products/update/{id}").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(productsRepository, times(1)).updateProducts(any(Products.class));
    }

    @Test
    void deleteRecipeById() throws Exception {
        MvcResult result = mvc.perform(delete("/products/delete", anyLong()))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(productsRepository, times(1)).deleteProducts(any(Products.class));
    }

}
