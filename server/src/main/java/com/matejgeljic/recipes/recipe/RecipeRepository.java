package com.matejgeljic.recipes.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Page<Recipe> findByPublisherId(UUID publisherId, Pageable pageable);
    Optional<Recipe> findByIdAndPublisherId(UUID id, UUID publisherId);
    Page<Recipe> findByStatus(RecipeStatus status, Pageable pageable);
}
