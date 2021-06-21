package com.mcecelja.forum.specifications;

import com.mcecelja.forum.domain.topic.Topic;
import com.mcecelja.forum.specifications.criteria.TopicSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TopicSearchSpecification {

	public static Specification<Topic> findTopics(final TopicSearchCriteria criteria) {

		return (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> restrictions = new ArrayList<>();

			String title = criteria.getTitle();

			if (title != null && !title.trim().isEmpty()) {
				restrictions.add(
						criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%")
				);
			}

			criteriaQuery.distinct(true);

			return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
		};
	}
}
