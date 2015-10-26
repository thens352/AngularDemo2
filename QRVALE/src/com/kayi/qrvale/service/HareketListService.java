package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AracDao;
import com.kayi.qrvale.dao.HareketDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.dao.search.HareketSearch;
import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Hareket;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Vale;

@ManagedBean(name = "hareketListS")
@ViewScoped
public class HareketListService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8427278105605199303L;

	@EJB
	private HareketDao hareketDao;

	@EJB
	private AracDao aracDao;

	@EJB
	private ValeDao valeDao;

	@EJB
	private IsletmeDao isletmeDao;

	private Isletme isletme;

	private Hareket hareket = new Hareket();

	private HareketSearch hareketSearch = new HareketSearch();

	private List<Hareket> hareketList = new ArrayList<Hareket>();

	private List<Arac> aracList = new ArrayList<Arac>();

	private List<Vale> valeList = new ArrayList<Vale>();

	@PostConstruct
	public void init() {
		try {
			isletme = isletmeDao.IsletmeGetirById((Integer) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("isletmeID"));
		} catch (Exception e) {
			LoginService.sessionDoldu();
		}
		aracList = aracDao.AracGetirByIsletmeId(isletme.getId());
		valeList = valeDao.valeGetirByIsletmeId(isletme.getId());
	}

	public void hareketAra() {
		hareketList = hareketDao.HareketGetir(hareketSearch);
	}

	public Hareket getHareket() {
		return hareket;
	}

	public void setHareket(Hareket hareket) {
		this.hareket = hareket;
	}

	public HareketSearch getHareketSearch() {
		return hareketSearch;
	}

	public void setHareketSearch(HareketSearch hareketSearch) {
		this.hareketSearch = hareketSearch;
	}

	public List<Hareket> getHareketList() {
		return hareketList;
	}

	public void setHareketList(List<Hareket> hareketList) {
		this.hareketList = hareketList;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public List<Vale> getValeList() {
		return valeList;
	}

	public void setValeList(List<Vale> valeList) {
		this.valeList = valeList;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}
}
