package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.ChatForumComment;


@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface ChatForumCommentDAO {

	// Declare all CRUD Operations...
	
		public boolean save(ChatForumComment forumComment);
		
		public boolean update(ChatForumComment forumComment);
		
		public boolean saveOrUpdate(ChatForumComment forumComment);
		
		public boolean delete(ChatForumComment forumComment);
		
		public ChatForumComment get(String id);
		
		public List<ChatForumComment> list();
}