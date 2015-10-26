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
import com.kayiyazilim.ekentsayim.dao.PDKSDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.PDKS;
import com.kayiyazilim.ekentsayim.model.search.PDKSSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "pdksService")
@ViewScoped
public class PDKSService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private PDKSDao dao;

	private PDKS pdks = new PDKS();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<PDKS> pdksList;

	private PDKSSearch pdksSearch = new PDKSSearch();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		pdksList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		pdksList = dao.search(pdksSearch);
	}

	public void kaydet() {
		pdks.setGununTarihi(new Date());
		dao.persist(pdks);
		if (pdksList == null)
			pdksList = new ArrayList<PDKS>();
		pdksList.add(pdks);
		pdks = new PDKS();
	}

	public void guncelle(RowEditEvent event) {
		pdks = ((PDKS) event.getObject());
		dao.merge(pdks);
		pdks = new PDKS();
	}

	public void sil() {
		if (pdks != null)
			dao.remove(pdks);
		pdksList.remove(pdks);
		pdks = new PDKS();
	}

	public PDKS getLcd() {
		return pdks;
	}

	public void setLcd(PDKS pdks) {
		this.pdks = pdks;
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

	public List<PDKS> getLcdList() {
		return pdksList;
	}

	public void setLcdList(List<PDKS> pdksList) {
		this.pdksList = pdksList;
	}

	public PDKS getPdks() {
		return pdks;
	}

	public void setPdks(PDKS pdks) {
		this.pdks = pdks;
	}

	public List<PDKS> getPdksList() {
		return pdksList;
	}

	public void setPdksList(List<PDKS> pdksList) {
		this.pdksList = pdksList;
	}

	public PDKSSearch getPdksSearch() {
		return pdksSearch;
	}

	public void setPdksSearch(PDKSSearch pdksSearch) {
		this.pdksSearch = pdksSearch;
	}

}
