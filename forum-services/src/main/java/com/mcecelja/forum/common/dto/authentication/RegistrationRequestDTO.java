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
public class RegistrationRequestDTO {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String confirmationPassword;

	@NotBlank
	private String email;
}
