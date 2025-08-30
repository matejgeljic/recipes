package com.matejgeljic.recipes.comment;

import com.matejgeljic.recipes.exception.RecipeNotFoundException;
import com.matejgeljic.recipes.exception.UserNotFoundException;
import com.matejgeljic.recipes.recipe.Recipe;
import com.matejgeljic.recipes.recipe.RecipeRepository;
import com.matejgeljic.recipes.recipe.RecipeStatus;
import com.matejgeljic.recipes.user.User;
import com.matejgeljic.recipes.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Comment createComment(UUID recipeId, UUID userId, CreateCommentRequest comment) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RecipeNotFoundException(String.format("Recipe with id '%s' not found", recipeId)));

        if (recipe.getStatus() != RecipeStatus.PUBLISHED) {
            // TODO replace exception
             throw new IllegalStateException("Cannot comment on unpublished recipes");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User not found with id: %s", userId)
                ));

        Comment commentToCreate = new Comment();
        commentToCreate.setContent(comment.getContent());
        commentToCreate.setRecipe(recipe);
        commentToCreate.setUser(user);

        return commentRepository.save(commentToCreate);
    }

    @Override
    public List<Comment> getCommentsByRecipeId(UUID recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RecipeNotFoundException(String.format("Recipe with id '%s' not found", recipeId)));

        return commentRepository.findByRecipeWithUser(recipe);
    }
}
