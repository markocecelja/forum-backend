package com.mcecelja.forum.rest;

import com.mcecelja.forum.common.dto.comment.CommentDTO;
import com.mcecelja.forum.common.dto.vote.VoteRequestDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.services.CommentService;
import com.mcecelja.forum.services.VoteService;
import com.mcecelja.forum.specifications.criteria.CommentSearchCriteria;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

	private final CommentService commentService;

	private final VoteService voteService;

	@GetMapping("/{commentId}")
	public ResponseEntity<ResponseMessage<CommentDTO>> getComment(@PathVariable("commentId") Long commentId) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(commentService.getComment(commentId)));
	}

	@GetMapping("")
	public ResponseEntity<ResponseMessage<Page<CommentDTO>>> getComments(@RequestParam Long topicId,
	                                                                     @PageableDefault(size = 20, sort = "id", direction = ASC) Pageable pageable) {
		return ResponseEntity.ok(new ResponseMessage<>(commentService.getComments(new CommentSearchCriteria(topicId), pageable)));
	}

	@PostMapping("")
	public ResponseEntity<ResponseMessage<CommentDTO>> createComment(@Valid @RequestBody CommentDTO commentDTO) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(commentService.createComment(commentDTO)));
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<ResponseMessage<CommentDTO>> updateComment(@PathVariable("commentId") Long commentId, @Valid @RequestBody CommentDTO commentDTO) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(commentService.updateComment(commentId, commentDTO)));
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ResponseMessage<String>> deleteComment(@PathVariable("commentId") Long commentId) throws ForumException {
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}

	@PutMapping("/{commentId}/vote")
	public ResponseEntity<ResponseMessage<CommentDTO>> addVote(@PathVariable Long commentId, @Valid @RequestBody VoteRequestDTO voteRequestDTO) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(voteService.addVote(commentId, voteRequestDTO)));
	}

	@DeleteMapping("/{commentId}/vote")
	public ResponseEntity<ResponseMessage<String>> removeVote(@PathVariable Long commentId) throws ForumException {
		voteService.removeVote(commentId);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}
}
