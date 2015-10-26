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
import com.kayiyazilim.ekentsayim.dao.NcModemDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.NcModem;
import com.kayiyazilim.ekentsayim.model.search.NcModemSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "ncModemService")
@ViewScoped
public class NcModemService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private NcModemDao dao;

	private NcModem ncModem = new NcModem();

	private List<Kullanici> kullaniciList;

	private NcModemSearch ncModemSearch = new NcModemSearch();

	private List<Bolge> bolgeList;

	private List<NcModem> ncModemList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		ncModemList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

	}

	public void ara() {
		ncModemList = dao.search(ncModemSearch);
	}

	public void kaydet() {
		ncModem.setGununTarihi(new Date());
		dao.persist(ncModem);
		if (ncModemList == null)
			ncModemList = new ArrayList<NcModem>();
		ncModemList.add(ncModem);
		ncModem = new NcModem();
	}

	public void guncelle(RowEditEvent event) {
		ncModem = ((NcModem) event.getObject());
		dao.merge(ncModem);
		ncModem = new NcModem();
	}

	public void sil() {
		if (ncModem != null)
			dao.remove(ncModem);
		ncModemList.remove(ncModem);
		ncModem = new NcModem();
	}

	public NcModem getNcModem() {
		return ncModem;
	}

	public void setNcModem(NcModem ncModem) {
		this.ncModem = ncModem;
	}

	public NcModemSearch getNcModemSearch() {
		return ncModemSearch;
	}

	public void setNcModemSearch(NcModemSearch ncModemSearch) {
		this.ncModemSearch = ncModemSearch;
	}

	public List<NcModem> getNcModemList() {
		return ncModemList;
	}

	public void setNcModemList(List<NcModem> ncModemList) {
		this.ncModemList = ncModemList;
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
