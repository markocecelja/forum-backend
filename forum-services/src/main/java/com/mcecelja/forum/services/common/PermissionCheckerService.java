package com.mcecelja.forum.services.common;

import com.mcecelja.forum.common.enums.RoleEnum;
import com.mcecelja.forum.domain.user.User;

public interface PermissionCheckerService {

	boolean checkHasRole(User user, RoleEnum role);
}
