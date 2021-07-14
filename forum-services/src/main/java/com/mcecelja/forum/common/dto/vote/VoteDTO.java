package com.mcecelja.forum.common.dto.vote;

import com.mcecelja.forum.domain.vote.codebook.VoteTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

	private Long upVoteCount = 0L;

	private Long downVoteCount = 0L;

	private CurrentUserVoteDTO currentUserVote;

	public void incrementCountForVote(Long voteTypeId) {
		if (voteTypeId.equals(VoteTypeEnum.UP.getId())) {
			upVoteCount++;
		} else if (voteTypeId.equals(VoteTypeEnum.DOWN.getId())) {
			downVoteCount++;
		}
	}
}
