package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.FirmaDao;
import com.ens.degerleme.dao.searchobject.FirmaSearch;
import com.ens.degerleme.model.entity.Firma;

@ManagedBean(name="firmaListesi")
@ViewScoped
public class FirmaListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 850832900257525781L;
	
	@EJB
	
	private FirmaDao firmaDao;
	
	private FirmaSearch firmaSearch=new FirmaSearch();
	
	private List<Firma> firmaList=new ArrayList<Firma>();
	
	public void listele(){
		setFirmaList(firmaDao.firmaGetir(firmaSearch));
	}

	public List<Firma> getFirmaList() {
		return firmaList;
	}

	public void setFirmaList(List<Firma> firmaList) {
		this.firmaList = firmaList;
	}

	public FirmaSearch getFirmaSearch() {
		return firmaSearch;
	}

	public void setFirmaSearch(FirmaSearch firmaSearch) {
		this.firmaSearch = firmaSearch;
	}
	

}
