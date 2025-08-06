package com.matejgeljic.recipes.recipe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public Recipe createRecipe(CreateRecipeRequest recipe) {
        Recipe recipeToCreate = getRecipeToCreate(recipe);

        recipe.getIngredients().forEach(ingredientRequest -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientRequest.getName());
            ingredient.setQuantity(ingredientRequest.getQuantity());
            ingredient.setUnit(ingredientRequest.getUnit());

            recipeToCreate.addIngredient(ingredient);
        });
        log.info("Recipe created successfully with id: {}", recipeToCreate.getId());

        return recipeRepository.save(recipeToCreate);
    }

    @Override
    public Page<Recipe> getPublishedRecipes(Pageable pageable) {
        return recipeRepository.findByStatus(RecipeStatus.PUBLISHED, pageable);
    }

    private static Recipe getRecipeToCreate(CreateRecipeRequest recipe) {
        Recipe recipeToCreate = new Recipe();

        recipeToCreate.setName(recipe.getName());
        recipeToCreate.setDescription(recipe.getDescription());
        recipeToCreate.setInstructions(recipe.getInstructions());
        recipeToCreate.setPreparationTime(recipe.getPreparationTime());
        recipeToCreate.setServings(recipe.getServings());
        recipeToCreate.setDishType(recipe.getDishType());
        recipeToCreate.setDietaryInformation(recipe.getDietaryInformation());
        recipeToCreate.setStatus(recipe.getStatus());
        return recipeToCreate;
    }
}
