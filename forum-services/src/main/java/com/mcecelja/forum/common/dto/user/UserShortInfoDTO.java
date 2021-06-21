package com.mcecelja.forum.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserShortInfoDTO {

	private String id;

	private String firstName;

	private String lastName;
}
