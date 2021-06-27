package com.mcecelja.forum.domain.vote;

import com.mcecelja.forum.domain.AbstractBaseEntity;
import com.mcecelja.forum.domain.comment.Comment;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.domain.vote.codebook.VoteType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private VoteType voteType;

	@ManyToOne
	private Comment comment;

	@ManyToOne
	private User createdBy;
}
