package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;

import com.kayiyazilim.ekentsayim.model.entity.Bilgi;
import com.kayiyazilim.ekentsayim.model.type.Statu;

@Stateless
public class BilgiDao extends DAO<Bilgi> {

	private static final long serialVersionUID = 1787748734206290073L;

	public BilgiDao() {
		super(Bilgi.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Bilgi> getRestAll() {
		return (List<Bilgi>) entityManager.createQuery(
				"select a from Bilgi as a where a.statu=:statu").setParameter(
				"statu", Statu.valueOf("AKTIF")).getResultList();
	}
	
}
