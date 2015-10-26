package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.dao.searchobject.IlceSearch;

@ManagedBean(name = "ilceListService")
@ViewScoped
public class IlceListService implements Serializable {

	private static final long serialVersionUID = -6093393727814589664L;
	@EJB
	private IlceDao ilceDao;

	IlceSearch ilceSearch = new IlceSearch();

	List<Ilce> ilceList = new ArrayList<Ilce>();

	@PostConstruct
	public void init() {
		ilceList = ilceDao.ilceGetirByOrnek(ilceSearch);
	}

	public void ilceSil(Ilce ilce_s) {
		ilceDao.ilceSil(ilce_s);
		ilceListele();
		ilce_s = new Ilce();
	}

	public String goToEdit(Ilce ilce) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put("ilce", ilce);

		return "ilceKaydet?faces-redirect=true";
	}

	public IlceDao getIlceDao() {
		return ilceDao;
	}

	public void ilceListele() {
		ilceList = ilceDao.hepsiniGetir();
	}

	public void setIlceDao(IlceDao ilceDao) {
		this.ilceDao = ilceDao;
	}

	public IlceSearch getIlceSearch() {
		return ilceSearch;
	}

	public void setIlceSearch(IlceSearch ilceSearch) {
		this.ilceSearch = ilceSearch;
	}

	public List<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(List<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

}