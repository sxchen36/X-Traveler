package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * This is an entity class which will generate table of placeComment in database.
 * @author kekekeng
 * 
 */
@Entity 
public class PlaceComment implements Serializable{
		
	private static final long serialVersionUID = -1755853124619950874L;
	@Id @GeneratedValue
	private long placeCommentId;
	private int star;
	private String comment;

   
    
    @ManyToOne()  
	private Place place;
    
    public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
    
    @ManyToOne()   
    private User user; 
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    

	public String getComment() {
		return comment;
	}

	public long getPlaceCommentId() {
		return placeCommentId;
	}

	public void setPlaceCommentId(long placeCommentId) {
		this.placeCommentId = placeCommentId;
	}

	



	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	

	
    
}
