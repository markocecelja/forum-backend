package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.codebook.CodeBookEntityDTO;
import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.common.dto.user.UserShortInfoDTO;
import com.mcecelja.forum.common.dto.vote.CurrentUserVoteDTO;
import com.mcecelja.forum.common.dto.vote.VoteDTO;
import com.mcecelja.forum.context.AuthorizedRequestContext;
import com.mcecelja.forum.domain.comment.Comment;
import com.mcecelja.forum.domain.vote.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, TopicMapper.class})
public abstract class CommentMapper {

	@Autowired
	protected UserMapper userMapper;

	@Autowired
	protected CodeBookMapper codeBookMapper;

	@Mappings({
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "topic", source = "entity.topic"),
			@Mapping(target = "vote", expression = "java(getVoteDTO(entity.getVotes()))"),
			@Mapping(target = "createdAt", source = "createdDateTime")
	})
	public abstract CommentDTO commentToCommentDTO(Comment entity);

	protected VoteDTO getVoteDTO(Set<Vote> votes) {
		if (votes == null ||votes.isEmpty()) {
			return null;
		}

		VoteDTO voteDTO = new VoteDTO();

		UserShortInfoDTO userShortInfoDTO = userMapper.userToUserShortInfoDTO(AuthorizedRequestContext.getCurrentUser());
		Vote currentUserVote = votes.stream().filter(vote -> vote.getCreatedBy().getId().toString().equals(userShortInfoDTO.getId())).findFirst().orElse(null);

		if(currentUserVote != null) {
			CodeBookEntityDTO codeBookEntityDTO = codeBookMapper.codeBookEntityToCodeBookEntityDTO(currentUserVote.getVoteType());
			voteDTO.setCurrentUserVote(new CurrentUserVoteDTO(userShortInfoDTO, codeBookEntityDTO));
		}

		votes.forEach(vote -> voteDTO.incrementCountForVote(vote.getVoteType().getId()));

		return voteDTO;
	}
}
