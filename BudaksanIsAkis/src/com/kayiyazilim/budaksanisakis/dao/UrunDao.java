package com.kayiyazilim.budaksanisakis.dao;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Urun;

@Stateless
public class UrunDao extends DAO<Urun> {

	public UrunDao() {
		super(Urun.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5071040924538773671L;

}
