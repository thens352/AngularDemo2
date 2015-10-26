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
import com.kayiyazilim.ekentsayim.dao.KabinetDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kabinet;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.search.KabinetSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "kabinetService")
@ViewScoped
public class KabinetService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private KabinetDao dao;

	private Kabinet kabinet = new Kabinet();

	private KabinetSearch kabinetSearch = new KabinetSearch();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Kabinet> kabinetList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@PostConstruct
	public void init() {
		kabinetList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		kabinetList = dao.search(kabinetSearch);
	}

	public void kaydet() {
		kabinet.setGununTarihi(new Date());
		dao.persist(kabinet);
		if (kabinetList == null)
			kabinetList = new ArrayList<Kabinet>();
		kabinetList.add(kabinet);
		kabinet = new Kabinet();
	}

	public void guncelle(RowEditEvent event) {
		kabinet = ((Kabinet) event.getObject());
		dao.merge(kabinet);
		kabinet = new Kabinet();
	}

	public void sil() {
		if (kabinet != null)
			dao.remove(kabinet);
		kabinetList.remove(kabinet);
		kabinet = new Kabinet();
	}

	public Kabinet getLcd() {
		return kabinet;
	}

	public void setLcd(Kabinet kabinet) {
		this.kabinet = kabinet;
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

	public List<Kabinet> getLcdList() {
		return kabinetList;
	}

	public void setLcdList(List<Kabinet> kabinetList) {
		this.kabinetList = kabinetList;
	}

	public Kabinet getKabinet() {
		return kabinet;
	}

	public void setKabinet(Kabinet kabinet) {
		this.kabinet = kabinet;
	}

	public List<Kabinet> getKabinetList() {
		return kabinetList;
	}

	public void setKabinetList(List<Kabinet> kabinetList) {
		this.kabinetList = kabinetList;
	}

	public KabinetSearch getKabinetSearch() {
		return kabinetSearch;
	}

	public void setKabinetSearch(KabinetSearch kabinetSearch) {
		this.kabinetSearch = kabinetSearch;
	}

}
