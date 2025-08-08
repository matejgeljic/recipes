package com.matejgeljic.recipes.recipe;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeMapper recipeMapper;
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<CreateRecipeResponseDto> createRecipe(
            @Valid @RequestBody CreateRecipeRequestDto createRecipeRequestDto) {

        //TODO replace next line with real userID
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        CreateRecipeRequest createRecipeRequest = recipeMapper.fromDto(createRecipeRequestDto);
        Recipe createdRecipe = recipeService.createRecipe(userId, createRecipeRequest);
        CreateRecipeResponseDto createdRecipeResponseDto = recipeMapper.toDto(createdRecipe);

        return new ResponseEntity<>(createdRecipeResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{recipeId}")
    public ResponseEntity<UpdateRecipeResponseDto> updateRecipe(
            @PathVariable UUID recipeId,
            @Valid @RequestBody UpdateRecipeRequestDto updateRecipeRequestDto) {
        //TODO replace next line with real userID
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UpdateRecipeRequest updateRecipeRequest = recipeMapper.fromDto(updateRecipeRequestDto);
        Recipe updateRecipe = recipeService.updateRecipeForPublisher(userId, recipeId, updateRecipeRequest);
        UpdateRecipeResponseDto updateRecipeResponseDto = recipeMapper.toUpdateRecipeResponseDto(updateRecipe);

        return ResponseEntity.ok(updateRecipeResponseDto);
    }
}
