package com.mcecelja.forum.common.dto.vote;

import com.mcecelja.forum.common.dto.codebook.CodeBookEntityDTO;
import com.mcecelja.forum.common.dto.user.UserShortInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserVoteDTO {

	private UserShortInfoDTO currentUser;

	private CodeBookEntityDTO voteType;
}
