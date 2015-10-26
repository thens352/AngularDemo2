package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.UserSearch;
import com.ens.degerleme.model.entity.User;

@Stateless
public class UserDao {

	@PersistenceContext
	EntityManager entityManager;

	UserSearch userSearch;

	public void userKaydet(User user) {
		entityManager.persist(user);
	}

	public User userGuncelle(User user) {
		return entityManager.merge(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> hepsiniGetir() {

		return entityManager.createQuery("select i from User i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> userGetir(UserSearch userSearch) {
		String sql = "select a from User a where 1=1";

		if (userSearch.getId() != null && 0 != userSearch.getId()) {
			sql += " and a.id=:id";
		}
		if (userSearch.getName() != null
				&& !!"".equals(userSearch.getName())) {
			sql += " and UPPER(a.name) like :name";
		}
		if (userSearch.getSurName() != null
				&& !!"".equals(userSearch.getSurName())) {
			sql += " and UPPER(a.surName) like :surName";
		}

		Query query = entityManager.createQuery(sql);

		if (userSearch.getId() != null && 0 != userSearch.getId()) {
			query.setParameter("id", userSearch.getId());
		}
		if (userSearch.getName() != null
				&& !!"".equals(userSearch.getName())) {
			query.setParameter("name", "%"
					+ userSearch.getName().toUpperCase() + "%");
		}
		if (userSearch.getSurName() != null
				&& !!"".equals(userSearch.getSurName())) {
			query.setParameter("surName", "%"
					+ userSearch.getSurName().toUpperCase() + "%");
		}

		return query.getResultList();
	}

	public User userGetir(Integer id) {
		return entityManager.find(User.class, id);
	}
}
