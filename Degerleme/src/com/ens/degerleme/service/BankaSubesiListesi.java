package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.BankaSubesiDao;
import com.ens.degerleme.dao.searchobject.BankaSubesiSearch;
import com.ens.degerleme.model.entity.BankaSubesi;

@ManagedBean(name = "bankaSubesiListesi")
@ViewScoped
public class BankaSubesiListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3840210599974081172L;

	@EJB
	private BankaSubesiDao bankaSubesiDao;

	private BankaSubesiSearch bankaSubesiSearch=new BankaSubesiSearch();

	private List<BankaSubesi> bankaSubesiList = new ArrayList<BankaSubesi>();

	public void listele() {
		bankaSubesiList = bankaSubesiDao.bankaSubesiGetir(bankaSubesiSearch);
	}

	public BankaSubesiSearch getBankaSubesiSearch() {
		return bankaSubesiSearch;
	}

	public void setBankaSubesiSearch(BankaSubesiSearch bankaSubesiSearch) {
		this.bankaSubesiSearch = bankaSubesiSearch;
	}

	public List<BankaSubesi> getBankaSubesiList() {
		return bankaSubesiList;
	}

	public void setBankaSubesiList(List<BankaSubesi> bankaSubesiList) {
		this.bankaSubesiList = bankaSubesiList;
	}
}
