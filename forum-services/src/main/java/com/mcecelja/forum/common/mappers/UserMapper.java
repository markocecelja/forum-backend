package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.user.UserShortInfoDTO;
import com.mcecelja.forum.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

	public abstract UserShortInfoDTO userToUserShortInfoDTO(User user);
}
