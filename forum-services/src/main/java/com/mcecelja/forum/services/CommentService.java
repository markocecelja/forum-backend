package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.specifications.criteria.CommentSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

	CommentDTO getComment(Long commentId) throws ForumException;

	Page<CommentDTO> getComments(CommentSearchCriteria commentSearchCriteria, Pageable pageable);

	CommentDTO createComment(CommentDTO commentDTO) throws ForumException;

	CommentDTO updateComment(Long commentId, CommentDTO commentDTO) throws ForumException;

	void deleteComment(Long commentId) throws ForumException;
}
