package com.matejgeljic.recipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// TODO check should enums stay enum or be changed to strings

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeResponseDto {
    private UUID id;
    private String name;
    private String description;
    private List<CreateIngredientResponseDto> ingredients;
    private List<String> instructions;
    private Duration preparationTime;
    private Integer servings;
    private String dishType;
    private String dietaryInformation;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
