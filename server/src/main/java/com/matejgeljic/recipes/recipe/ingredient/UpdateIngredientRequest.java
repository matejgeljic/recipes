package com.matejgeljic.recipes.recipe.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIngredientRequest {
    private UUID id;
    private String name;
    private Double quantity;
    private Unit unit;
}
