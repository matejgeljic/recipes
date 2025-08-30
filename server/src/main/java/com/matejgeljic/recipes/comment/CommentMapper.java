package com.matejgeljic.recipes.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "name", source = "user.name")
    GetCommentResponseDto toGetCommentResponseDto(Comment comment);
    List<GetCommentResponseDto> toGetCommentResponseDtoList(List<Comment> comments);
    CreateCommentResponseDto toCreateResponseDto(Comment comment);
}
