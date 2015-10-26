package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.SoruSearch;
import com.ens.degerleme.model.entity.Soru;

@Stateless
public class SoruDao {

	@PersistenceContext
	EntityManager entityManager;

	public Soru soruSil(Integer id) {
		Soru soru = entityManager.find(Soru.class, id);
		entityManager.remove(soru);
		return soru;
	}

	public void soruKaydet(Soru soru) {

		entityManager.persist(soru);

	}

	public Soru soruGuncelle(Soru soru) {

		return entityManager.merge(soru);

	}

	@SuppressWarnings("unchecked")
	public List<Soru> soruGetirByBolumId(Integer id) {
		return entityManager
				.createQuery("select i from Soru i where i.bolum.id=:id")
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Soru> soruGetir(SoruSearch soruSearch) {
		String sql = "select s from Soru s where 1=1 ";

		if (soruSearch.getId() != null && 0 != soruSearch.getId()) {
			sql = sql + " and s.id=:id";
		}

		if (0 != soruSearch.getSiraNo()) {
			sql = sql + " and s.siraNo=:siraNo";
		}

		if (0 != soruSearch.getParametreSayisi()) {
			sql = sql + " and s.parametreSayisi=:parametreSayisi";
		}

		if (soruSearch.getBolum() != null
				&& soruSearch.getBolum().getName() != null
				&& !!"".equals(soruSearch.getBolum().getName())) {
			sql = sql + " and s.bolum.name =:bolumAdi";
		}

		Query query = entityManager.createQuery(sql);

		if (soruSearch.getId() != null && 0 != soruSearch.getId()) {
			query.setParameter("id", soruSearch.getId());
		}

		if (0 != soruSearch.getSiraNo()) {
			query.setParameter("siraNo", soruSearch.getSiraNo());
		}

		if (0 != soruSearch.getParametreSayisi()) {
			query.setParameter("parametreSayisi",
					soruSearch.getParametreSayisi());
		}
		if (soruSearch.getBolum() != null
				&& soruSearch.getBolum().getName() != null
				&& !!"".equals(soruSearch.getBolum().getName())) {
			query.setParameter("bolumAdi", "%"
					+ soruSearch.getBolum().getName() + "%");
		}

		return query.getResultList();

	}

	public Soru soruGetir(Integer id) {
		return entityManager.find(Soru.class, id);
	}

}
