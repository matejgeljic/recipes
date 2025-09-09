package com.matejgeljic.recipes.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    Optional<Rating> findByUserIdAndRecipeId(UUID userId, UUID recipeId);

    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.recipe.id = :recipeId")
    Optional<Double> calculateAverageRatingByRecipeId(@Param("recipeId") UUID recipeId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.recipe.id = :recipeId")
    Integer countByRecipeId(@Param("recipeId") UUID recipeId);
}
