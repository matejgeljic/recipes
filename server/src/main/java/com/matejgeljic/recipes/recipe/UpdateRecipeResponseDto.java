package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientResponseDto;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequest;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeResponseDto {
    private UUID id;
    private String name;
    private String description;
    private List<UpdateIngredientResponseDto> ingredients;
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private String dishType;
    private String dietaryInformation;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
