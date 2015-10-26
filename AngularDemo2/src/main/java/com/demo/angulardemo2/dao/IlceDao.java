package com.demo.angulardemo2.dao;

import javax.ejb.Stateless;

import com.demo.angulardemo2.model.Ilce;

@Stateless
public class IlceDao extends DAO<Ilce> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989689566020189877L;

	public IlceDao() {
		super(Ilce.class);
		// TODO Auto-generated constructor stub
	}

}
