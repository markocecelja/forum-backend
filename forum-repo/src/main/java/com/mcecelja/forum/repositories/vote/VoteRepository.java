package com.mcecelja.forum.repositories.vote;

import com.mcecelja.forum.domain.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	Vote findVoteByCreatedByIdAndCommentId(Long userId, Long commentId);
}
