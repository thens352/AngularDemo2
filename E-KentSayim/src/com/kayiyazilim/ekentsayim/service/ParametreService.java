package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.ParametreSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "parametreService")
public class ParametreService implements Serializable {

	private static final long serialVersionUID = -4113448087065832806L;

	@EJB
	private ParametreDao dao;

	private Parametre parametre = new Parametre();

	private List<Parametre> parametreList = new ArrayList<Parametre>();

	private ParametreSearch parametreSearch = new ParametreSearch();

	private List<Parametre> ustAlanList = new ArrayList<Parametre>();

	public Ekran[] getEkranlar() {
		return Ekran.values();
	}

	public Alan[] getAlanlar() {
		return Alan.values();
	}

	public void ara() {
		parametreList = dao.search(parametreSearch);
	}

	@PostConstruct
	public void init() {
		parametreList = dao.findAll();
		for (Parametre p : dao.findAll()) {
			if (p.getAlan() == Alan.MARKA)
				ustAlanList.add(p);
		}
	}

	public void kaydet() {
		dao.persist(parametre);
		if (parametreList == null)
			parametreList = new ArrayList<Parametre>();
		parametreList.add(parametre);
		parametre = new Parametre();
		for (Parametre p : dao.findAll()) {
			if (p.getAlan() == Alan.MARKA)
				ustAlanList.add(p);
		}
	}

	public void guncelle(RowEditEvent event) {
		parametre = ((Parametre) event.getObject());
		dao.merge(parametre);
		parametre = new Parametre();
	}

	public void sil() {
		if (parametre != null)
			dao.remove(parametre);
		parametreList.remove(parametre);
		parametre = new Parametre();
	}

	public Parametre getParametre() {
		return parametre;
	}

	public void setParametre(Parametre parametre) {
		this.parametre = parametre;
	}

	public List<Parametre> getParametreList() {
		return parametreList;
	}

	public void setParametreList(List<Parametre> parametreList) {
		this.parametreList = parametreList;
	}

	public ParametreSearch getParametreSearch() {
		return parametreSearch;
	}

	public void setParametreSearch(ParametreSearch parametreSearch) {
		this.parametreSearch = parametreSearch;
	}

	public List<Parametre> getUstAlanList() {
		return ustAlanList;
	}

	public void setUstAlanList(List<Parametre> ustAlanList) {
		this.ustAlanList = ustAlanList;
	}
}
