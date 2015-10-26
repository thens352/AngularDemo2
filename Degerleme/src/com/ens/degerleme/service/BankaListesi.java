package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.BankaDao;
import com.ens.degerleme.dao.searchobject.BankaSearch;
import com.ens.degerleme.model.entity.Banka;

@ManagedBean(name = "bankaListesi")
@ViewScoped
public class BankaListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1250471210236167419L;
	@EJB
	private BankaDao bankaDao;

	private BankaSearch bankaSearch = new BankaSearch();

	private List<Banka> bankaList = new ArrayList<Banka>();

	public BankaSearch getBankaSearch() {
		return bankaSearch;
	}

	public void setBankaSearch(BankaSearch bankaSearch) {
		this.bankaSearch = bankaSearch;
	}

	public List<Banka> getBankaList() {
		return bankaList;
	}

	public void setBankaList(List<Banka> bankaList) {
		this.bankaList = bankaList;
	}

	public void listele() {
		bankaList = bankaDao.bankaGetir(bankaSearch);
	}
}
