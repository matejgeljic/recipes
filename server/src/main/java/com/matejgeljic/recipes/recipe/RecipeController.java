package com.matejgeljic.recipes.recipe;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.matejgeljic.recipes.utils.JwtUtil.parseUserId;

@RestController
@RequestMapping("recipes")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {
    private final RecipeMapper recipeMapper;
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<CreateRecipeResponseDto> createRecipe(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateRecipeRequestDto createRecipeRequestDto) {

        CreateRecipeRequest createRecipeRequest = recipeMapper.fromDto(createRecipeRequestDto);
        UUID userId = parseUserId(jwt);
        Recipe createdRecipe = recipeService.createRecipe(userId, createRecipeRequest);
        CreateRecipeResponseDto createdRecipeResponseDto = recipeMapper.toDto(createdRecipe);

        return new ResponseEntity<>(createdRecipeResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{recipeId}")
    public ResponseEntity<UpdateRecipeResponseDto> updateRecipe(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID recipeId,
            @Valid @RequestBody UpdateRecipeRequestDto updateRecipeRequestDto) {
        UUID userId = parseUserId(jwt);
        UpdateRecipeRequest updateRecipeRequest = recipeMapper.fromDto(updateRecipeRequestDto);
        Recipe updateRecipe = recipeService.updateRecipeForPublisher(userId, recipeId, updateRecipeRequest);
        UpdateRecipeResponseDto updateRecipeResponseDto = recipeMapper.toUpdateRecipeResponseDto(updateRecipe);

        return ResponseEntity.ok(updateRecipeResponseDto);
    }
}
