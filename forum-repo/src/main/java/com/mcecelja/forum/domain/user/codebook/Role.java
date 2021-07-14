package com.mcecelja.forum.domain.user.codebook;

import com.mcecelja.forum.domain.AbstractCodeBookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Role extends AbstractCodeBookEntity implements Serializable {
}
