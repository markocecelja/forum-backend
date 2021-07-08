package com.mcecelja.forum.domain.user;

import com.mcecelja.forum.domain.AbstractBaseEntity;
import com.mcecelja.forum.domain.user.codebook.Role;
import com.mcecelja.forum.domain.vote.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SQLDelete(sql="update users SET deleted_date_time = (SELECT CURRENT_TIMESTAMP) where id=?")
@Where(clause = "deleted_date_time IS NULL")
public class User extends AbstractBaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")},
			indexes = {@Index(name = "idx_user_role_user_id", columnList = "user_id"),
					@Index(name = "idx_user_role_role_id", columnList = "role_id")}
	)
	private Set<Role> roles;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private UserLogin userLogin;

	@OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Vote> userVotes;
}
