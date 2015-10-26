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
import com.kayiyazilim.ekentsayim.dao.LCDDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.LCD;
import com.kayiyazilim.ekentsayim.model.search.LCDSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "lcdService")
@ViewScoped
public class LCDService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private LCDDao dao;

	private LCD lcd = new LCD();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<LCD> lcdList;

	private LCDSearch lcdSearch = new LCDSearch();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		lcdList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		lcdList = dao.search(lcdSearch);
	}

	public void kaydet() {
		lcd.setGununTarihi(new Date());
		dao.persist(lcd);
		if (lcdList == null)
			lcdList = new ArrayList<LCD>();
		lcdList.add(lcd);
		lcd = new LCD();
	}

	public void guncelle(RowEditEvent event) {
		lcd = ((LCD) event.getObject());
		dao.merge(lcd);
		lcd = new LCD();
	}

	public void sil() {
		if (lcd != null)
			dao.remove(lcd);
		lcdList.remove(lcd);
		lcd = new LCD();
	}

	public LCD getLcd() {
		return lcd;
	}

	public void setLcd(LCD lcd) {
		this.lcd = lcd;
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

	public List<LCD> getLcdList() {
		return lcdList;
	}

	public void setLcdList(List<LCD> lcdList) {
		this.lcdList = lcdList;
	}

	public LCDSearch getLcdSearch() {
		return lcdSearch;
	}

	public void setLcdSearch(LCDSearch lcdSearch) {
		this.lcdSearch = lcdSearch;
	}

}
