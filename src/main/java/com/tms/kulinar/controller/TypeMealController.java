package com.tms.kulinar.controller;

import com.tms.kulinar.domain.TypeMeal;
import com.tms.kulinar.exception.CustomException;
import com.tms.kulinar.repository.TypeMealRepository;
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
@RequestMapping("/type")
public class TypeMealController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final TypeMealRepository mealRepository;

    @Autowired
    public TypeMealController(TypeMealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Operation(description = "Ищет тип блюда по ID", summary = "Ищет тип блюда")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Все ОК! Улыбаемся и машем"),
            @ApiResponse(responseCode = "404", description = "Куда ты жмал?!!? Ничего не нашел")
    })

    @GetMapping("/{id}")
    @Tag(name = "byID", description = "ищём по id")
    public ResponseEntity<TypeMeal> getTypeMealById(@PathVariable int id)  {
        TypeMeal typeMeal = mealRepository.getTypeMealById(id);
        return  new ResponseEntity<>(typeMeal,  HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<TypeMeal>> getAllTypeMeal() {
        return new ResponseEntity<>(mealRepository.getAllTypeMeal(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createTypeMeal(@RequestBody @Valid TypeMeal typeMeal) {
        TypeMeal mealResult = mealRepository.createTypeMeal(typeMeal);
        if (mealResult == null) {
            throw new CustomException("Type_WAS_NOT_CREATED");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateTypeMeal(@RequestBody @Valid TypeMeal typeMeal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        mealRepository.updateTypeMeal(typeMeal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTypeMeal(@RequestBody @Valid TypeMeal typeMeal, BindingResult bindingResult) {
        mealRepository.deleteTypeMeal(typeMeal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
