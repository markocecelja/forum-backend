package com.mcecelja.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
	ADMIN(1L), REGULAR(2L);

	private final Long id;
}
