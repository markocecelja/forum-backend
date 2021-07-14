package com.mcecelja.forum.specifications;

import com.mcecelja.forum.domain.comment.Comment;
import com.mcecelja.forum.specifications.criteria.CommentSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CommentSearchSpecification {

	public static Specification<Comment> findComments(final CommentSearchCriteria criteria) {

		return (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> restrictions = new ArrayList<>();

			Long topicId = criteria.getTopicId();

			if (topicId != null) {
				restrictions.add(
						criteriaBuilder.equal(root.get("topic").get("id"), topicId)
				);
			}

			criteriaQuery.distinct(true);

			return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
		};
	}
}
