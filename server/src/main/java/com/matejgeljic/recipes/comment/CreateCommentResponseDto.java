package com.matejgeljic.recipes.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResponseDto {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;
}
