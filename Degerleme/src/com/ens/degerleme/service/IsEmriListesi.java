package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.BankaDao;
import com.ens.degerleme.dao.FirmaDao;
import com.ens.degerleme.dao.IsEmriDao;
import com.ens.degerleme.dao.KisiDao;
import com.ens.degerleme.dao.UserDao;
import com.ens.degerleme.dao.searchobject.BankaSearch;
import com.ens.degerleme.dao.searchobject.FirmaSearch;
import com.ens.degerleme.dao.searchobject.IsEmriSearch;
import com.ens.degerleme.dao.searchobject.KisiSearch;
import com.ens.degerleme.dao.searchobject.UserSearch;
import com.ens.degerleme.model.entity.Banka;
import com.ens.degerleme.model.entity.Firma;
import com.ens.degerleme.model.entity.IsEmri;
import com.ens.degerleme.model.entity.Kisi;
import com.ens.degerleme.model.entity.User;
import com.ens.degerleme.model.type.IsEmriDurumu;

@ManagedBean(name = "isEmriListesi")
@ViewScoped
public class IsEmriListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3316846081832144980L;

	@EJB
	private IsEmriDao isEmriDao;
	@EJB
	private BankaDao bankaDao;
	@EJB
	private FirmaDao firmaDao;
	@EJB
	private UserDao userDao;
	@EJB
	private KisiDao kisiDao;

	private boolean kisiMi = true;

	private boolean firmaMi = true;

	private List<Kisi> kisiList = new ArrayList<Kisi>();

	private List<User> userList = new ArrayList<User>();

	private List<Firma> firmaList = new ArrayList<Firma>();

	private List<Banka> bankaList = new ArrayList<Banka>();

	private IsEmriSearch isEmriSearch = new IsEmriSearch();

	private List<IsEmri> isEmriList = new ArrayList<IsEmri>();

	public IsEmriDurumu[] getIsEmriDurumlari() {
		return IsEmriDurumu.values();
	}

	public void listele() {
		isEmriList = isEmriDao.isEmriGetir(isEmriSearch);

		if (isEmriSearch.getKisi() == null) {
			setKisiMi(false);
		} else {
			setKisiMi(true);
		}
		if (isEmriSearch.getFirma() == null) {
			setFirmaMi(false);
		} else {
			setFirmaMi(true);
		}
		if (firmaMi == kisiMi) {
			setFirmaMi(true);
			setKisiMi(true);
		}
	}

	@PostConstruct
	public void init() {
		bankaList = bankaDao.bankaGetir(new BankaSearch());
		firmaList = firmaDao.firmaGetir(new FirmaSearch());
		userList = userDao.userGetir(new UserSearch());
		kisiList = kisiDao.kisiGetir(new KisiSearch());
	}

	public List<Kisi> getKisiList() {
		return kisiList;
	}

	public void setKisiList(List<Kisi> kisiList) {
		this.kisiList = kisiList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Firma> getFirmaList() {
		return firmaList;
	}

	public void setFirmaList(List<Firma> firmaList) {
		this.firmaList = firmaList;
	}

	public List<Banka> getBankaList() {
		return bankaList;
	}

	public void setBankaList(List<Banka> bankaList) {
		this.bankaList = bankaList;
	}

	public IsEmriSearch getIsEmriSearch() {
		return isEmriSearch;
	}

	public void setIsEmriSearch(IsEmriSearch isEmriSearch) {
		this.isEmriSearch = isEmriSearch;
	}

	public List<IsEmri> getIsEmriList() {
		return isEmriList;
	}

	public void setIsEmriList(List<IsEmri> isEmriList) {
		this.isEmriList = isEmriList;
	}

	public boolean isKisiMi() {
		return kisiMi;
	}

	public void setKisiMi(boolean kisiMi) {
		this.kisiMi = kisiMi;
	}

	public boolean isFirmaMi() {
		return firmaMi;
	}

	public void setFirmaMi(boolean firmaMi) {
		this.firmaMi = firmaMi;
	}

}
