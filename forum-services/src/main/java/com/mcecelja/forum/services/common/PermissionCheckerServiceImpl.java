package com.mcecelja.forum.services.common;

import com.mcecelja.forum.common.enums.RoleEnum;
import com.mcecelja.forum.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PermissionCheckerServiceImpl implements PermissionCheckerService {

	@Override
	public boolean checkHasRole(User user, RoleEnum role) {
		return user.getRoles().stream().anyMatch(userRole -> userRole.getId().equals(role.getId()));
	}
}
