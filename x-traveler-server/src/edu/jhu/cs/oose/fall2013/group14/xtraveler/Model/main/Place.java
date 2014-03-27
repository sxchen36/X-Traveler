package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class is a entity class of Place, we define a place have placeId, placeName,star which is the evaluation of this
 * place, place's description, imageURL of this place, city and state this place is in, and place feature which may be 
 * fishing, boating and so on. 
 * @author kekekeng
 */
@Entity

public class Place {

	    @Id @GeneratedValue
	    private int placeId;
	    @Column(name = "placeName",unique=true)
		private String placeName;
	   
		private int star;
	    
		private String description;
	    
		private String imageURL;
	    
		private String state;
	    
		private String city;	
	    
		private String placeFeature;
		
		
		public int getPlaceId() {
			return placeId;
		}

		public void setPlaceId(int placeId) {
			this.placeId = placeId;
		}
		
		/**
		 * This ManyToMany annotation
		 */
			
		@ManyToMany(cascade=CascadeType.PERSIST)
		private Collection<Plan> plan= new ArrayList<Plan>();
		
		
		public Collection<Plan> getPlan() {
			return plan;
		}

		public void setPlan(Collection<Plan> plan) {
			this.plan = plan;
		}
		
		
		@OneToMany(cascade=CascadeType.PERSIST,mappedBy="place")
		private Collection<PlaceComment> placeCommentList = new ArrayList<PlaceComment>();
		
		public Collection<PlaceComment> getPlaceCommentList() {
			return placeCommentList;
		}

		public void setPlaceCommentList(Collection<PlaceComment> placeCommentList) {
			this.placeCommentList = placeCommentList;
		}
		
		
		@ManyToMany(mappedBy = "mywishList")
		private Collection<User> wishPlace = new ArrayList<User>();
		
		
		@ManyToMany(mappedBy = "myVisitedList")
		private Collection<User> visitedPlace = new ArrayList<User>();
		

			
		public int getStar() {
			return star;
		}

		public void setStar(int ticketPrice) {
			this.star = ticketPrice;
		}

		public Collection<User> getWishPlace() {
			return wishPlace;
		}

		public void setWishPlace(Collection<User> wishPlace) {
			this.wishPlace = wishPlace;
		}

		public Collection<User> getVisitedPlace() {
			return visitedPlace;
		}

		public void setVisitedPlace(Collection<User> visitedPlace) {
			this.visitedPlace = visitedPlace;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPlaceFeature() {
			return placeFeature;
		}

		public void setPlaceFeature(String placeFeature) {
			this.placeFeature = placeFeature;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImageURL() {
			return imageURL;
		}

		public void setImageURL(String imageURL) {
			this.imageURL = imageURL;
		}

		
		public String getPlaceName() {
			return placeName;
		}

		public void setPlaceName(String placeName) {
			this.placeName = placeName;
		}

}
