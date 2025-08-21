package com.matejgeljic.recipes.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {
    private UUID id;
    private String name;
    private String email;
}
