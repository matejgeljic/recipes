package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.exception.IngredientNotFoundException;
import com.matejgeljic.recipes.exception.RecipeNotFoundException;
import com.matejgeljic.recipes.exception.RecipeUpdateException;
import com.matejgeljic.recipes.exception.UserNotFoundException;
import com.matejgeljic.recipes.recipe.ingredient.Ingredient;
import com.matejgeljic.recipes.recipe.ingredient.UpdateIngredientRequest;
import com.matejgeljic.recipes.user.User;
import com.matejgeljic.recipes.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Recipe createRecipe(UUID publisherId, CreateRecipeRequest recipe) {
        User publisher = userRepository.findById(publisherId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id '%s' not found", publisherId)
                ));

        Recipe recipeToCreate = getRecipeToCreate(recipe, publisher);

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

    private static Recipe getRecipeToCreate(CreateRecipeRequest recipe, User publisher) {
        Recipe recipeToCreate = new Recipe();

        recipeToCreate.setName(recipe.getName());
        recipeToCreate.setDescription(recipe.getDescription());
        recipeToCreate.setInstructions(recipe.getInstructions());
        recipeToCreate.setPreparationTime(recipe.getPreparationTime());
        recipeToCreate.setServings(recipe.getServings());
        recipeToCreate.setDishType(recipe.getDishType());
        recipeToCreate.setDietaryInformation(recipe.getDietaryInformation());
        recipeToCreate.setStatus(recipe.getStatus());
        recipeToCreate.setPublisher(publisher);
        return recipeToCreate;
    }

    @Override
    public Optional<Recipe> getRecipe(UUID recipeID) {
        return recipeRepository.findById(recipeID);
    }

    @Override
    @Transactional
    public Recipe updateRecipeForPublisher(UUID publisherId, UUID recipeId, UpdateRecipeRequest recipe) {
        if(recipe.getId() == null) {
            throw new RecipeNotFoundException("Recipe ID cannot be null");
        }

        if(!recipe.getId().equals(recipeId)) {
            throw new RecipeUpdateException("Cannot update the ID of a recipe");
        }

        Recipe existingRecipe = recipeRepository
                .findByIdAndPublisherId(recipeId, publisherId)
                .orElseThrow(() -> new RecipeNotFoundException(String.format("Recipe with id '%s' not found", recipeId)));

        existingRecipe.setName(recipe.getName());
        existingRecipe.setDescription(recipe.getDescription());
        existingRecipe.setInstructions(recipe.getInstructions());
        existingRecipe.setPreparationTime(recipe.getPreparationTime());
        existingRecipe.setServings(recipe.getServings());
        existingRecipe.setDishType(recipe.getDishType());
        existingRecipe.setDietaryInformation(recipe.getDietaryInformation());
        existingRecipe.setStatus(recipe.getStatus());

        Set<UUID> requestIngredientIds = recipe.getIngredients()
                .stream()
                .map(UpdateIngredientRequest::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        existingRecipe.getIngredients().removeIf(
                existingIngredient -> !requestIngredientIds.contains(existingIngredient.getId())
        );

        Map<UUID, Ingredient> existingIngredientIndex = existingRecipe.getIngredients().stream()
                .collect(Collectors.toMap(Ingredient::getId, Function.identity()));

        for(UpdateIngredientRequest ingredient : recipe.getIngredients()) {
            if(ingredient.getId() == null) {
                // Create
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(ingredient.getName());
                newIngredient.setQuantity(ingredient.getQuantity());
                newIngredient.setUnit(ingredient.getUnit());
                existingRecipe.getIngredients().add(newIngredient);
            } else if(existingIngredientIndex.containsKey(ingredient.getId())) {
                // update
                Ingredient existingIngredient = existingIngredientIndex.get(ingredient.getId());
                existingIngredient.setName(ingredient.getName());
                existingIngredient.setQuantity(ingredient.getQuantity());
                existingIngredient.setUnit(ingredient.getUnit());
            } else {
                throw new IngredientNotFoundException(String.format("Ingredient with id '%s' not found", ingredient.getId()));
            }
        }

        return recipeRepository.save(existingRecipe);
    }

    @Override
    public Page<Recipe> getPublishedRecipes(Pageable pageable) {
        return recipeRepository.findByStatus(RecipeStatus.PUBLISHED, pageable);
    }
}
