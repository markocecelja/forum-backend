package com.mcecelja.forum.common.dto.comment;

import com.mcecelja.forum.common.dto.topic.TopicDTO;
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
public class CommentDTO {

	private String id;

	@NotBlank
	private String content;

	private TopicDTO topic;

	private UserShortInfoDTO createdBy;
}
