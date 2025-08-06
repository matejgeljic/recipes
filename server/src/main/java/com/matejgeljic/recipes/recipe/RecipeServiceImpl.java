package com.matejgeljic.recipes.recipe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public Recipe createRecipe(CreateRecipeRequest recipe) {
        Recipe recipeToCreate = new Recipe();

        List<Ingredient> ingredientsToCreate = recipe.getIngredients().stream().map(
                ingredient -> {
                    Ingredient ingredientToCreate = new Ingredient();
                    ingredientToCreate.setName(ingredient.getName());
                    ingredientToCreate.setQuantity(ingredient.getQuantity());
                    ingredientToCreate.setUnit(ingredient.getUnit());
                    ingredientToCreate.setRecipe(recipeToCreate);
                    return ingredientToCreate;
                }).toList();

        ingredientsToCreate.forEach(i ->
                System.out.println("Ready to save: " + i.getName() + ", quantity: " + i.getQuantity())
        );

        recipeToCreate.setName(recipe.getName());
        recipeToCreate.setDescription(recipe.getDescription());
        recipeToCreate.setIngredients(ingredientsToCreate);
        recipeToCreate.setInstructions(recipe.getInstructions());
        recipeToCreate.setPreparationTime(recipe.getPreparationTime());
        recipeToCreate.setServings(recipe.getServings());
        recipeToCreate.setDishType(recipe.getDishType());
        recipeToCreate.setDietaryInformation(recipe.getDietaryInformation());
        recipeToCreate.setStatus(recipe.getStatus());

        return recipeRepository.save(recipeToCreate);
    }

    @Override
    public Page<Recipe> getPublishedRecipes(Pageable pageable) {
        return recipeRepository.findByStatus(RecipeStatus.PUBLISHED, pageable);
    }
}
