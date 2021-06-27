package com.mcecelja.forum.common.dto.vote;

import com.mcecelja.forum.common.dto.codebook.CodeBookEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDTO {

	@NotNull
	private CodeBookEntityDTO voteType;
}
