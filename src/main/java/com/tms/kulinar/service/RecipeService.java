package com.tms.kulinar.service;

import com.tms.kulinar.domain.Recipe;
import com.tms.kulinar.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecipeService {
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipeById(int id) {
        return recipeRepository.getRecipeById(id);
    }

    public ArrayList<Recipe> getAllRecipes() {
        return (ArrayList<Recipe>) recipeRepository.getAllRecipe();
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.createRecipe(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.updateRecipe(recipe);
    }

    public Recipe deleteRecipe(Recipe recipe) {
        recipeRepository.deleteRecipe(recipe);
        return recipe;
    }
}
