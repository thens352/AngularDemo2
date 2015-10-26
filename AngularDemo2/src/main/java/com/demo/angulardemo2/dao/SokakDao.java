package com.demo.angulardemo2.dao;

import javax.ejb.Stateless;

import com.demo.angulardemo2.model.Sokak;

@Stateless
public class SokakDao extends DAO<Sokak> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989689566020189877L;

	public SokakDao() {
		super(Sokak.class);
		// TODO Auto-generated constructor stub
	}

}
