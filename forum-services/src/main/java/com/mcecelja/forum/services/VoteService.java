package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.common.dto.vote.VoteRequestDTO;
import com.mcecelja.forum.common.exceptions.ForumException;

public interface VoteService {

	CommentDTO addVote(Long commentId, VoteRequestDTO voteRequestDTO) throws ForumException;

	void removeVote(Long commentId) throws ForumException;
}
