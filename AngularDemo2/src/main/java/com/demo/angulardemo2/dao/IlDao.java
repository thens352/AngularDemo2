package com.demo.angulardemo2.dao;

import javax.ejb.Stateless;

import com.demo.angulardemo2.model.Il;

@Stateless
public class IlDao extends DAO<Il> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989689566020189877L;

	public IlDao() {
		super(Il.class);
		// TODO Auto-generated constructor stub
	}

}
