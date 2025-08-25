package com.matejgeljic.recipes.recipe;

import com.matejgeljic.recipes.user.PublisherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRecipeSummaryResponseDto {
    private UUID id;
    private String name;
    private DishType dishType;
    private RecipeStatus status;
    private PublisherDto publisher;
}
