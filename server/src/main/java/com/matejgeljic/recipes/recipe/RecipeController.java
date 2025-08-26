package com.matejgeljic.recipes.recipe;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.matejgeljic.recipes.utils.JwtUtil.parseUserId;

@RestController
@RequestMapping("recipes")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {
    private final RecipeMapper recipeMapper;
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<CreateRecipeResponseDto> createRecipe(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateRecipeRequestDto createRecipeRequestDto) {

        CreateRecipeRequest createRecipeRequest = recipeMapper.fromDto(createRecipeRequestDto);
        UUID userId = parseUserId(jwt);
        Recipe createdRecipe = recipeService.createRecipe(userId, createRecipeRequest);
        CreateRecipeResponseDto createdRecipeResponseDto = recipeMapper.toDto(createdRecipe);

        return new ResponseEntity<>(createdRecipeResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{recipeId}")
    public ResponseEntity<UpdateRecipeResponseDto> updateRecipe(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID recipeId,
            @Valid @RequestBody UpdateRecipeRequestDto updateRecipeRequestDto) {
        UUID userId = parseUserId(jwt);
        UpdateRecipeRequest updateRecipeRequest = recipeMapper.fromDto(updateRecipeRequestDto);
        Recipe updateRecipe = recipeService.updateRecipeForPublisher(userId, recipeId, updateRecipeRequest);
        UpdateRecipeResponseDto updateRecipeResponseDto = recipeMapper.toUpdateRecipeResponseDto(updateRecipe);

        return ResponseEntity.ok(updateRecipeResponseDto);
    }

    @GetMapping(path = "/{recipeId}")
    public ResponseEntity<GetRecipeDetailsResponseDto> getRecipe(
            @PathVariable UUID recipeId
    ) {
        return recipeService.getRecipe(recipeId)
                .map(recipeMapper::toGetRecipeDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<GetRecipeSummaryResponseDto>> getAllPublishedRecipes(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Recipe> recipes;

        if (StringUtils.hasText(search)) {
            Pageable searchPageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "created_at")
            );
            recipes = recipeService.searchPublishedRecipes(search, searchPageable);
        } else {
            recipes = recipeService.getPublishedRecipes(pageable);
        }

        Page<GetRecipeSummaryResponseDto> recipeDtos = recipes.map(recipeMapper::toGetRecipeSummaryResponseDto);
        return ResponseEntity.ok(recipeDtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<GetRecipeSummaryResponseDto>> getPublishedRecipesByUser (
            @PathVariable UUID userId,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Recipe> recipes = recipeService.getPublishedRecipesByUser(userId, pageable);

        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Page<GetRecipeSummaryResponseDto> recipeDtos = recipes.map(recipeMapper::toGetRecipeSummaryResponseDto);

        return ResponseEntity.ok(recipeDtos);
    }

    @GetMapping("/my-recipes")
    public ResponseEntity<Page<GetRecipeSummaryResponseDto>> getMyRecipes(
            @AuthenticationPrincipal Jwt jwt,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        UUID userId = parseUserId(jwt);

        Page<Recipe> recipes = recipeService.getCurrentUserRecipes(userId, pageable);
        Page<GetRecipeSummaryResponseDto> recipeDtos = recipes.map(recipeMapper::toGetRecipeSummaryResponseDto);

        return ResponseEntity.ok(recipeDtos);
    }
}
