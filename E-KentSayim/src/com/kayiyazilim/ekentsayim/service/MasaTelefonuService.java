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
import com.kayiyazilim.ekentsayim.dao.MasaTelefonuDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.MasaTelefonu;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.MasaTelefonuSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "masaTelefonuService")
@ViewScoped
public class MasaTelefonuService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private MasaTelefonuDao dao;

	private MasaTelefonu masaTelefonu = new MasaTelefonu();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<MasaTelefonu> masaTelefonuList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> masaTelefonTipiList = new ArrayList<SelectItem>();

	private MasaTelefonuSearch masaTelefonuSearch = new MasaTelefonuSearch();

	public void ara() {
		masaTelefonuList = dao.search(masaTelefonuSearch);
	}

	@PostConstruct
	public void init() {
		masaTelefonuList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e28,
				Alan.MASATELEFONTIPI)) {
			masaTelefonTipiList.add(new SelectItem(parametre.getDeger(),
					parametre.getDeger()));
		}

	}

	public void kaydet() {
		masaTelefonu.setGununTarihi(new Date());
		dao.persist(masaTelefonu);
		if (masaTelefonuList == null)
			masaTelefonuList = new ArrayList<MasaTelefonu>();
		masaTelefonuList.add(masaTelefonu);
		masaTelefonu = new MasaTelefonu();
	}

	public void guncelle(RowEditEvent event) {
		masaTelefonu = ((MasaTelefonu) event.getObject());
		dao.merge(masaTelefonu);
		masaTelefonu = new MasaTelefonu();
	}

	public void sil() {
		if (masaTelefonu != null)
			dao.remove(masaTelefonu);
		masaTelefonuList.remove(masaTelefonu);
		masaTelefonu = new MasaTelefonu();
	}

	public List<SelectItem> getMasaTelefonTipiList() {
		return masaTelefonTipiList;
	}

	public void setMasaTelefonTipiList(List<SelectItem> masaTelefonTipiList) {
		this.masaTelefonTipiList = masaTelefonTipiList;
	}

	public MasaTelefonu getLcd() {
		return masaTelefonu;
	}

	public void setLcd(MasaTelefonu masaTelefonu) {
		this.masaTelefonu = masaTelefonu;
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

	public List<MasaTelefonu> getLcdList() {
		return masaTelefonuList;
	}

	public void setLcdList(List<MasaTelefonu> masaTelefonuList) {
		this.masaTelefonuList = masaTelefonuList;
	}

	public MasaTelefonu getMasaTelefonu() {
		return masaTelefonu;
	}

	public void setMasaTelefonu(MasaTelefonu masaTelefonu) {
		this.masaTelefonu = masaTelefonu;
	}

	public List<MasaTelefonu> getMasaTelefonuList() {
		return masaTelefonuList;
	}

	public void setMasaTelefonuList(List<MasaTelefonu> masaTelefonuList) {
		this.masaTelefonuList = masaTelefonuList;
	}

	public MasaTelefonuSearch getMasaTelefonuSearch() {
		return masaTelefonuSearch;
	}

	public void setMasaTelefonuSearch(MasaTelefonuSearch masaTelefonuSearch) {
		this.masaTelefonuSearch = masaTelefonuSearch;
	}

}
