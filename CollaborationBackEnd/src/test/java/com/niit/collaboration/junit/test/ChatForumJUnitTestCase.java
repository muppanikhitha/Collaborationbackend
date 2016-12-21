/*package com.niit.collaboration.junit.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.ChatForumDAO;
import com.niit.collaboration.model.ChatForum;


public class ChatForumJUnitTestCase {
	
	@Autowired
	ChatForumDAO chatforumDAO;	//instance of ChatChatForumDAO created...
	
	@Autowired
	ChatForum chatforum;		//instance of ChatForum created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		chatforumDAO = (ChatForumDAO) context.getBean("chatforumDAO");
		chatforum = (ChatForum) context.getBean("chatforum");
		
	}
	
	@Test
	public void listChatForum() {
		
		assertEquals(chatforumDAO.list().size(), 1);
	}
	
	//@Test
	public void addChatForum() {
		//chatforum.setId("chatforum002");
		
		assertEquals(chatforumDAO.save(chatforum), true);
	}
	
	//@Test
	public void updateChatForum() {
		//chatforum.setId("chatforum002");
		
		assertEquals(chatforumDAO.update(chatforum), true);
	}
	
	//@Test
	public void deleteChatForum() {
		chatforum.setUserId("chatforum001");
		
		assertEquals(chatforumDAO.delete(chatforum), true);
	}
	
	//@Test
	public void getChatForum() {
		
		assertEquals(chatforumDAO.get("chatforum001").getId(), "chatforum001");
	}
}
*/