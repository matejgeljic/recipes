package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientRequest;
import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientRequestDto;
import com.matejgeljic.recipes.recipe.ingredient.Ingredient;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequest;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequestDto;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {
    CreateIngredientRequest fromDto(CreateIngredientRequestDto dto);
    CreateRecipeRequest fromDto(CreateRecipeRequestDto dto);
    CreateRecipeResponseDto toDto(Recipe recipe);
    UpdateIngredientRequest fromDto(UpdateIngredientRequestDto dto);
    UpdateRecipeRequest fromDto(UpdateRecipeRequestDto dto);
    UpdateIngredientResponseDto toUpdateIngredientResponseDto(Ingredient ingredient);
    UpdateRecipeResponseDto toUpdateRecipeResponseDto(Recipe recipe);
}
