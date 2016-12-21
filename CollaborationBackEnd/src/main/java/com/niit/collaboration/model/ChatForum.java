package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "n_chatforum")
@Component
public class ChatForum extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/**
	 *  declare the database column names for User... 
	 */
	
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FORUM_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int id;
	
	private String description;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	private String userId;
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name = "COUNT_CHATFORUM_LIKE")
	private int countLike;
	
	@Column(name = "COUNT_COMMENT")
	private int countComment;
	
	/**
	 *  getters/setters for all the fields taken... 
	 */
	
	public int getId() {
		return id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCountLike() {
		return countLike;
	}
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}
	public int getCountComment() {
		return countComment;
	}
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
}
