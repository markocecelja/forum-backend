package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.topic.TopicDTO;
import com.mcecelja.forum.common.enums.RoleEnum;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.common.mappers.TopicMapper;
import com.mcecelja.forum.context.AuthorizedRequestContext;
import com.mcecelja.forum.domain.topic.Topic;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.repositories.specifications.TopicSearchSpecification;
import com.mcecelja.forum.repositories.specifications.criteria.TopicSearchCriteria;
import com.mcecelja.forum.repositories.topic.TopicRepository;
import com.mcecelja.forum.services.common.PermissionCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicServiceImpl implements TopicService {

	private final TopicRepository topicRepository;

	private final PermissionCheckerService permissionCheckerService;

	private final TopicMapper topicMapper;

	@Override
	public TopicDTO getTopic(Long topicId) throws ForumException {

		Optional<Topic> topicOptional = topicRepository.findById(topicId);

		if (!topicOptional.isPresent()) {
			log.warn("Get topic failed: topic {} doesn't exist!", topicId);
			throw new ForumException(ForumError.NON_EXISTING_TOPIC);
		}

		return topicMapper.topicToTopicDTO(topicOptional.get());
	}

	@Override
	public Page<TopicDTO> getTopics(TopicSearchCriteria criteria, Pageable pageable) {

		Page<Topic> topics = topicRepository.findAll(TopicSearchSpecification.findProducts(criteria), pageable);

		return topics.map(topicMapper::topicToTopicDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TopicDTO createTopic(TopicDTO topicDTO) {

		Topic topic = new Topic();
		topic.setTitle(topicDTO.getTitle().trim());
		topic.setDescription(topicDTO.getDescription().trim());
		topic.setCreatedBy(AuthorizedRequestContext.getCurrentUser());

		topicRepository.save(topic);

		return topicMapper.topicToTopicDTO(topic);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TopicDTO updateTopic(Long topicId, TopicDTO topicDTO) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		Optional<Topic> topicOptional = topicRepository.findById(topicId);

		if (!topicOptional.isPresent()) {
			log.warn("Update topic failed: topic {} doesn't exist!", topicId);
			throw new ForumException(ForumError.NON_EXISTING_TOPIC);
		}

		Topic topic = topicOptional.get();

		if (!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !topic.getCreatedBy().getId().equals(currentUser.getId())) {
			log.warn("Update topic failed: user {} is not allowed to update topic {}!", currentUser.getId(), topicId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		topic.setTitle(topicDTO.getTitle().trim());
		topic.setDescription(topicDTO.getDescription().trim());

		topicRepository.save(topic);

		return topicMapper.topicToTopicDTO(topic);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteTopic(Long topicId) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		Optional<Topic> topicOptional = topicRepository.findById(topicId);

		if (!topicOptional.isPresent()) {
			log.warn("Delete topic failed: topic {} doesn't exist!", topicId);
			throw new ForumException(ForumError.NON_EXISTING_TOPIC);
		}

		Topic topic = topicOptional.get();

		if (!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !topic.getCreatedBy().getId().equals(currentUser.getId())) {
			log.warn("Delete topic failed: user {} is not allowed to delete topic {}!", currentUser.getId(), topicId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		topicRepository.delete(topic);
	}
}
