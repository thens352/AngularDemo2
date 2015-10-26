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
import com.kayiyazilim.ekentsayim.dao.TelefonSantraliDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.TelefonSantrali;
import com.kayiyazilim.ekentsayim.model.search.TelefonSantraliSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "telefonSantraliService")
@ViewScoped
public class TelefonSantraliService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private TelefonSantraliDao dao;

	private TelefonSantrali telefonSantrali = new TelefonSantrali();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private TelefonSantraliSearch telefonSantraliSearch = new TelefonSantraliSearch();

	private List<TelefonSantrali> telefonSantraliList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		telefonSantraliList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		telefonSantraliList = dao.search(telefonSantraliSearch);
	}

	public void kaydet() {
		telefonSantrali.setGununTarihi(new Date());
		dao.persist(telefonSantrali);
		if (telefonSantraliList == null)
			telefonSantraliList = new ArrayList<TelefonSantrali>();
		telefonSantraliList.add(telefonSantrali);
		telefonSantrali = new TelefonSantrali();
	}

	public void guncelle(RowEditEvent event) {
		telefonSantrali = ((TelefonSantrali) event.getObject());
		dao.merge(telefonSantrali);
		telefonSantrali = new TelefonSantrali();
	}

	public void sil() {
		if (telefonSantrali != null)
			dao.remove(telefonSantrali);
		telefonSantraliList.remove(telefonSantrali);
		telefonSantrali = new TelefonSantrali();
	}

	public TelefonSantrali getLcd() {
		return telefonSantrali;
	}

	public void setLcd(TelefonSantrali telefonSantrali) {
		this.telefonSantrali = telefonSantrali;
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

	public List<TelefonSantrali> getLcdList() {
		return telefonSantraliList;
	}

	public void setLcdList(List<TelefonSantrali> telefonSantraliList) {
		this.telefonSantraliList = telefonSantraliList;
	}

	public TelefonSantrali getTelefonSantrali() {
		return telefonSantrali;
	}

	public void setTelefonSantrali(TelefonSantrali telefonSantrali) {
		this.telefonSantrali = telefonSantrali;
	}

	public List<TelefonSantrali> getTelefonSantraliList() {
		return telefonSantraliList;
	}

	public void setTelefonSantraliList(List<TelefonSantrali> telefonSantraliList) {
		this.telefonSantraliList = telefonSantraliList;
	}

	public TelefonSantraliSearch getTelefonSantraliSearch() {
		return telefonSantraliSearch;
	}

	public void setTelefonSantraliSearch(
			TelefonSantraliSearch telefonSantraliSearch) {
		this.telefonSantraliSearch = telefonSantraliSearch;
	}

}
