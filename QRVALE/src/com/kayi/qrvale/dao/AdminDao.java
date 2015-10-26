package com.kayi.qrvale.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kayi.qrvale.dao.search.AdminSearch;
import com.kayi.qrvale.model.entity.Admin;

@Stateless
public class AdminDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Admin adminKaydet(Admin admin) {
		entityManager.persist(admin);
		return admin;
	}

	public Admin adminSil(Admin admin) {
		if (entityManager.contains(admin)) {
			entityManager.remove(admin);
			return admin;
		} else {
			Admin yeniAdmin = entityManager.merge(admin);
			entityManager.remove(yeniAdmin);
			return yeniAdmin;
		}
	}

	public Admin adminGuncelle(Admin admin) {
		admin = entityManager.merge(admin);
		return admin;
	}

	public Admin adminGetirById(Integer id) {
		return (Admin) entityManager
				.createQuery("select a from Admin a where a.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	public Admin adminGetirByKullaniciAdi(String kullaniciAdi) {

		try {
			return (Admin) entityManager
					.createQuery(
							"select i from Admin i where i.kullaniciAdi =:kullaniciAdi")
					.setParameter("kullaniciAdi", kullaniciAdi)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Admin> adminGetir(AdminSearch adminSearch) {

		String sql = "select a from Admin as a " + " where 1=1 ";

		if (adminSearch.getAd() != null && !"".equals(adminSearch.getAd())) {
			sql += " and UPPER(a.ad) like :ad";
		}

		if (adminSearch.getSoyad() != null
				&& !"".equals(adminSearch.getSoyad())) {
			sql += " and UPPER(a.soyad) like :soyad";
		}

		if (adminSearch.getKullaniciAdi() != null
				&& !"".equals(adminSearch.getKullaniciAdi())) {
			sql += " and UPPER(a.kullaniciAdi) like :kullaniciAdi";
		}

		if (adminSearch.getEmail() != null
				&& !"".equals(adminSearch.getEmail())) {
			sql += " and UPPER(a.email) like :email";
		}

		if (adminSearch.getIsletme() != null) {
			sql += " and a.isletme=:isletme";
		}

		Query query = entityManager.createQuery(sql);

		if (adminSearch.getAd() != null && !"".equals(adminSearch.getAd())) {
			query.setParameter("ad", "%" + adminSearch.getAd().toUpperCase()
					+ "%");
		}

		if (adminSearch.getSoyad() != null
				&& !"".equals(adminSearch.getSoyad())) {
			query.setParameter("soyad", "%"
					+ adminSearch.getSoyad().toUpperCase() + "%");
		}

		if (adminSearch.getKullaniciAdi() != null
				&& !"".equals(adminSearch.getKullaniciAdi())) {
			query.setParameter("kullaniciAdi", "%"
					+ adminSearch.getKullaniciAdi().toUpperCase() + "%");
		}

		if (adminSearch.getEmail() != null
				&& !"".equals(adminSearch.getEmail())) {
			query.setParameter("email", "%"
					+ adminSearch.getEmail().toUpperCase() + "%");
		}

		if (adminSearch.getIsletme() != null) {
			query.setParameter("isletme", adminSearch.getIsletme());
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Admin> adminGetirByIsletmeId(Integer id) {

		return entityManager
				.createQuery("select i from Admin i where i.isletme.id=:id")
				.setParameter("id", id).getResultList();

	}
}
