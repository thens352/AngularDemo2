package com.example.angularexamples.dao;

import javax.ejb.Stateless;

import com.example.angularexamples.model.Il;

@Stateless
public class IlDao extends DAO<Il> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3034184867053957649L;

	public IlDao() {
		super(Il.class);
		// TODO Auto-generated constructor stub
	}

}
