package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.domain.comment.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, TopicMapper.class})
public abstract class CommentMapper {

	@Mappings({
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "topic", source = "entity.topic")
	})
	public abstract CommentDTO commentToCommentDTO(Comment entity);
}
