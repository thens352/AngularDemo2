package com.demo.angulardemo2.dao;

import javax.ejb.Stateless;

import com.demo.angulardemo2.model.Mahalle;

@Stateless
public class MahalleDao extends DAO<Mahalle> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989689566020189877L;

	public MahalleDao() {
		super(Mahalle.class);
		// TODO Auto-generated constructor stub
	}

}
