package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



/**
 * The entity class of plan, which have annotation to give connection with other tables 
 * @author kekekeng
 *
 */
@Entity
public class Plan {

	@Id @GeneratedValue
	 private  int planId;
	 private  String planName;
	 private  double budget;
	 private  String planDiscription;
	 private  int groupPeople;
	 
	 public int getGroupPeople() {
		return groupPeople;
	}

	public void setGroupPeople(int groupPeople) {
		this.groupPeople = groupPeople;
	}

	 @ManyToOne()
	 //@JoinColumn(name="User_Id")
	 private User user;
	 
	 public User getUser() {
			return user;
	}

	 public void setUser(User user) {
			this.user = user;
	}
	 
	 @ManyToMany()
	 private Collection<Place> place=new ArrayList<Place>();
	 
	 public Collection<Place> getPlace() {
			return place;
	 }

	 public void setPlace(Collection<Place> place) {
			this.place = place;
	 }
	 
	 @OneToMany(cascade=CascadeType.PERSIST,mappedBy="plan")
	 private  Collection<PlanComment> planComment=new ArrayList<PlanComment>();

	 public String getPlanName() {
		return planName;
	 }

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getPlanDiscription() {
		return planDiscription;
	}

	public void setPlanDiscription(String planDiscription) {
		this.planDiscription = planDiscription;
	}

	public Collection<PlanComment> getPlanComment() {
		return planComment;
	}

	public void setPlanComment(Collection<PlanComment> planComment) {
		this.planComment = planComment;
	}
	 
}
