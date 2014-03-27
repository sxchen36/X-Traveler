package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.transactions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * 
 * This method is used to get current session and get current transaction, if there is not session or transaction open one
 * @author kekekeng
 *
 * @param <P>
 * @param <R>
 * @param <X>
 */
public abstract class TransactionRoutine<P, R, X extends Exception> {
	private static final ThreadLocal<Transaction> THREAD_TX = new ThreadLocal<Transaction>();
	static
	{
		THREAD_TX.set(null);
	}
	private SessionFactory sessionFactory;

	public TransactionRoutine(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public abstract R executeWithinTransaction(Session session,
			Transaction transaction, P param) throws X;

	public R execute(P param) throws X {
		Session session = sessionFactory.getCurrentSession();
		boolean startedTransaction = false;
		Transaction tx = THREAD_TX.get();
		if (tx == null)
		{
			tx = session.beginTransaction();
			THREAD_TX.set(tx);
			startedTransaction = true;
		}
		try {
			R r = executeWithinTransaction(session, tx, param);
			if (startedTransaction) {
				tx.commit();
				THREAD_TX.set(null);
			}
			return r;
		} catch (Throwable t) {
			if (startedTransaction) {
				tx.rollback();
				THREAD_TX.set(null);
			}
			throw t;
		}
	}
}
