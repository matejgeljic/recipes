package com.matejgeljic.recipes.rating;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.matejgeljic.recipes.utils.JwtUtil.parseUserId;

@RestController
@RequestMapping("recipes/{recipeId}/ratings")
@RequiredArgsConstructor
@Slf4j
public class RatingController {
    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @PostMapping
    public ResponseEntity<CreateRatingResponseDto> rateRecipe(
            @PathVariable UUID recipeId,
            @Valid @RequestBody CreateRatingRequestDto request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userId = parseUserId(jwt);

        Rating rating = ratingService.createOrUpdateRating(
                userId,
                recipeId,
                ratingMapper.fromDto(request)
        );

        CreateRatingResponseDto response = ratingMapper.toDto(rating);
        log.info("Created rating for recipe {} with user {}", recipeId, userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeRating(@PathVariable UUID recipeId, @AuthenticationPrincipal Jwt jwt) {
        UUID userId = parseUserId(jwt);

        ratingService.deleteUserRating(userId, recipeId);
        return ResponseEntity.noContent().build();
    }
}
