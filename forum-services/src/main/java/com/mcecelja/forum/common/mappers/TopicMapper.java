package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.topic.TopicDTO;
import com.mcecelja.forum.domain.topic.Topic;
import com.mcecelja.forum.repositories.comment.CommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class}, imports = {DateTimeFormatter.class})
public abstract class TopicMapper {

	@Autowired
	protected CommentRepository commentRepository;

	@Mappings({
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "createdAt", source = "entity.createdDateTime"),
			@Mapping(target = "numberOfComments", expression = "java((long) entity.getComments().size())"),
			@Mapping(target = "lastCommentDateTime", expression = "java(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(commentRepository.getDateOfLatestCommentByTopicId(entity.getId())))")
	})
	public abstract TopicDTO topicToTopicDTO(Topic entity);
}
