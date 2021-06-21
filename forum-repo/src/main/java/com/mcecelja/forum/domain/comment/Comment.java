package com.mcecelja.forum.domain.comment;

import com.mcecelja.forum.domain.topic.Topic;
import com.mcecelja.forum.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne
	private Topic topic;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private User createdBy;
}
