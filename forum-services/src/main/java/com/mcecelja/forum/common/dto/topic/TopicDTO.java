package com.mcecelja.forum.common.dto.topic;

import com.mcecelja.forum.common.dto.user.UserShortInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

	private String id;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	private UserShortInfoDTO createdBy;

	private String createdAt;

	private Long numberOfComments;

	private String lastCommentDateTime;
}
