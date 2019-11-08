package com.nagarro.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
	final static Logger LOG = Logger.getLogger(CustomerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public boolean addCustomer(Customer customer) {

		LOG.info("Adding to DB new customer : " + customer.getBalance());

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String id = session.save(customer).toString();

		tx.commit();
		session.close();

		if (id == null) {
			return false;
		}
		return true;
	}

	public Customer getCustomer(String id) {
		LOG.info("Retreiving from DB customer details for customerId : " + id);

		Session session = sessionFactory.openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

		Predicate customerIdPredicate = criteriaBuilder.equal(customerRoot.get("id"), id);
		criteriaQuery.select(customerRoot).where(customerIdPredicate);

		Query<Customer> query = session.createQuery(criteriaQuery);

		Customer customer = null;
		try {
			customer = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOG.error("No customer found with customerId : " + id, noResultException.getCause());
		} catch (NonUniqueResultException nonUniqueResultException) {
			LOG.error("More than one customer found with customerId : " + id, nonUniqueResultException.getCause());
		}

		return customer;
	}

	public List<Customer> getCustomers(int offset, int limit, String nameFilter, String emailFilter,
			String contactNumberFilter) {

		LOG.info("Retreiving from DB customers with offset :" + offset + ", limit : " + limit + ", nameFilter : "
				+ nameFilter + ", emailFilter : " + emailFilter + ", contactNumberFilter : " + contactNumberFilter);

		Session session = sessionFactory.openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

		Predicate[] predicates = new Predicate[3];
		predicates[0] = criteriaBuilder.like(customerRoot.<String>get("name"), "%" + nameFilter + "%");
		predicates[2] = criteriaBuilder.like(customerRoot.<String>get("email"), "%" + emailFilter + "%");
		predicates[1] = criteriaBuilder.like(customerRoot.<String>get("contactNumber"),
				"%" + contactNumberFilter + "%");

		criteriaQuery.select(customerRoot).where(predicates);

		Query<Customer> query = session.createQuery(criteriaQuery);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<Customer> customers = query.getResultList();

		session.close();

		return customers;
	}

	public int getCustomerCount(String nameFilter, String emailFilter, String contactNumberFilter) {

		LOG.info("Retreiving from DB number of customers with " + "nameFilter : " + nameFilter + ", emailFilter"
				+ emailFilter + ", contactNumberFilter" + contactNumberFilter);

		Session session = sessionFactory.openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

		Predicate[] predicates = new Predicate[3];
		predicates[0] = criteriaBuilder.like(customerRoot.<String>get("name"), "%" + nameFilter + "%");
		predicates[2] = criteriaBuilder.like(customerRoot.<String>get("email"), "%" + emailFilter + "%");
		predicates[1] = criteriaBuilder.like(customerRoot.<String>get("contactNumber"),
				"%" + contactNumberFilter + "%");

		criteriaQuery.select(criteriaBuilder.count(customerRoot)).where(predicates);

		Query query = session.createQuery(criteriaQuery);
		Long count = (Long) query.getSingleResult();

		return Integer.valueOf(count.toString());
	}

	public boolean updateCustomerBalance(String id, int amount) {

		LOG.info("Updating DB for customerId :" + id + " balance : " + amount);

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<Customer> update = criteriaBuilder.createCriteriaUpdate(Customer.class);
		Root customerRoot = update.from(Customer.class);

		Predicate customerIdPredicate = criteriaBuilder.equal(customerRoot.get("id"), id);

		update.set(customerRoot.get("balance"), criteriaBuilder.sum(customerRoot.get("balance"), amount));
		update.where(customerIdPredicate);

		Query query = session.createQuery(update);
		int rowCount = query.executeUpdate();

		tx.commit();
		session.close();

		return rowCount != 0;
	}

	public boolean blockAmount(String id, int blockAmount) {

		LOG.info("Updating DB for customerId :" + id + " blockAmount : " + blockAmount);

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<Customer> update = criteriaBuilder.createCriteriaUpdate(Customer.class);
		Root customerRoot = update.from(Customer.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(criteriaBuilder.equal(customerRoot.get("id"), id));
		predicates.add(criteriaBuilder.greaterThanOrEqualTo(customerRoot.get("balance"), blockAmount));

		update.set(customerRoot.get("balance"), criteriaBuilder.diff(customerRoot.get("balance"), blockAmount));
		update.set(customerRoot.get("blockedBalance"), blockAmount);
		update.where(predicates.toArray(new Predicate[predicates.size()]));

		int rowCount = 0;

		try {

			Query query = session.createQuery(update);
			rowCount = query.executeUpdate();

		} catch (Exception exception) {
			session.getTransaction().rollback();
		}

		tx.commit();
		session.close();

		return rowCount != 0;
	}

	public boolean adjustAmount(String id, int adjustAmount) {

		LOG.info("Updating DB for customerId :" + id + " adjustAmount : " + adjustAmount);

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<Customer> update = criteriaBuilder.createCriteriaUpdate(Customer.class);
		Root customerRoot = update.from(Customer.class);

		Predicate customerIdPredicate = criteriaBuilder.equal(customerRoot.get("id"), id);
		
		update.set(customerRoot.get("balance"), criteriaBuilder.sum(customerRoot.get("balance"), adjustAmount));
		update.set(customerRoot.get("blockedBalance"), 0);
		update.where(customerIdPredicate);

		int rowCount = 0;

		try {

			Query query = session.createQuery(update);
			rowCount = query.executeUpdate();

		} catch (Exception exception) {
			session.getTransaction().rollback();
		}

		tx.commit();
		session.close();

		return rowCount != 0;
	}
	
	public List<Tuple> getLeaderBoard() {
		LOG.info("Retrieving Leaderboard from DB.");
		
		Session session = sessionFactory.openSession();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
		Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(customerRoot.get("balance")));
		criteriaQuery.multiselect(customerRoot.get("name"), customerRoot.get("balance"));
		
		Query<Tuple> query = session.createQuery(criteriaQuery);
		query.setMaxResults(5);
		
		return query.getResultList();
	}

}
