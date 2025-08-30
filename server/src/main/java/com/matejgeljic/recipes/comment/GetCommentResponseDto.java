package com.matejgeljic.recipes.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentResponseDto {
    private UUID id;
    private String content;
    private UUID userId;
    private String name;
    private LocalDateTime createdAt;
}
