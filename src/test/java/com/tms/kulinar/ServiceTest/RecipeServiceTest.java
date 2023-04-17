package com.tms.kulinar.ServiceTest;

import com.tms.kulinar.domain.Recipe;
import com.tms.kulinar.repository.RecipeRepository;
import com.tms.kulinar.service.RecipeService;
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

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeService recipeService;

    private Recipe recipe;

    private List<Recipe> recipes;

    @BeforeEach
    void setRecipe() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeService(recipeRepository);
        recipe = new Recipe(1, 10, "15min","300gramm",7,"Recipe","main meal",1,1);
        recipes = new ArrayList<>();
        recipes.add(recipe);
        recipeRepository.createRecipe(recipe);
    }

    @Test
    void getAllRecipe() {
        when(recipeRepository.getAllRecipe()).thenReturn((ArrayList<Recipe>) recipes);
        Optional<List<Recipe>> optionalBooks = Optional.ofNullable(recipeService.getAllRecipes());
        assertTrue(optionalBooks.isPresent());
        verify(recipeRepository, times(1)).getAllRecipe();
    }

    @Test
    void createFeedback() {
        Recipe newRecipe = new Recipe(1, 10, "15min","300gramm",7,"Recipe","main meal",1,1);
        recipeService.createRecipe(newRecipe);
        verify(recipeRepository, times(2)).createRecipe(ArgumentMatchers.any(Recipe.class));
    }
}
