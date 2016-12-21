package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;


@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface ChatForumDAO {

	/**
	 * Declare all CRUD Operations...
	 * 
	 */
	
	public boolean save(ChatForum forum);	
	public boolean saveComment(ChatForumComment forumComment);		//forumComment
	
	public boolean update(ChatForum forum);
		
	public boolean delete(ChatForum forum);
		
	public ChatForum get(int id);
	public ChatForumComment getComment(int id);			//forumComment
	
	public List<ChatForum> list();	
	public List<ChatForumComment> listComment(String id);		//forumComment
	
	//public Forum getLike(int id);
	//public Forum getCountComment(int id);
}