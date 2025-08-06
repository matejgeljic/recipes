package com.matejgeljic.recipes.recipe;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeMapper recipeMapper;
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<CreateRecipeResponseDto> createRecipe(
            @Valid @RequestBody CreateRecipeRequestDto createRecipeRequestDto) {

        CreateRecipeRequest createRecipeRequest = recipeMapper.fromDto(createRecipeRequestDto);
        Recipe createdRecipe = recipeService.createRecipe(createRecipeRequest);
        CreateRecipeResponseDto createdRecipeResponseDto = recipeMapper.toDto(createdRecipe);

        return new ResponseEntity<>(createdRecipeResponseDto, HttpStatus.CREATED);
    }
}
