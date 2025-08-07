package com.matejgeljic.recipes.recipe;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotEmpty(message = "Ingredients are required")
    @Valid
    private List<CreateIngredientRequestDto> ingredients;
    @NotEmpty(message = "Instructions are required")
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private DishType dishType;
    private DietaryInformation dietaryInformation;
    @NotNull(message = "Recipe status is required")
    private RecipeStatus status;
}
