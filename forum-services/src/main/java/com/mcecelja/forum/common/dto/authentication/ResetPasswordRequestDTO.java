package com.mcecelja.forum.common.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDTO {

	@NotBlank
	private String oldPassword;

	@NotBlank
	private String newPassword;

	@NotBlank
	private String confirmationPassword;
}
