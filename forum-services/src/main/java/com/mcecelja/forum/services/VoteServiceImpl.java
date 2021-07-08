package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.common.dto.vote.VoteRequestDTO;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.common.mappers.CommentMapper;
import com.mcecelja.forum.context.AuthorizedRequestContext;
import com.mcecelja.forum.domain.comment.Comment;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.domain.vote.Vote;
import com.mcecelja.forum.domain.vote.codebook.VoteType;
import com.mcecelja.forum.repositories.comment.CommentRepository;
import com.mcecelja.forum.repositories.vote.VoteRepository;
import com.mcecelja.forum.repositories.vote.VoteTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoteServiceImpl implements VoteService {

	private final CommentRepository commentRepository;

	private final VoteTypeRepository voteTypeRepository;

	private final VoteRepository voteRepository;

	private final CommentMapper commentMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public CommentDTO modifyVote(Long commentId, VoteRequestDTO voteRequestDTO) throws ForumException {
		User currentUser = AuthorizedRequestContext.getCurrentUser();

		Optional<Comment> commentOptional = commentRepository.findById(commentId);

		if (!commentOptional.isPresent()) {
			log.warn("Add vote failed: no comment with id {}!", commentId);
			throw new ForumException(ForumError.NON_EXISTING_COMMENT);
		}

		Comment comment = commentOptional.get();

		Optional<VoteType> voteTypeOptional = voteTypeRepository.findById(Long.valueOf(voteRequestDTO.getVoteType().getId()));

		if(!voteTypeOptional.isPresent()) {
			log.warn("Add vote failed: vote type {} doesn't exist!", voteRequestDTO);
			throw new ForumException(ForumError.NON_EXISTING_VOTE_TYPE);
		}

		VoteType voteType = voteTypeOptional.get();

		Vote vote = voteRepository.findVoteByCreatedByIdAndCommentId(currentUser.getId(), commentId);

		if (vote == null) {

			vote = new Vote();
			vote.setComment(comment);
			vote.setCreatedBy(currentUser);
			vote.setVoteType(voteType);

			voteRepository.save(vote);

		} else if (vote.getVoteType().getId().equals(voteType.getId())) {
			comment.getVotes().remove(vote);
		} else {
			vote.setVoteType(voteType);
		}

		return commentMapper.commentToCommentDTO(comment);
	}
}
