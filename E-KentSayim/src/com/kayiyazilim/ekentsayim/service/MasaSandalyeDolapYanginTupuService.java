package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.Masa_Sandalye_Dolap_YanginTupuDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Masa_Sandalye_Dolap_YanginTupu;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.Masa_Sandalye_Dolap_YanginTupuSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "masaSandalyeDolapYanginTupuService")
@ViewScoped
public class MasaSandalyeDolapYanginTupuService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private Masa_Sandalye_Dolap_YanginTupuDao dao;

	private Masa_Sandalye_Dolap_YanginTupu masaSandalyeDolapYanginTupu = new Masa_Sandalye_Dolap_YanginTupu();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Masa_Sandalye_Dolap_YanginTupu> masaSandalyeDolapYanginTupuList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> cesitList = new ArrayList<SelectItem>();

	private Masa_Sandalye_Dolap_YanginTupuSearch masaSandalyeDolapYanginTupuSearch = new Masa_Sandalye_Dolap_YanginTupuSearch();

	public void ara() {
		masaSandalyeDolapYanginTupuList = dao
				.search(masaSandalyeDolapYanginTupuSearch);
	}

	@PostConstruct
	public void init() {
		masaSandalyeDolapYanginTupuList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e27,
				Alan.CESIT)) {
			cesitList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

	}

	public void kaydet() {
		masaSandalyeDolapYanginTupu.setGununTarihi(new Date());
		dao.persist(masaSandalyeDolapYanginTupu);
		if (masaSandalyeDolapYanginTupuList == null)
			masaSandalyeDolapYanginTupuList = new ArrayList<Masa_Sandalye_Dolap_YanginTupu>();
		masaSandalyeDolapYanginTupuList.add(masaSandalyeDolapYanginTupu);
		masaSandalyeDolapYanginTupu = new Masa_Sandalye_Dolap_YanginTupu();
	}

	public void guncelle(RowEditEvent event) {
		masaSandalyeDolapYanginTupu = ((Masa_Sandalye_Dolap_YanginTupu) event
				.getObject());
		dao.merge(masaSandalyeDolapYanginTupu);
		masaSandalyeDolapYanginTupu = new Masa_Sandalye_Dolap_YanginTupu();
	}

	public void sil() {
		if (masaSandalyeDolapYanginTupu != null)
			dao.remove(masaSandalyeDolapYanginTupu);
		masaSandalyeDolapYanginTupuList.remove(masaSandalyeDolapYanginTupu);
		masaSandalyeDolapYanginTupu = new Masa_Sandalye_Dolap_YanginTupu();
	}

	public List<SelectItem> getCesitList() {
		return cesitList;
	}

	public void setCesitList(List<SelectItem> cesitList) {
		this.cesitList = cesitList;
	}

	public Masa_Sandalye_Dolap_YanginTupu getLcd() {
		return masaSandalyeDolapYanginTupu;
	}

	public void setLcd(
			Masa_Sandalye_Dolap_YanginTupu masaSandalyeDolapYanginTupu) {
		this.masaSandalyeDolapYanginTupu = masaSandalyeDolapYanginTupu;
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

	public List<Masa_Sandalye_Dolap_YanginTupu> getLcdList() {
		return masaSandalyeDolapYanginTupuList;
	}

	public void setLcdList(
			List<Masa_Sandalye_Dolap_YanginTupu> masaSandalyeDolapYanginTupuList) {
		this.masaSandalyeDolapYanginTupuList = masaSandalyeDolapYanginTupuList;
	}

	public Masa_Sandalye_Dolap_YanginTupu getMasaSandalyeDolapYanginTupu() {
		return masaSandalyeDolapYanginTupu;
	}

	public void setMasaSandalyeDolapYanginTupu(
			Masa_Sandalye_Dolap_YanginTupu masaSandalyeDolapYanginTupu) {
		this.masaSandalyeDolapYanginTupu = masaSandalyeDolapYanginTupu;
	}

	public List<Masa_Sandalye_Dolap_YanginTupu> getMasaSandalyeDolapYanginTupuList() {
		return masaSandalyeDolapYanginTupuList;
	}

	public void setMasaSandalyeDolapYanginTupuList(
			List<Masa_Sandalye_Dolap_YanginTupu> masaSandalyeDolapYanginTupuList) {
		this.masaSandalyeDolapYanginTupuList = masaSandalyeDolapYanginTupuList;
	}

	public Masa_Sandalye_Dolap_YanginTupuSearch getMasaSandalyeDolapYanginTupuSearch() {
		return masaSandalyeDolapYanginTupuSearch;
	}

	public void setMasaSandalyeDolapYanginTupuSearch(
			Masa_Sandalye_Dolap_YanginTupuSearch masaSandalyeDolapYanginTupuSearch) {
		this.masaSandalyeDolapYanginTupuSearch = masaSandalyeDolapYanginTupuSearch;
	}

}
