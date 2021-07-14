package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.user.UserDTO;
import com.mcecelja.forum.common.dto.user.UserShortInfoDTO;
import com.mcecelja.forum.domain.user.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CodeBookMapper.class)
public abstract class UserMapper {

	@Mappings({
			@Mapping(target = "username", source = "entity.userLogin.username"),
			@Mapping(target = "roles", source = "roles")
	})
	public abstract UserDTO userToUserDTO(User entity);

	@Mappings({
			@Mapping(target = "username", source = "entity.userLogin.username")
	})
	public abstract UserShortInfoDTO userToUserShortInfoDTO(User entity);
}
