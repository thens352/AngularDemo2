package com.kayiyazilim.budaksanisakis.dao;

import java.util.List;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.type.MalzemeTipi;

@Stateless
public class MalzemeDao extends DAO<Malzeme> {

	public MalzemeDao() {
		super(Malzeme.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119797279724910357L;

	@SuppressWarnings("unchecked")
	public List<Malzeme> malzemeGetirByTip(MalzemeTipi tip) {

		if (tip != null)
			return entityManager
					.createQuery("from Malzeme m where m.malzemeTipi=:tip")
					.setParameter("tip", tip).getResultList();
		else
			return findAll();
	}
}
