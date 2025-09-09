package com.matejgeljic.recipes.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRatingResponseDto {
    private UUID id;
    private Integer value;
    private UUID userId;
    private String username;
    private UUID recipeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
