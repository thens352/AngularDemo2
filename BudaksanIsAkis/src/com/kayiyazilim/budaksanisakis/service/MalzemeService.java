package com.kayiyazilim.budaksanisakis.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kayiyazilim.budaksanisakis.dao.MalzemeDao;
import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.type.MalzemeTipi;


@ManagedBean(name = "malzemeService")
@ViewScoped
public class MalzemeService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6751725842989798074L;

	@EJB
	private MalzemeDao malzemeDao;

	private Malzeme malzeme = new Malzeme();

	public MalzemeTipi[] getMalzemeTipleri() {
		return MalzemeTipi.values();
	}

	public void kaydet() {
		malzemeDao.persist(malzeme);
	}

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}
}
