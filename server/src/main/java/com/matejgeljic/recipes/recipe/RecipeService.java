package com.matejgeljic.recipes.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService {
    Recipe createRecipe(CreateRecipeRequest recipe);
    Page<Recipe> getPublishedRecipes(Pageable pageable);
}
