package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.topic.TopicDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.specifications.criteria.TopicSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

	TopicDTO getTopic(Long topicId) throws ForumException;

	Page<TopicDTO> getTopics(TopicSearchCriteria criteria, Pageable pageable);

	TopicDTO createTopic(TopicDTO topicDTO);

	TopicDTO updateTopic(Long topicId, TopicDTO topicDTO) throws ForumException;

	void deleteTopic(Long topicId) throws ForumException;
}
