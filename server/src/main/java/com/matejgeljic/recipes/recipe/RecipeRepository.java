package com.matejgeljic.recipes.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Page<Recipe> findByPublisherId(UUID publisherId, Pageable pageable);
    Optional<Recipe> findByIdAndPublisherId(UUID id, UUID publisherId);
    Page<Recipe> findByStatus(RecipeStatus status, Pageable pageable);
    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.publisher WHERE r.id = :id AND r.status = :status")
    Optional<Recipe> findByIdAndStatusWithPublisher(@Param("id") UUID id, @Param("status") RecipeStatus status);
    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.publisher WHERE r.status = :status")
    Page<Recipe> findByStatusWithPublisher(@Param("status") RecipeStatus status, Pageable pageable);
    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.publisher WHERE r.publisher.id = :userId")
    Page<Recipe> findByPublisherIdWithPublisher(@Param("userId") UUID userId, Pageable pageable);
    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.publisher WHERE r.publisher.id = :userId AND r.status = :status")
    Page<Recipe> findByPublisherIdAndStatusWithPublisher(@Param("userId") UUID userId, @Param("status") RecipeStatus status, Pageable pageable);
}
