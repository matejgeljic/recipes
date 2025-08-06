package com.matejgeljic.recipes.recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Double quantity;

    @NotNull(message = "Unit must be selected")
    private Unit unit;
}
