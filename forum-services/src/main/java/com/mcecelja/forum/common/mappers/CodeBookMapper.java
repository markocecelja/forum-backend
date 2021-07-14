package com.mcecelja.forum.common.mappers;

import com.mcecelja.forum.common.dto.codebook.CodeBookEntityDTO;
import com.mcecelja.forum.domain.AbstractCodeBookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CodeBookMapper {

	public abstract CodeBookEntityDTO codeBookEntityToCodeBookEntityDTO(AbstractCodeBookEntity entity);
}
