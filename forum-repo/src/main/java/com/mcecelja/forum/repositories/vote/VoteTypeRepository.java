package com.mcecelja.forum.repositories.vote;

import com.mcecelja.forum.domain.vote.codebook.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteTypeRepository extends JpaRepository<VoteType, Long> {
}
