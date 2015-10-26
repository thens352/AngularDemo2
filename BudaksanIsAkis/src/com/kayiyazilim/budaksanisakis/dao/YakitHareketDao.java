package com.kayiyazilim.budaksanisakis.dao;

import javax.ejb.Stateless;

import com.kayiyazilim.budaksanisakis.model.entity.YakitHareket;

@Stateless
public class YakitHareketDao extends DAO<YakitHareket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6461217335921705103L;

	public YakitHareketDao() {
		// TODO Auto-generated constructor stub
		super(YakitHareket.class);
	}
}
