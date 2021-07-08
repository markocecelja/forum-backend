package com.mcecelja.forum.repositories.comment;

import com.mcecelja.forum.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

	@Query(value = "select max(created_date_time) from comment where topic_id = :topicId", nativeQuery = true)
	LocalDateTime getDateOfLatestCommentByTopicId(@Param("topicId") Long topicId);
}
