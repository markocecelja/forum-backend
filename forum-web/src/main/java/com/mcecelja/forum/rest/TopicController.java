package com.mcecelja.forum.rest;

import com.mcecelja.forum.common.dto.topic.TopicDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.specifications.criteria.TopicSearchCriteria;
import com.mcecelja.forum.services.TopicService;
import com.mcecelja.forum.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicController {

	private final TopicService topicService;

	@GetMapping("/{topicId}")
	public ResponseEntity<ResponseMessage<TopicDTO>> getTopic(@PathVariable("topicId") Long topicId) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(topicService.getTopic(topicId)));
	}

	@GetMapping("")
	public ResponseEntity<ResponseMessage<Page<TopicDTO>>> getTopics(TopicSearchCriteria criteria,
	                                                                 @PageableDefault(size = 20, sort = "id", direction = ASC) Pageable pageable) {
		return ResponseEntity.ok(new ResponseMessage<>(topicService.getTopics(criteria, pageable)));
	}

	@PostMapping("")
	public ResponseEntity<ResponseMessage<TopicDTO>> createTopic(@Valid @RequestBody TopicDTO topicDTO) {
		return ResponseEntity.ok(new ResponseMessage<>(topicService.createTopic(topicDTO)));
	}

	@PutMapping("/{topicId}")
	public ResponseEntity<ResponseMessage<TopicDTO>> updateTopic(@PathVariable("topicId") Long topicId, @Valid @RequestBody TopicDTO topicDTO) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(topicService.updateTopic(topicId, topicDTO)));
	}

	@DeleteMapping("/{topicId}")
	public ResponseEntity<ResponseMessage<String>> deleteTopic(@PathVariable("topicId") Long topicId) throws ForumException {
		topicService.deleteTopic(topicId);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}
}
