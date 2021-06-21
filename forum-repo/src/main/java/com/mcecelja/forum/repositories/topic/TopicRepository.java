package com.mcecelja.forum.repositories.topic;

import com.mcecelja.forum.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TopicRepository  extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {
}
