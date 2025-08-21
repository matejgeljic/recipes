package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientRequest;
import com.matejgeljic.recipes.recipe.ingredient.CreateIngredientRequestDto;
import com.matejgeljic.recipes.recipe.ingredient.GetRecipeDetailsIngredientResponseDto;
import com.matejgeljic.recipes.recipe.ingredient.Ingredient;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequest;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequestDto;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientResponseDto;
import com.matejgeljic.recipes.user.PublisherDto;
import com.matejgeljic.recipes.user.User;
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
    GetRecipeDetailsIngredientResponseDto toGetRecipeDetailsIngredientResponseDto(Ingredient ingredient);
    GetRecipeDetailsResponseDto toGetRecipeDetailsResponseDto(Recipe recipe);
    PublisherDto toPublisherDto(User user);
}
