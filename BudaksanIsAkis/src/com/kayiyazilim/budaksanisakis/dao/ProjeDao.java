package com.kayiyazilim.budaksanisakis.dao;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.Proje;

@Stateless
public class ProjeDao extends DAO<Proje> {

	public ProjeDao(){
		super(Proje.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 582466835812855217L;

	
}
