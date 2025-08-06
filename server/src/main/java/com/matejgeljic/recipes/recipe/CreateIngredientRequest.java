package com.matejgeljic.recipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngredientRequest {
    private String name;
    private Double quantity;
    private Unit unit;
}
