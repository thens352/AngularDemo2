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
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.dao.SunucuDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.entity.Sunucu;
import com.kayiyazilim.ekentsayim.model.search.SunucuSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "sunucuService")
@ViewScoped
public class SunucuService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715047750934446741L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private SunucuDao dao;

	private Sunucu sunucu = new Sunucu();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Sunucu> sunucuList;

	private SunucuSearch sunucuSearch = new SunucuSearch();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> uzerindekiIsletimSistemiList = new ArrayList<SelectItem>();

	@PostConstruct
	public void init() {
		sunucuList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e17,
				Alan.UZERINDEKIISLETIMSISTEMI)) {
			uzerindekiIsletimSistemiList.add(new SelectItem(parametre
					.getDeger(), parametre.getDeger()));
		}
	}

	public void ara() {
		sunucuList = dao.search(sunucuSearch);
	}

	public void kaydet() {
		sunucu.setGununTarihi(new Date());
		dao.persist(sunucu);
		if (sunucuList == null)
			sunucuList = new ArrayList<Sunucu>();
		sunucuList.add(sunucu);
		sunucu = new Sunucu();
	}

	public void guncelle(RowEditEvent event) {
		sunucu = ((Sunucu) event.getObject());
		dao.merge(sunucu);
		sunucu = new Sunucu();
	}

	public void sil() {
		if (sunucu != null)
			dao.remove(sunucu);
		sunucuList.remove(sunucu);
		sunucu = new Sunucu();
	}

	public List<SelectItem> getUzerindekiIsletimSistemiList() {
		return uzerindekiIsletimSistemiList;
	}

	public void setUzerindekiIsletimSistemiList(
			List<SelectItem> uzerindekiIsletimSistemiList) {
		this.uzerindekiIsletimSistemiList = uzerindekiIsletimSistemiList;
	}

	public Sunucu getSunucu() {
		return sunucu;
	}

	public void setSunucu(Sunucu sunucu) {
		this.sunucu = sunucu;
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

	public List<Sunucu> getSunucuList() {
		return sunucuList;
	}

	public void setSunucuList(List<Sunucu> sunucuList) {
		this.sunucuList = sunucuList;
	}

	public SunucuSearch getSunucuSearch() {
		return sunucuSearch;
	}

	public void setSunucuSearch(SunucuSearch sunucuSearch) {
		this.sunucuSearch = sunucuSearch;
	}
}
