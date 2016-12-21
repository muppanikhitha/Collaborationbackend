package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.ChatForumDAO;
import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;
import com.niit.collaboration.model.Users;


@RestController
public class ChatForumController {
	
	Logger log = Logger.getLogger(ChatForumController.class);
	
	@Autowired
	ChatForumDAO chatforumDAO;
	
	@Autowired
	ChatForum chatforum;
	
	/**
	 * 	http://localhost:8020/CollaborationFrontEnd/chatforums								[working]
	 * @return
	 */
	@GetMapping(value = "/chatforums")
	public ResponseEntity<List<ChatForum>> listChatForums() {
		log.debug("**********Starting of listChatfForums() method.");
		List<ChatForum> chatforum = chatforumDAO.list();
		if(chatforum.isEmpty()) {
			return new ResponseEntity<List<ChatForum>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listChatForums() method.");
		return new ResponseEntity<List<ChatForum>>(chatforum, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8020/CollaborationFrontEnd/chatforum/									[working]
	 * @param chatforum
	 * @return
	 */
	@PostMapping(value = "/chatforums/")
	public ResponseEntity<ChatForum> createChatForum(@RequestBody ChatForum chatforum, HttpSession session) {
		log.debug("**********Starting of createChatForum() method.");
		if(chatforumDAO.get(chatforum.getId()) == null) {
			
			Users loggedInUser = (Users) session.getAttribute("loggedInUser");
			
			chatforum.setUserId(loggedInUser.getId());
			chatforumDAO.save(chatforum);
			
			log.debug("**********End of createChatForum() method.");
			return new ResponseEntity<ChatForum>(chatforum, HttpStatus.OK);
		}
		chatforum.setErrMessage("ChatForum already exist with id : " +chatforum.getId());
		log.error("ChatForum already exist with id : " +chatforum.getId());
		return new ResponseEntity<ChatForum>(chatforum, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8020/CollaborationFrontEnd/chatforum/{id}								[working]
	 * @param id
	 * @param chatforum
	 * @return
	 */
	@PutMapping(value = "/chatforum/{id}")
	public ResponseEntity<ChatForum> updateChatForum(@PathVariable("id") int id, @RequestBody ChatForum chatforum) {
		log.debug("**********Starting of updateChatForum() method.");
		if(chatforumDAO.get(id) == null) {
			chatforum = new ChatForum();
			chatforum.setErrMessage("No chatforum exist with id : " +chatforum.getId());
			log.error("No chatforum exist with id : " +chatforum.getId());
			return new ResponseEntity<ChatForum>(chatforum, HttpStatus.NOT_FOUND);
		}
		chatforumDAO.update(chatforum);
		log.debug("**********End of updateChatForum() method.");
		return new ResponseEntity<ChatForum>(chatforum, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8020/CollaborationFrontEnd/chatforum/{id}								[working]
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/chatforum/{id}")
	public ResponseEntity<ChatForum> deleteChatForum(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteChatForum() method.");
		ChatForum chatforum = chatforumDAO.get(id);
		if(chatforum == null) {
			chatforum = new ChatForum();
			chatforum.setErrMessage("No chatforum exist with id : " + id);
			log.error("No chatforum exist with id : " + id);
			return new ResponseEntity<ChatForum>(chatforum, HttpStatus.NOT_FOUND);
		}
		chatforumDAO.delete(chatforum);
		log.debug("**********End of deleteChatForum() method.");
		return new ResponseEntity<ChatForum>(HttpStatus.OK);		
	}
	
	/**
	 * 	http://localhost:8020/CollaborationFrontEnd/chatforum/{id}							[working]
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/chatforum/{id}")
	public ResponseEntity<ChatForum> getChatForum(@PathVariable("id") int id) {
		log.debug("**********Starting of getChatForum() method.");
		ChatForum chatforum = chatforumDAO.get(id);
		if(chatforum == null) {
			chatforum = new ChatForum();
			chatforum.setErrMessage("No chatforum exist with id : " + id);
			log.error("No chatforum exist with id : " + id);
			return new ResponseEntity<ChatForum>(chatforum, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getChatForum() method.");
		return new ResponseEntity<ChatForum>(chatforum, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8020/CollaborationFrontEnd/chatforum/likeForum/{id}			[working]
	 * @param id
	 * @param chatforum
	 * @return
	 */
	@PutMapping(value = "/chatforum/likeChatForum/{id}")
	public ResponseEntity<ChatForum> likeChatForum(@PathVariable("id") int id, @RequestBody ChatForum chatforum){
		log.debug("**********Starting of likeChatForum() method.");
		
		int like = chatforum.getCountLike();
		chatforum.setCountLike(like + 1);		
		chatforumDAO.update(chatforum);
		
		log.debug("**********End of likeChatForum() method.");
		return new ResponseEntity<ChatForum>(chatforum, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8020/CollaborationFrontEnd/chatforum/countComment/{id}			[working]
	 * @param id
	 * @param chatforum
	 * @return
	 */
	@PutMapping(value = "/chatforum/countComment/{id}")
	public ResponseEntity<ChatForum> countComment(@PathVariable("id") int id, @RequestBody ChatForum chatforum){
		log.debug("**********Starting of countComment() method.");
		
		int countComment = chatforum.getCountComment();
		chatforum.setCountComment(countComment + 1);
		chatforumDAO.update(chatforum);
		
		log.debug("**********End of countComment() method.");
		return new ResponseEntity<ChatForum>(chatforum, HttpStatus.OK);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|Forum Comment Area|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	/**
	 * http://localhost:8020/CollaborationFrontEnd/chatforumComments						[working]
	 * @return
	 */
	/*@GetMapping(value = "/chatforumComments/{chatforumId}")
	public ResponseEntity<List<ChatForumComment>> listChatForumComments(@PathVariable("chatforumId") String chatforumId) {
		log.debug("**********Starting of listChatForumComments() method.");
		List<ChatForumComment> chatforumComment = chatforumDAO.listComment(chatforumId);
		if(chatforumComment.isEmpty()) {
			return new ResponseEntity<List<ChatForumComment>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listForumComments() method.");
		return new ResponseEntity<List<ChatForumComment>>(chatforumComment, HttpStatus.OK);
	}
	
	*//**
	 * http://localhost:8020/CollaborationFrontEnd/chatforumComment/					[working]
	 * @param chatforumComment
	 * @param session
	 * @return
	 *//*
	@PostMapping(value = "/chatforumComment/")
	public ResponseEntity<ChatForumComment> createForumComment(@RequestBody ChatForumComment chatforumComment, HttpSession session) {
		log.debug("**********Starting of createForumComment() method.");
		Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		chatforumComment.setUserId(loggedInUser.getId());
		
		chatforumDAO.saveComment(chatforumComment);
		log.debug("**********End of createForumComment() method.");
		return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
	}
	*/
	/**
	 * http://localhost:8020/CollaborationFrontEnd/chatforumComment/{id}							[working]
	 * @param id
	 * @return
	 */
/*	@GetMapping(value = "/chatforumComment/{id}")
	public ResponseEntity<ChatForumComment> getChatForumComment(@PathVariable("id") int id) {
		log.debug("**********Starting of getChatForumComment() method.");
		ChatForumComment chatforumComment = chatforumDAO.getComment(id);
		if(chatforumComment == null) {
			chatforumComment = new ChatForumComment();
			chatforumComment.setErrMessage("No getForumComment exist with id : " + id);
			log.error("No getChatForumComment exist with id : " + id);
			return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getForumComment() method.");
		return new ResponseEntity<ChatForumComment>(chatforumComment, HttpStatus.OK);
	}*/
}
