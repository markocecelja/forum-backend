package com.mcecelja.forum.domain.comment;

import com.mcecelja.forum.domain.AbstractBaseEntity;
import com.mcecelja.forum.domain.topic.Topic;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.domain.vote.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends AbstractBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne
	private Topic topic;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private User createdBy;

	@OneToMany(mappedBy = "comment", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Vote> votes;
}
