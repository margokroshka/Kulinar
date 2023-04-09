package com.tms.kulinar.controller;

import com.tms.kulinar.domain.Recipe;
import com.tms.kulinar.exception.CustomException;
import com.tms.kulinar.repository.RecipeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/recipe")
public class RecipeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Operation(description = "Ищет юзера по ID", summary = "Ищет юзера")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Все ОК! Улыбаемся и машем"),
            @ApiResponse(responseCode = "404", description = "Куда ты жмал?!!? Ничего не нашел")
    })

    @GetMapping("/{id}")
    @Tag(name = "byID", description = "ищём по id")
    public ResponseEntity<Recipe> getRecipesById(@PathVariable int id)  {
        Recipe recipe = recipeRepository.getRecipeById(id);
        return  new ResponseEntity<>(recipe,  HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeRepository.getAllRecipe(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createRecipe(@RequestBody @Valid Recipe recipe) {
        Recipe resultRecipe = recipeRepository.createRecipe(recipe);
        if (resultRecipe == null) {
            throw new CustomException("RECIPE_WAS_NOT_CREATED");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateRecipe(@RequestBody @Valid Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        recipeRepository.updateRecipe(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteRecipe(@RequestBody @Valid Recipe recipe, BindingResult bindingResult) {
        Recipe resultRecipe = recipeRepository.deleteProducts(recipe);
        if (resultRecipe == null) {
            throw new CustomException("RECIPE_WAS_NOT_DELETED");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
