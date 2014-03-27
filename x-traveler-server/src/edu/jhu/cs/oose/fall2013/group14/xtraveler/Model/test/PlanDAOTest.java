package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlanDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * To test if one can get his plan list
 * @author kekekeng
 *
 */
public class PlanDAOTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlanDAO tester = new PlanDAO(sessionFactory);
	UserDAO tester1 =new UserDAO(sessionFactory);
	@Test	
	public void testGetPlanCommentsList(){
		tester1.insertUser("andy", "100000", 40, "20@slk.com","male");
		tester1.insertUser("fynn", "100000", 40, "20@slk.com","male");
		tester1.insertUser("lulu", "100000",5,"sss","ddd");
		tester.insertPlan("fynn", "3 day 2 night bal", 44, "sekdjglsejgh");
		tester.insertPlanComment("fynn","3 day 2 night bal","andy","good plan and have fun",4);
		tester.insertPlanComment("fynn","3 day 2 night bal","lulu","good plan^^",4);
		assertEquals("is fynn have plan?","3 day 2 night bal",tester1.getPlanList("fynn").get(0));
			
	}
}
