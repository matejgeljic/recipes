package com.matejgeljic.recipes.rating;

import com.matejgeljic.recipes.recipe.Recipe;
import com.matejgeljic.recipes.recipe.RecipeRepository;
import com.matejgeljic.recipes.recipe.RecipeStatus;
import com.matejgeljic.recipes.user.User;
import com.matejgeljic.recipes.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Rating createOrUpdateRating(UUID userId, UUID recipeId, CreateRatingRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("exception"));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("exception"));

        if (recipe.getStatus() != RecipeStatus.PUBLISHED) {
            throw new IllegalStateException("Cannot rate unpublished recipes");
        }

        if (recipe.getPublisher().getId().equals(userId)) {
            throw new IllegalStateException("Cannot rate your own recipe");
        }

        Optional<Rating> existingRating = ratingRepository.findByUserIdAndRecipeId(userId, recipeId);

        Rating rating;
        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setValue(request.getValue());
        } else {
            rating = new Rating();
            rating.setValue(request.getValue());
            rating.setUser(user);
            rating.setRecipe(recipe);
        }

        rating = ratingRepository.save(rating);

        updateRecipeRatingStatistics(recipe);

        return rating;
    }

    @Override
    public Optional<Rating> getUserRatingForRecipe(UUID userId, UUID recipeId) {
        return ratingRepository.findByUserIdAndRecipeId(userId, recipeId);
    }

    @Override
    public void deleteUserRating(UUID userId, UUID recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("exception"));

        Rating rating = ratingRepository.findByUserIdAndRecipeId(userId, recipeId)
                .orElseThrow(() -> new RuntimeException("exception"));

        ratingRepository.delete(rating);

        updateRecipeRatingStatistics(recipe);
    }

    private void updateRecipeRatingStatistics(Recipe recipe) {
        Optional<Double> avgRating = ratingRepository.calculateAverageRatingByRecipeId(recipe.getId());
        Integer totalRatings = ratingRepository.countByRecipeId(recipe.getId());

        recipe.setAverageRating(avgRating.orElse(null));
        recipe.setTotalRatings(totalRatings);

        recipeRepository.save(recipe);

        log.debug("Updated recipe {} rating statistics: avg={}, total={}",
                recipe.getId(), avgRating.orElse(null), totalRatings);
    }
}
