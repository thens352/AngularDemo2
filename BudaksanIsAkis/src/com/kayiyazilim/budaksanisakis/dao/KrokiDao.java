package com.kayiyazilim.budaksanisakis.dao;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Kroki;

@Stateless
public class KrokiDao extends DAO<Kroki> {

	public KrokiDao() {
		super(Kroki.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3860805258854294519L;

}
