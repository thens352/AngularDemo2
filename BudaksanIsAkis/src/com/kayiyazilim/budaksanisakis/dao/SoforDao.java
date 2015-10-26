package com.kayiyazilim.budaksanisakis.dao;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Sofor;

@Stateless
public class SoforDao extends DAO<Sofor> {

	public SoforDao(){
		super(Sofor.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1052534771817926478L;

}
