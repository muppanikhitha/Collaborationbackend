package com.niit.collaboration.junit.test;



import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;


public class BlogJUnitTestCase {
	
	Logger log =  Logger.getLogger(BlogJUnitTestCase.class);
	
	@Autowired
	BlogDAO blogDAO;	//instance of BlogDAO created...
	
	@Autowired
	Blog blog;		//instance of Blog created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		
	}
	
	//@Test
	public void listBlog() {
		log.debug("Entered in listBlog method.........");
		assertEquals(blogDAO.list().size(), 1);
	}
		
	@Test
	public void addBlog() {
		blog.setId(3);
		blog.setTitle("#1 Blog");
		blog.setReason("Testpurpose");
		blog.setContent("This  blog...");
		blog.setUserId("nikhi001");
		blog.setDescription("blog");
		blog.setPostDate(null);
		blog.setCountLike(0);

		assertEquals(blogDAO.save(blog), true);
	}
	
	//@Test
/*	public void updateBlog() {
		//blog.setId("arpan001");
		blog.setTitle("#1 Blog");
		blog.setContent("This is my first blog...");
		blog.setUserId("sovan001");
		blog.setStatus("approved");
		
		assertEquals(blogDAO.update(blog), true);
	}
*/	
	//@Test
/*	public void deleteBlog() {
		blog.setId(1);
		
		assertEquals(blogDAO.delete(blog), true);
	}
*/	
	//@Test
	public void getBlog() {
		
		//assertEquals(blogDAO.get("blog001").getTitle(), "#1 Blog");
	}
}
