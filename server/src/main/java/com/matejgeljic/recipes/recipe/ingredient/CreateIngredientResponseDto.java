package com.matejgeljic.recipes.recipe.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngredientResponseDto {
    private UUID id;
    private String name;
    private Double quantity;
    private Unit unit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
