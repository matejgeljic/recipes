package com.matejgeljic.recipes.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestDto {
    @NotBlank(message = "Comment content cannot be empty")
    @Size(min = 3, max = 1000, message = "Comment must be between 3 and 1000 characters")
    private String content;
}
