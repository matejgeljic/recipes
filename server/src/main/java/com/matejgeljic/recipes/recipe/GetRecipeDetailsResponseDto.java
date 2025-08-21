package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.recipe.ingredient.GetRecipeDetailsIngredientResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRecipeDetailsResponseDto {
    private UUID id;
    private String name;
    private String description;
    private List<GetRecipeDetailsIngredientResponseDto> ingredients = new ArrayList<>();
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private DishType dishType;
    private DietaryInformation dietaryInformation;
    private RecipeStatus status;
    //TODO publisher
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
