package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

/**
 * The entity class of plan comment, which have annotation that give the relationship with other tables 
 * @author kekekeng
 *
 */
@Entity
public class PlanComment implements Serializable{
	
	private static final long serialVersionUID = 8500830544382714685L;

	@Id @GeneratedValue
    private int PlanCommentId;
	
	public int getPlanCommentId() {
		return PlanCommentId;
	}

	public void setPlanCommentId(int planCommentId) {
		PlanCommentId = planCommentId;
	}
	
	@ManyToOne
	private Plan plan;
	
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	

	@ManyToOne
	private User user;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private String planCommentContent;

	public String getPlanCommentContent() {
		return planCommentContent;
	}

	public void setPlanCommentContent(String planCommentContent) {
		this.planCommentContent = planCommentContent;
	}
	
	
     
}
