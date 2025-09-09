package com.matejgeljic.recipes.rating;

import java.util.Optional;
import java.util.UUID;

public interface RatingService {
    Rating createOrUpdateRating(UUID userId, UUID recipeId, CreateRatingRequest request);
    Optional<Rating> getUserRatingForRecipe(UUID userId, UUID recipeId);
    void deleteUserRating(UUID userId, UUID recipeId);
}
