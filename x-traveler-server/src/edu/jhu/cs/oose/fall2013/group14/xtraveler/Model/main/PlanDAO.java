package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;
import java.util.List;











import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.transactions.TransactionRoutine;


/**
 * The Dao layer of the plan table which let the upper layer to query the data with relationship to table plan
 * @author kekekeng
 *
 */

public class PlanDAO {
	 SessionFactory sessionFactory;
 
	 /**
	  * The constructor of PlanDAO
	  * @param s pass in a seesionFactory
	  */
	 public PlanDAO(SessionFactory s){
    	 this.sessionFactory=s;
     }
     
     /**
      * This method can insert a new plan
      * @param userName give the plan creater's name
      * @param planName give the new plan a name
      * @param budget give the budget of this plan will costs
      * @param planDiscription the plan description
      */
     
     public void insertPlan(String userName, String planName,double budget, String planDiscription ){
    	 Session session = sessionFactory.openSession();
 		 Transaction tx = null;
 		 Integer planID = null;
		 try{
		   tx=session.beginTransaction();
		   Plan plan=new Plan();
		   User u=null;
		   Query query = session
					.createQuery("from User where username= :name");
		   query.setParameter("name", userName);

		   @SuppressWarnings("unchecked")
		   List<User> list = query.list();
		   for (User use_r : list) {				
				u = use_r;
		   }
		   plan.setUser(u);
		   plan.setBudget(budget);
		   plan.setPlanName(planName);
		   plan.setPlanDiscription(planDiscription);
		   
		   session.save(plan);
		   
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
      * This method is to delete a plan
      * @param userName the plan creater's name
      * @param planName the plan's name
      */
     public void deletePlan(String userName,String planName ){
    	 Session session = sessionFactory.openSession();
 		 Transaction tx = null;
		 try{
		   tx=session.beginTransaction();
		   User u=null;
		   int userId;
		   Query query = session
					.createQuery("from User where username= :name");
		   query.setParameter("name", userName);
		   @SuppressWarnings("unchecked")
		   List<User> list = query.list();
		   for (User use_r : list) {				
				u = use_r;
		   }
		   userId= u.getUserId();
		   
		   Query query1 = session
					.createQuery("Delete from Plan where planname= :planName AND User_Id=:userId");
		   query1.setParameter("planName", planName);
		   query1.setParameter("userId", userId);

		   query1.executeUpdate();
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
      * This method is to insert a new plan comment
      * @param userName the creater's name
      * @param planName the plan's name
      * @param otherName the commenter's name
      * @param planComment the comment to a plan
      * @param stars the star the commenter gives to a plan
      */
     public void insertPlanComment(String userName, String planName, String otherName, String planComment, int stars){
    	 Session session = sessionFactory.openSession();
 		 Transaction tx = null;
		 try{
		   tx=session.beginTransaction();
		   PlanComment planComment1=new PlanComment();
		   User u=null;
		   Query query = session
					.createQuery("from User where username= :username");
		   query.setParameter("username", userName);

		   @SuppressWarnings("unchecked")
		   List<User> list = query.list();
		   for (User use_r : list) {				
				u = use_r;
		   }
           
		   for(Plan p:u.getPlanList()){
			   if((p.getPlanName()).equals(planName)){
				   p.getPlanComment().add(planComment1);
			   }
		   }
		   
		   session.save(u);
		   
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
     * This method is to insert a comment to a plan
     * @param planName a plan name
     * @param otherName the commenter's name
     * @param comment the comment to this plan
     */
     public void insertPlanComment( String planName,String otherName, String comment ){
    	 Session session = sessionFactory.openSession();
 		 Transaction tx = null;
 		 Integer planID = null;
		 try{
		   tx=session.beginTransaction();
		   PlanComment planComment=new PlanComment();
		   User u=null;
		   Plan plan = null;
		   Integer planCommentID = null;
		   Query query = session
					.createQuery("from User where username= :name");
		   query.setParameter("name", otherName);

		   @SuppressWarnings("unchecked")
		   List<User> list = query.list();
		   for (User use_r : list) {				
				u = use_r;
		   }
		   Query query1 = session
					.createQuery("from Plan where planName= :planname");
		   query1.setParameter("planname", planName);

		   @SuppressWarnings("unchecked")
		   List<Plan> list1 = query1.list();
		   for (Plan pla_n : list1) {				
//			 if((pla_n.getUser().getUserName()).equals(userName)){
			   plan = pla_n;
		//	 }
		   }
		      
		   planComment.setUser(u);
		   planComment.setPlanCommentContent(comment);
		   planComment.setPlan(plan);
		   
		   planCommentID = (Integer) session.save(planComment);
		   
		   tx.commit();
		   } catch (Throwable t) {
				if (tx!= null)
				tx.rollback();
				throw t;
		   } finally {
				session.close();
		   }
		   
     }
     
}