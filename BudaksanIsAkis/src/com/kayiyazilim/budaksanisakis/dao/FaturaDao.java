package com.kayiyazilim.budaksanisakis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.budaksanisakis.dao.searchobject.FaturaSearch;
import com.kayiyazilim.budaksanisakis.model.entity.Fatura;

@Stateless
public class FaturaDao extends DAO<Fatura> {

	public FaturaDao() {
		super(Fatura.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9019630719203252995L;

	@SuppressWarnings("unchecked")
	public List<Fatura> faturaAraBySoforID(int id) {
		String s = "from Fatura f where f.id in (select sh.fatura.id from StokHareket sh where sh.id in (select yh.stokHareket.id from YakitHareket yh where yh.sofor.id =:id))";

		Query query = entityManager.createQuery(s);

		query.setParameter("id", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Fatura> faturaAraByAracID(int id) {
		String s = "from Fatura f where f.id in (select sh.fatura.id from StokHareket sh where sh.id in (select yh.stokHareket.id from YakitHareket yh where yh.arac.id =:id))";

		Query query = entityManager.createQuery(s);

		query.setParameter("id", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Fatura> faturaAraByMalzemeID(int id) {
		String s = "from Fatura f where f.id in (select sh.fatura.id from StokHareket sh where sh.malzeme.id =:id)";

		Query query = entityManager.createQuery(s);

		query.setParameter("id", id);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Fatura> faturaAra(FaturaSearch faturaSearch) {
		String sql = "select a from Fatura as a " + " where 1=1 ";

		if (faturaSearch.getMinDuzenlemeTarihi() != null) {
			sql += " and a.duzenlemeTarihi >= :minDuzenlemeTarihi";
		}

		if (faturaSearch.getMaxDuzenlemeTarihi() != null) {
			sql += " and a.duzenlemeTarihi >= :maxDuzenlemeTarihi";
		}

		if (faturaSearch.getMinFiiliSevkTarihi() != null) {
			sql += " and a.fiiliSevkTarihi = :minFiiliSevkTarihi";
		}

		if (faturaSearch.getMaxFiiliSevkTarihi() != null) {
			sql += " and a.fiiliSevkTarihi = :maxFiiliSevkTarihi";
		}

		if (faturaSearch.getProje() != null) {
			sql += " and a.proje = :proje";
		}

		Query query = entityManager.createQuery(sql);

		if (faturaSearch.getMinDuzenlemeTarihi() != null) {
			query.setParameter("minDuzenlemeTarihi",
					faturaSearch.getMinDuzenlemeTarihi());
		}

		if (faturaSearch.getMaxDuzenlemeTarihi() != null) {
			query.setParameter("maxDuzenlemeTarihi",
					faturaSearch.getMaxDuzenlemeTarihi());
		}

		if (faturaSearch.getMinFiiliSevkTarihi() != null) {
			query.setParameter("minFiiliSevkTarihi",
					faturaSearch.getMinFiiliSevkTarihi());
		}

		if (faturaSearch.getMaxFiiliSevkTarihi() != null) {
			query.setParameter("maxFiiliSevkTarihi",
					faturaSearch.getMaxFiiliSevkTarihi());
		}

		if (faturaSearch.getProje() != null) {
			query.setParameter("proje", faturaSearch.getProje());
		}

		return query.getResultList();
	}
}
