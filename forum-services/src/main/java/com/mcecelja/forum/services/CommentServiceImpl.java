package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.common.enums.RoleEnum;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.common.mappers.CommentMapper;
import com.mcecelja.forum.context.AuthorizedRequestContext;
import com.mcecelja.forum.domain.comment.Comment;
import com.mcecelja.forum.domain.topic.Topic;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.repositories.comment.CommentRepository;
import com.mcecelja.forum.repositories.topic.TopicRepository;
import com.mcecelja.forum.services.common.PermissionCheckerService;
import com.mcecelja.forum.specifications.CommentSearchSpecification;
import com.mcecelja.forum.specifications.criteria.CommentSearchCriteria;
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
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;

	private final TopicRepository topicRepository;

	private final PermissionCheckerService permissionCheckerService;

	private final CommentMapper commentMapper;

	@Override
	public CommentDTO getComment(Long commentId) throws ForumException {

		Optional<Comment> commentOptional = commentRepository.findById(commentId);

		if (!commentOptional.isPresent()) {
			log.warn("Get comment failed: comment {} doesn't exist!", commentId);
			throw new ForumException(ForumError.NON_EXISTING_COMMENT);
		}

		return commentMapper.commentToCommentDTO(commentOptional.get());
	}

	@Override
	public Page<CommentDTO> getComments(CommentSearchCriteria commentSearchCriteria, Pageable pageable) {

		Page<Comment> comments = commentRepository.findAll(CommentSearchSpecification.findComments(commentSearchCriteria), pageable);
		return comments.map(commentMapper::commentToCommentDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CommentDTO createComment(CommentDTO commentDTO) throws ForumException {

		if(commentDTO.getTopic() == null) {
			log.warn("Create comment failed: topic can't be null!");
			throw new ForumException(ForumError.BAD_REQUEST);
		}

		Optional<Topic> topicOptional = topicRepository.findById(Long.valueOf(commentDTO.getTopic().getId()));

		if (!topicOptional.isPresent()) {
			log.warn("Create comment failed: topic {} doesn't exist!", commentDTO.getTopic().getId());
			throw new ForumException(ForumError.NON_EXISTING_TOPIC);
		}

		Comment comment = new Comment();
		comment.setContent(commentDTO.getContent());
		comment.setCreatedBy(AuthorizedRequestContext.getCurrentUser());
		comment.setTopic(topicOptional.get());

		commentRepository.save(comment);

		return commentMapper.commentToCommentDTO(comment);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CommentDTO updateComment(Long commentId, CommentDTO commentDTO) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		Optional<Comment> commentOptional = commentRepository.findById(commentId);

		if (!commentOptional.isPresent()) {
			log.warn("Update comment failed: comment {} doesn't exist!", commentId);
			throw new ForumException(ForumError.NON_EXISTING_COMMENT);
		}

		Comment comment = commentOptional.get();

		if (!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !comment.getCreatedBy().getId().equals(currentUser.getId())) {
			log.warn("Update comment failed: user {} is not allowed to update comment {}!", currentUser.getId(), commentId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		comment.setContent(commentDTO.getContent());

		commentRepository.save(comment);

		return commentMapper.commentToCommentDTO(comment);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteComment(Long commentId) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		Optional<Comment> commentOptional = commentRepository.findById(commentId);

		if (!commentOptional.isPresent()) {
			log.warn("Delete comment failed: comment {} doesn't exist!", commentId);
			throw new ForumException(ForumError.NON_EXISTING_COMMENT);
		}

		Comment comment = commentOptional.get();

		if (!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !comment.getCreatedBy().getId().equals(currentUser.getId())) {
			log.warn("Delete comment failed: user {} is not allowed to delete comment {}!", currentUser.getId(), commentId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		commentRepository.delete(comment);
	}
}
