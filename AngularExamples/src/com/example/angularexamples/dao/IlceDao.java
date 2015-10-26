package com.example.angularexamples.dao;

import javax.ejb.Stateless;

import com.example.angularexamples.model.Ilce;

@Stateless
public class IlceDao extends DAO<Ilce> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1817673489498894292L;

	public IlceDao() {
		super(Ilce.class);
		// TODO Auto-generated constructor stub
	}

}
