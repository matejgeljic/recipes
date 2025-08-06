package com.matejgeljic.recipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequest {
    private String name;
    private String description;
    private List<CreateIngredientRequest> ingredients;
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private DishType dishType;
    private DietaryInformation dietaryInformation;
    private RecipeStatus status;
}
