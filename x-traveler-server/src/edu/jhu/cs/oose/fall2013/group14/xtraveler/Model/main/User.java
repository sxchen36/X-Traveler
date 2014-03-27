package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;






import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceComment;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.Plan;

/**
 * This is a class of a entity class to generate table of user
 * @author kekekeng
 *
 */

@Entity
public class User {
     @Id @GeneratedValue
	private int userId;
    private int age;
	private Date joinDate;
	private String name, userName, password, 
	sex, mobile, hobbie, email;
//	String province;
    
	@ManyToMany()
	private Collection<User> friends = new ArrayList<User>();
	
	public Collection<User> getFriends() {
		return friends;
	}
	
	public void setFriends(Collection<User> friends) {
		this.friends = friends;
	}

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private Collection<PlaceComment> placeComment = new ArrayList<PlaceComment>();

	public Collection<PlaceComment> getPlaceComment() {
		return placeComment;
	}

	public void setPlaceComment(Collection<PlaceComment> placeComment) {
		this.placeComment = placeComment;
	}


	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private Collection<Plan> planList = new ArrayList<Plan>();
	
	public Collection<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(Collection<Plan> planList) {
		this.planList = planList;
	}


	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_mywishlist_place")
	private Collection<Place> mywishList = new ArrayList<Place>();

	public Collection<Place> getMywishList() {
		return mywishList;
	}

	public void setMywishList(Collection<Place> mywishList) {
		this.mywishList = mywishList;
	}
	
	
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_myvisitedlist_place")
	private Collection<Place> myVisitedList = new ArrayList<Place>();
	
	public Collection<Place> getMyVisitedList() {
		return myVisitedList;
	}

	public void setMyVisitedList(Collection<Place> myVisitedList) {
		this.myVisitedList=myVisitedList;
	}
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_plancomment_place")
    private Collection<Plan> planComment = new ArrayList<Plan>();
	
	public Collection<Plan> getPlanComment() {
		return planComment;
	}

	public void setPlanComment(Collection<Plan> planComment) {
		this.planComment=planComment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHobbie() {
		return hobbie;
	}

	public void setHobbie(String hobbie) {
		this.hobbie = hobbie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
