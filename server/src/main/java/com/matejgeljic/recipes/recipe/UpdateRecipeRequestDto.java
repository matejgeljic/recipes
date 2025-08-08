package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientRequestDto;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeRequestDto {
    @NotNull(message = "Recipe id is required")
    private UUID id;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotEmpty(message = "Ingredients are required")
    @Valid
    private List<UpdateIngredientRequestDto> ingredients;
    @NotEmpty(message = "Instructions are required")
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private DishType dishType;
    private DietaryInformation dietaryInformation;
    @NotNull(message = "Recipe status is required")
    private RecipeStatus status;
}
