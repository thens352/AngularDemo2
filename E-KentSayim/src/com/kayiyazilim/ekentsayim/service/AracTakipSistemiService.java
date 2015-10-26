package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.AracTakipSistemiDao;
import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.AracTakipSistemi;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.AracTakipSistemiSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "aracTakipSistemiService")
@ViewScoped
public class AracTakipSistemiService implements Serializable {

	private static final long serialVersionUID = -2569153500900286383L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private AracTakipSistemiDao aracTakipSistemiDao;

	private AracTakipSistemi aracTakipSistemi = new AracTakipSistemi();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<AracTakipSistemi> aracTakipSistemiList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return aracTakipSistemi.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private List<SelectItem> hizmetVerenFirmaList = new ArrayList<SelectItem>();

	private AracTakipSistemiSearch aracTakipSistemiSearch = new AracTakipSistemiSearch();

	@PostConstruct
	public void init() {
		aracTakipSistemi.setDurum(Durum.SAHADA);
		aracTakipSistemiList = aracTakipSistemiDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e10,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e10,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e10,
				Alan.HIZMETVERENFIRMA)) {
			hizmetVerenFirmaList.add(new SelectItem(parametre.getDeger(),
					parametre.getDeger()));
		}
	}

	public void ara() {
		aracTakipSistemiList = aracTakipSistemiDao
				.search(aracTakipSistemiSearch);
	}

	public void ekle() {
		AracTakipSistemi aranan = null;
		try {
			aranan = aracTakipSistemiDao.find(aracTakipSistemi.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			aracTakipSistemi.setGununTarihi(new Date());
			aracTakipSistemiDao.persist(aracTakipSistemi);
			if (aracTakipSistemiList == null)
				aracTakipSistemiList = new ArrayList<AracTakipSistemi>();
			aracTakipSistemiList.add(aracTakipSistemi);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			aracTakipSistemi = new AracTakipSistemi();
		}
	}

	public void guncelle(RowEditEvent event) {
		aracTakipSistemi = ((AracTakipSistemi) event.getObject());
		aracTakipSistemiDao.merge(aracTakipSistemi);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		aracTakipSistemi = new AracTakipSistemi();
	}

	public void sil() {
		if (aracTakipSistemi != null)
			aracTakipSistemiDao.remove(aracTakipSistemi);
		aracTakipSistemiList.remove(aracTakipSistemi);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		aracTakipSistemi = new AracTakipSistemi();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public List<SelectItem> getModelList() {
		return modelList;
	}

	public void setModelList(List<SelectItem> modelList) {
		this.modelList = modelList;
	}

	public List<SelectItem> getHizmetVerenFirmaList() {
		return hizmetVerenFirmaList;
	}

	public void setHizmetVerenFirmaList(List<SelectItem> hizmetVerenFirmaList) {
		this.hizmetVerenFirmaList = hizmetVerenFirmaList;
	}

	public AracTakipSistemiSearch getAracTakipSistemiSearch() {
		return aracTakipSistemiSearch;
	}

	public void setAracTakipSistemiSearch(
			AracTakipSistemiSearch aracTakipSistemiSearch) {
		this.aracTakipSistemiSearch = aracTakipSistemiSearch;
	}

	public AracTakipSistemi getAracTakipSistemi() {
		return aracTakipSistemi;
	}

	public void setAracTakipSistemi(AracTakipSistemi aracTakipSistemi) {
		this.aracTakipSistemi = aracTakipSistemi;
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

	public List<AracTakipSistemi> getAracTakipSistemiList() {
		return aracTakipSistemiList;
	}

	public void setAracTakipSistemiList(
			List<AracTakipSistemi> aracTakipSistemiList) {
		this.aracTakipSistemiList = aracTakipSistemiList;
	}

}
