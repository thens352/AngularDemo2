package com.example.angularexamples.dao;

import javax.ejb.Stateless;

import com.example.angularexamples.model.Mahalle;

@Stateless
public class MahalleDao extends DAO<Mahalle> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1951008061466490368L;

	public MahalleDao() {
		super(Mahalle.class);
		// TODO Auto-generated constructor stub
	}

}
