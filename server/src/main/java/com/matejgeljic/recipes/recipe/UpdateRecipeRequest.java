package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientRequest;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeRequest {
    private UUID id;
    private String name;
    private String description;
    private List<UpdateIngredientRequest> ingredients;
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private DishType dishType;
    private DietaryInformation dietaryInformation;
    private RecipeStatus status;
}
