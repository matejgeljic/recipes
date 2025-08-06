package com.matejgeljic.recipes.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {
    CreateIngredientRequest fromDto(CreateIngredientRequestDto dto);
    CreateRecipeRequest fromDto(CreateRecipeRequestDto dto);
    CreateRecipeResponseDto toDto(Recipe recipe);
}
