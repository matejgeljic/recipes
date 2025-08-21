package com.matejgeljic.recipes.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface RecipeService {
    Recipe createRecipe(UUID publisherID, CreateRecipeRequest recipe);
    Optional<Recipe> getRecipe(UUID recipeID);
    Recipe updateRecipeForPublisher(UUID publisherId, UUID recipeId, UpdateRecipeRequest recipe);
    Page<Recipe> getPublishedRecipes(Pageable pageable);
}
