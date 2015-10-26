package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.NcSwitchDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.NcSwitch;
import com.kayiyazilim.ekentsayim.model.search.NcSwitchSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "ncSwitchService")
@ViewScoped
public class NcSwitchService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private NcSwitchDao dao;

	private NcSwitch ncSwitch = new NcSwitch();

	private List<Kullanici> kullaniciList;

	private NcSwitchSearch ncSwitchSearch = new NcSwitchSearch();

	private List<Bolge> bolgeList;

	private List<NcSwitch> ncSwitchList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		ncSwitchList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		ncSwitchList = dao.search(ncSwitchSearch);
	}

	public void kaydet() {
		ncSwitch.setGununTarihi(new Date());
		dao.persist(ncSwitch);
		if (ncSwitchList == null)
			ncSwitchList = new ArrayList<NcSwitch>();
		ncSwitchList.add(ncSwitch);
		ncSwitch = new NcSwitch();
	}

	public void guncelle(RowEditEvent event) {
		ncSwitch = ((NcSwitch) event.getObject());
		dao.merge(ncSwitch);
		ncSwitch = new NcSwitch();
	}

	public void sil() {
		if (ncSwitch != null)
			dao.remove(ncSwitch);
		ncSwitchList.remove(ncSwitch);
		ncSwitch = new NcSwitch();
	}

	public NcSwitch getNcSwitch() {
		return ncSwitch;
	}

	public void setNcSwitch(NcSwitch ncSwitch) {
		this.ncSwitch = ncSwitch;
	}

	public NcSwitchSearch getNcSwitchSearch() {
		return ncSwitchSearch;
	}

	public void setNcSwitchSearch(NcSwitchSearch ncSwitchSearch) {
		this.ncSwitchSearch = ncSwitchSearch;
	}

	public List<NcSwitch> getNcSwitchList() {
		return ncSwitchList;
	}

	public void setNcSwitchList(List<NcSwitch> ncSwitchList) {
		this.ncSwitchList = ncSwitchList;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public List<Bolge> getBolgeList() {
		return bolgeList;
	}

	public void setBolgeList(List<Bolge> bolgeList) {
		this.bolgeList = bolgeList;
	}

}
