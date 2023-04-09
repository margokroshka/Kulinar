package com.tms.kulinar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tms.kulinar.controller.RecipeController;
import com.tms.kulinar.domain.Recipe;
import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.repository.RecipeRepository;
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
import static org.mockito.Matchers.anyInt;
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
public class RecipeControllerTest {
    MockMvc mvc;

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeController recipeController;

    private final Recipe recipe = new Recipe();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        recipe.setTaste(10);
        recipe.setTime("15min");
        recipe.setAmount("200g");
        recipe.setComplexity(5);
        recipe.setText("it is text recipe");
        recipe.setType_meal("main_meal");

    }

    @Test
    void findAllRecipe() throws Exception {
        ArrayList<Recipe> list = new ArrayList<>();
        list.add(new Recipe());
        when(recipeRepository.getAllRecipe()).thenReturn(list);
        MvcResult result = mvc.perform(get("/recipe/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(recipeRepository, times(1)).getAllRecipe();
    }

    @Test
    void findRecipeById() throws Exception {
        when(recipeRepository.getRecipeById(anyInt())).thenReturn(recipe);
        MvcResult result = mvc.perform(get("/recipe/{id}", anyLong()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(recipeRepository, times(1)).getRecipeById(anyInt());
    }

    @Test
    void createRecipe() throws Exception {
        when(recipeRepository.createRecipe(any())).thenReturn(recipe);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new RegistrationUser());
        MvcResult result = mvc.perform(post("/recipe/create").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(recipeRepository, times(1)).createRecipe(any());
    }

    @Test
    void updateRecipe() throws Exception {
        when(recipeRepository.updateRecipe(any(Recipe.class))).thenReturn(recipe);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new User());
        MvcResult result = mvc.perform(put("/recipe/update/{id}").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(recipeRepository, times(1)).updateRecipe(any(Recipe.class));
    }

    @Test
    void deleteRecipeById() throws Exception {
        MvcResult result = mvc.perform(delete("/recipe/delete", anyLong()))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(recipeRepository, times(1)).deleteProducts(any(Recipe.class));
    }
}
