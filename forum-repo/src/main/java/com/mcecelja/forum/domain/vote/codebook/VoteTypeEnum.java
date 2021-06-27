package com.mcecelja.forum.domain.vote.codebook;

import lombok.Getter;

@Getter
public enum VoteTypeEnum {
	UP(1L), DOWN(2L);

	private final Long id;

	VoteTypeEnum(Long id) {
		this.id = id;
	}
}
