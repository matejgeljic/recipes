package com.matejgeljic.recipes.rating;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    CreateRatingRequest fromDto(CreateRatingRequestDto dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.name")
    @Mapping(target = "recipeId", source = "recipe.id")
    CreateRatingResponseDto toDto(Rating rating);

}
