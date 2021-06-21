package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.user.UserDTO;
import com.mcecelja.forum.common.enums.RoleEnum;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.common.mappers.UserMapper;
import com.mcecelja.forum.context.AuthorizedRequestContext;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.domain.user.UserLogin;
import com.mcecelja.forum.repositories.user.UserLoginRepository;
import com.mcecelja.forum.repositories.user.UserRepository;
import com.mcecelja.forum.security.UserPrincipal;
import com.mcecelja.forum.services.common.PermissionCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userService")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserLoginRepository userLoginRepository;

	private final UserRepository userRepository;

	private final PermissionCheckerService permissionCheckerService;

	private final UserMapper userMapper;

	@Override
	public UserDetails findUserDetailsByUserId(Long userId) {
		UserLogin userLogin = userLoginRepository.findUserLoginByUserId(userId);
		return UserPrincipal.create(userLogin);
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserLogin userLogin = userLoginRepository.findUserLoginByUsernameIgnoreCase(s);
		return UserPrincipal.create(userLogin);
	}

	@Override
	public User findUserByUsername(String username) {
		UserLogin userLogin = userLoginRepository.findUserLoginByUsernameIgnoreCase(username);

		if (userLogin == null) {
			return null;
		}

		return userLogin.getUser();
	}

	@Override
	public UserDTO getCurrentUserInfo() {
		User currentUser = AuthorizedRequestContext.getCurrentUser();
		return userMapper.userToUserDTO(currentUser);
	}

	@Override
	public UserDTO getUser(Long userId) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		if(!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !currentUser.getId().equals(userId)) {
			log.warn("Get user failed: user {} doesn't have permission to get user {}!", currentUser.getId(), userId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		Optional<User> userOptional = userRepository.findById(userId);

		if(!userOptional.isPresent()) {
			log.warn("Get user failed: user {} doesn't exist!", userId);
			throw new ForumException(ForumError.USER_NOT_FOUND);
		}

		User user = userOptional.get();

		return userMapper.userToUserDTO(user);
	}

	@Override
	public UserDTO updateUser(Long userId, UserDTO userDTO) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		if(!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !currentUser.getId().equals(userId)) {
			log.warn("Update user failed: user {} doesn't have permission to update user {}!", currentUser.getId(), userId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		Optional<User> userOptional = userRepository.findById(userId);

		if(!userOptional.isPresent()) {
			log.warn("Update user failed: user {} doesn't exist!", userId);
			throw new ForumException(ForumError.USER_NOT_FOUND);
		}

		User user = userOptional.get();

		EmailValidator emailValidator = EmailValidator.getInstance();

		if(!user.getEmail().equals(userDTO.getEmail()) && !emailValidator.isValid(userDTO.getEmail())) {
			log.warn("Update user failed: invalid e-mail address!");
			throw new ForumException(ForumError.INVALID_EMAIL_ADDRESS);
		}

		if(!user.getUserLogin().getUsername().equals(userDTO.getUsername()) && userLoginRepository.existsByUsernameIgnoreCase(userDTO.getUsername())) {
			log.warn("Update user failed: username already in use!");
			throw new ForumException(ForumError.USERNAME_ALREADY_IN_USE);
		}

		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.getUserLogin().setUsername(userDTO.getUsername());

		userRepository.save(user);

		return userMapper.userToUserDTO(user);
	}

	@Override
	public void deleteUser(Long userId) throws ForumException {

		User currentUser = AuthorizedRequestContext.getCurrentUser();

		if(!permissionCheckerService.checkHasRole(currentUser, RoleEnum.ADMIN) && !currentUser.getId().equals(userId)) {
			log.warn("Delete user failed: user {} doesn't have permission to delete user {}!", currentUser.getId(), userId);
			throw new ForumException(ForumError.UNAUTHORIZED);
		}

		Optional<User> userOptional = userRepository.findById(userId);

		if(!userOptional.isPresent()) {
			log.warn("Delete user failed: user {} doesn't exist!", userId);
			throw new ForumException(ForumError.USER_NOT_FOUND);
		}

		userRepository.delete(userOptional.get());
	}
}
