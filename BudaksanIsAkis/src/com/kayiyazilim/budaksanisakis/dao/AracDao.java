package com.kayiyazilim.budaksanisakis.dao;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Arac;

@Stateless
public class AracDao extends DAO<Arac> {

	public AracDao() {
		super(Arac.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8115803666432443213L;

}
