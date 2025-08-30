package com.matejgeljic.recipes.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.matejgeljic.recipes.utils.JwtUtil.parseUserId;

@RestController
@RequestMapping("recipes/{recipeId}/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID recipeId,
            @Valid @RequestBody CreateCommentRequestDto createCommentRequestDto)
    {
        UUID userId = parseUserId(jwt);
        CreateCommentRequest commentToCreate = new CreateCommentRequest();
        commentToCreate.setContent(createCommentRequestDto.getContent());

        Comment createdComment = commentService.createComment(recipeId, userId, commentToCreate);

        CreateCommentResponseDto createCommentResponseDto = commentMapper.toCreateResponseDto(createdComment);

        return new ResponseEntity<>(createCommentResponseDto, HttpStatus.CREATED);
    }
}
