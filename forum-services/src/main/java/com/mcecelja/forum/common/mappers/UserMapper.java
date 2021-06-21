package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.user.UserDTO;
import com.mcecelja.forum.common.dto.user.UserShortInfoDTO;
import com.mcecelja.forum.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

	@Mappings({
			@Mapping(target = "username", source = "entity.userLogin.username")
	})
	public abstract UserDTO userToUserDTO(User entity);

	@Mappings({
			@Mapping(target = "username", source = "entity.userLogin.username")
	})
	public abstract UserShortInfoDTO userToUserShortInfoDTO(User entity);
}
