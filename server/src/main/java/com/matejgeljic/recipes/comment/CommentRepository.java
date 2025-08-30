package com.matejgeljic.recipes.comment;

import com.matejgeljic.recipes.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.recipe = :recipe ORDER BY c.createdAt DESC")
    List<Comment> findByRecipeWithUser(@Param("recipe") Recipe recipe);
}
