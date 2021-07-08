package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.topic.TopicDTO;
import com.mcecelja.forum.domain.topic.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public abstract class TopicMapper {

	@Mappings({
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "createdAt", source = "entity.createdDateTime")
	})
	public abstract TopicDTO topicToTopicDTO(Topic entity);
}
