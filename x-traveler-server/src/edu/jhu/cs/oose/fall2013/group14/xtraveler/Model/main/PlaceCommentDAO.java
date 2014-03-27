package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 * This is the class help the upper layer to query the database which mostly have relation with table PlaceComment
 * @author kekekeng
 * 
 */
public class PlaceCommentDAO {
	SessionFactory sessionFactory;
	/**
	 * This is the constructor of this placeCommentDAO class
	 * @param s SessionFactory which is passed in
	 */
    public PlaceCommentDAO(SessionFactory s){
   	 this.sessionFactory=s;
    }
    
    /**
     * This method insert a place comment into database
     * @param userName the user's name who give comment to this place
     * @param placeName the place name 
     * @param star the general evaluation which the system initialized it 
     * @param comment the comment visitor gave
     * 
     */
    public void insertPlaceComment(String userName, String placeName,int star, String comment ){
   	 Session session = sessionFactory.openSession();
		 Transaction tx = null;
		 try{
		   tx=session.beginTransaction();
		   PlaceComment placeComment=new PlaceComment();
		   User u=null;
		   Place p = null;
		   
		   Query query = session
					.createQuery("from User where username= :name");
		   query.setParameter("name", userName);

		   @SuppressWarnings("unchecked")
		   List<User> list = query.list();
		   for (User use_r : list) {				
				u = use_r;
		   }
		   
		   Query query1 = session
					.createQuery("from Place where placeName= :name");
		   query1.setParameter("name", placeName);

		  
		   @SuppressWarnings("unchecked")
		   List<Place> list1 = query1.list();
		   for (Place plac_e : list1) {				
				p = plac_e;
		   }
		   placeComment.setUser(u);
		   placeComment.setStar(star);
		   placeComment.setPlace(p);
		   placeComment.setComment(comment);
		   
		   session.save(placeComment);
		   
		   tx.commit();
		   } catch (Throwable t) {
				if (tx!= null)
				tx.rollback();
				throw t;
		   } finally {
				session.close();
		   }		   
    }
    
    /**
     * This method is used to give us one place's all comments
     * @param placeName The place name
     * @return the total place comment content list the place have
     */
    public ArrayList<ArrayList<String>> getPlaceCommentContent(String placeName){
    	Session session = sessionFactory.openSession();
		 Transaction tx = null;
		 ArrayList<ArrayList<String>> placeComment=new ArrayList<ArrayList<String>>();
		 try{
		   tx=session.beginTransaction();
		   
		   Place p = null;
		   Query query1 = session
					.createQuery("from Place where placeName= :name");
		   query1.setParameter("name", placeName);
		  
		   @SuppressWarnings("unchecked")
		   List<Place> list1 = query1.list();
		   for (Place plac_e : list1) {				
				p = plac_e;
		   }
		   
		   for(PlaceComment al: p.getPlaceCommentList()){
			   ArrayList<String> aList=new ArrayList<String>();
			   aList.add(al.getComment());
			   aList.add(String.valueOf(al.getStar()));
			   placeComment.add(aList);
		   }
		   		   
		   tx.commit();
		   } catch (Throwable t) {
				if (tx!= null)
				tx.rollback();
				throw t;
		   } finally {
				session.close();
		   }	
    	return placeComment;
    	
    }
    
    /**
     * This method we can get a place comment's star
     * @param placeName the name of a place
     * @return the star in one place's comment
     */
    public int getPlaceCommentStar(String placeName){
    	Session session = sessionFactory.openSession();
		 Transaction tx = null;
		 ArrayList<Integer> placeStar=new ArrayList<Integer>();
		 int total=0;
		 int averageStar=0;
		 try{
		   tx=session.beginTransaction();
		   
		   Place p = null;
		   Query query1 = session
					.createQuery("from Place where placeName= :name");
		   query1.setParameter("name", placeName);
		  
		   @SuppressWarnings("unchecked")
		   List<Place> list1 = query1.list();
		   for (Place plac_e : list1) {				
				p = plac_e;
		   }
		   
		   for(PlaceComment al: p.getPlaceCommentList()){
			   placeStar.add(al.getStar());
		   }
		   
		   for(int ps : placeStar){
			   total+=ps;
		   }
		   averageStar=(int)total/placeStar.size();		
		   
		   tx.commit();
		   } catch (Throwable t) {
				if (tx!= null)
				tx.rollback();
				throw t;
		   } finally {
				session.close();
		   }	
    	return averageStar;
    	
    }
}
