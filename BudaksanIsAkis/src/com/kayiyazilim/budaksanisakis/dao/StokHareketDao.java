package com.kayiyazilim.budaksanisakis.dao;

import java.util.List;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.entity.Proje;
import com.kayiyazilim.budaksanisakis.model.entity.StokHareket;

@Stateless
public class StokHareketDao extends DAO<StokHareket> {

	public StokHareketDao() {
		super(StokHareket.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4826375296433872117L;

	@SuppressWarnings("unchecked")
	public List<StokHareket> stokHareketGetirByProje(Proje proje) {

		return entityManager
				.createQuery("from StokHareket sh where sh.proje=:proje")
				.setParameter("proje", proje).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<StokHareket> stokHareketGetirByMalzeme(Malzeme malzeme) {

		return entityManager
				.createQuery("from StokHareket sh where sh.malzeme=:malzeme")
				.setParameter("malzeme", malzeme).getResultList();

	}

}
