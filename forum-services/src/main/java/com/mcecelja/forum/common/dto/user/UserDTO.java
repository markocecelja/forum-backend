package com.mcecelja.forum.common.dto.user;

import com.mcecelja.forum.common.dto.codebook.CodeBookEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String id;

	@NotBlank
	private String username;

	@NotBlank
	private String email;

	private List<CodeBookEntityDTO> roles;
}
