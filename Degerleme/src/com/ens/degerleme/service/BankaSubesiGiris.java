package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.BankaDao;
import com.ens.degerleme.dao.BankaSubesiDao;
import com.ens.degerleme.dao.searchobject.BankaSearch;
import com.ens.degerleme.model.entity.Banka;
import com.ens.degerleme.model.entity.BankaSubesi;

@ManagedBean(name = "bankaSubesiGirisi")
@ViewScoped
public class BankaSubesiGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BankaSubesiDao bankaSubesiDao;
	@EJB
	private BankaDao bankaDao;

	private BankaSubesi bankaSubesi = new BankaSubesi();

	private Banka secilenBanka;

	private BankaSearch bankaSearch = new BankaSearch();

	private List<Banka> bankaList = new ArrayList<Banka>();

	@PostConstruct
	public void init() {
		bankaList = bankaDao.bankaGetir(bankaSearch);
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String bankaSubesiId = params.get("bankaSubesiId");
		if (bankaSubesiId != null && !!"".equals(bankaSubesiId)) {
			bankaSubesi = bankaSubesiDao.bankaSubesiGetir(Integer
					.valueOf(bankaSubesiId));
			secilenBanka = bankaSubesi.getBanka();
		}

	}

	public void kaydet() {
		bankaSubesi.setBanka(secilenBanka);

		if (bankaSubesi.getId() == null) {
			bankaSubesiDao.bankaSubesiKaydet(bankaSubesi);
		} else {
			bankaSubesi = bankaSubesiDao.bankaSubesiGuncelle(bankaSubesi);
		}
	}

	public BankaSubesi getBankaSubesi() {
		return bankaSubesi;
	}

	public void setBankaSubesi(BankaSubesi bankaSubesi) {
		this.bankaSubesi = bankaSubesi;
	}

	public Banka getSecilenBanka() {
		return secilenBanka;
	}

	public void setSecilenBanka(Banka secilenBanka) {
		this.secilenBanka = secilenBanka;
	}

	public List<Banka> getBankaList() {
		return bankaList;
	}

	public void setBankaList(List<Banka> bankaList) {
		this.bankaList = bankaList;
	}

	public BankaSubesiDao getBankaSubesiDao() {
		return bankaSubesiDao;
	}

	public void setBankaSubesiDao(BankaSubesiDao bankaSubesiDao) {
		this.bankaSubesiDao = bankaSubesiDao;
	}

	public BankaDao getBankaDao() {
		return bankaDao;
	}

	public void setBankaDao(BankaDao bankaDao) {
		this.bankaDao = bankaDao;
	}

	public BankaSearch getBankaSearch() {
		return bankaSearch;
	}

	public void setBankaSearch(BankaSearch bankaSearch) {
		this.bankaSearch = bankaSearch;
	}
}
