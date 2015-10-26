package com.example.angularexamples.dao;

import javax.ejb.Stateless;

import com.example.angularexamples.model.Sokak;

@Stateless
public class SokakDao extends DAO<Sokak> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4476029986327009212L;

	public SokakDao() {
		super(Sokak.class);
		// TODO Auto-generated constructor stub
	}

}
