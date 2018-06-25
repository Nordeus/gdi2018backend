package rs.sga.gdi18.hibernate;

import org.hibernate.Session;

@FunctionalInterface
public interface TransactionalCode<T> {

	public T run(final Session session);
}
