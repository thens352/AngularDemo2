package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.kayi.qrvale.dao.SokakDao;
import com.kayi.qrvale.dao.search.SokakSearch;
import com.kayi.qrvale.model.entity.Sokak;

@ManagedBean(name = "sokakListService")
@ViewScoped
public class SokakListService implements Serializable {

	private static final long serialVersionUID = 3900038245199066248L;
	@EJB
	private SokakDao sokakDao;

	SokakSearch sokakSearch = new SokakSearch();

	List<Sokak> sokakList = new ArrayList<Sokak>();

	@PostConstruct
	public void init() {
		sokakList = sokakDao.sokakGetirByOrnek(sokakSearch);
	}

	public void sokakSil(Sokak sokak_s) {
		sokakDao.sokakSil(sokak_s);
		sokakListele();
		sokak_s = new Sokak();
	}

	public void sokakListele() {

		sokakList = sokakDao.hepsiniGetir();
	}

	public String goToEdit(Sokak sokak) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put("sokak", sokak);
		return "sokakKaydet?faces-redirect=true";
	}

	public SokakDao getSokakDao() {
		return sokakDao;
	}

	public void setSokakDao(SokakDao sokakDao) {
		this.sokakDao = sokakDao;
	}

	public SokakSearch getSokakSearch() {
		return sokakSearch;
	}

	public void setSokakSearch(SokakSearch sokakSearch) {
		this.sokakSearch = sokakSearch;
	}

	public List<Sokak> getSokakList() {
		return sokakList;
	}

	public void setSokakList(List<Sokak> sokakList) {
		this.sokakList = sokakList;
	}

}
