package com.matejgeljic.recipes.comment;


import java.util.UUID;

public interface CommentService {
    Comment createComment(UUID recipeId, UUID userId, CreateCommentRequest comment);
}
