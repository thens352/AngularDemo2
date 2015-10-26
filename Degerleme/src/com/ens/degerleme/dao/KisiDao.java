package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.KisiSearch;
import com.ens.degerleme.model.entity.Kisi;

@Stateless
public class KisiDao {

	KisiSearch kisiSearch;

	@PersistenceContext
	EntityManager entityManager;

	public void kisiKaydet(Kisi kisi) {
		entityManager.persist(kisi);
	}

	public Kisi kisiGuncelle(Kisi kisi) {
		return entityManager.merge(kisi);
	}

	@SuppressWarnings("unchecked")
	public List<Kisi> hepsiniGetir() {

		return entityManager.createQuery("select i from Kisi i")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Kisi> kisiGetir(KisiSearch kisiSearch) {
		String sql = "select a from Kisi a where 1=1";

		if (kisiSearch.getId() != null && 0 != kisiSearch.getId()) {
			sql += "and a.id=id";
		}
		if (kisiSearch.getName() != null && !!"".equals(kisiSearch.getName())) {
			sql += "and UPPER(a.name) like :name";
		}
		if (kisiSearch.getSurName() != null
				&& !!"".equals(kisiSearch.getSurName())) {
			sql += "and UPPER(a.surName) like :surName";
		}
		if (kisiSearch.getTcKimlikNo() != null
				&& !!"".equals(kisiSearch.getTcKimlikNo())) {
			sql += "and UPPER(a.tcKimlikNo) like :tcKimlikNo";
		}
		if (kisiSearch.getTelNo() != null && !!"".equals(kisiSearch.getTelNo())) {
			sql += "and UPPER(a.telNo) like :telNo";
		}

		Query query = entityManager.createQuery(sql);

		if (kisiSearch.getId() != null && 0 != kisiSearch.getId()) {
			query.setParameter("id", kisiSearch.getId());
		}
		if (kisiSearch.getName() != null && !!"".equals(kisiSearch.getName())) {
			query.setParameter("name", "%" + kisiSearch.getName().toUpperCase()
					+ "%");
		}
		if (kisiSearch.getSurName() != null
				&& !!"".equals(kisiSearch.getSurName())) {
			query.setParameter("surName", "%"
					+ kisiSearch.getSurName().toUpperCase() + "%");
		}
		if (kisiSearch.getTcKimlikNo() != null
				&& !!"".equals(kisiSearch.getTcKimlikNo())) {
			query.setParameter("tcKimlikNo", "%"
					+ kisiSearch.getTcKimlikNo().toUpperCase() + "%");
		}
		if (kisiSearch.getTelNo() != null && !!"".equals(kisiSearch.getTelNo())) {
			query.setParameter("telNo", "%"
					+ kisiSearch.getTelNo().toUpperCase() + "%");
		}
		return query.getResultList();
	}

	public Kisi kisiGetir(Integer id) {
		return entityManager.find(Kisi.class, id);
	}
}
