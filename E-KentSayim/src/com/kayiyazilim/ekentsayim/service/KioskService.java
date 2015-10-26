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

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KioskDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kiosk;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.KioskSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "kioskService")
@ViewScoped
public class KioskService implements Serializable {

	private static final long serialVersionUID = 8057426637668356833L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private KioskDao kioskDao;

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Kiosk> kioskList;

	private Kiosk kiosk = new Kiosk();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return kiosk.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private KioskSearch kioskSearch = new KioskSearch();

	@PostConstruct
	public void init() {
		kiosk.setDurum(Durum.SAHADA);
		kioskList = kioskDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e3,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e3,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		kioskList = kioskDao.search(kioskSearch);
	}

	public void ekle() {
		Kiosk aranan = null;
		try {
			aranan = kioskDao.find(kiosk.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			kiosk.setGununTarihi(new Date());
			kioskDao.persist(kiosk);
			if (kioskList == null)
				kioskList = new ArrayList<Kiosk>();
			kioskList.add(kiosk);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			kiosk = new Kiosk();

		}
	}

	public void guncelle(RowEditEvent event) {
		kiosk = ((Kiosk) event.getObject());
		kioskDao.merge(kiosk);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		kiosk = new Kiosk();
	}

	public void sil() {
		if (kiosk != null)
			kioskDao.remove(kiosk);
		kioskList.remove(kiosk);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		kiosk = new Kiosk();

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

	public KioskSearch getKioskSearch() {
		return kioskSearch;
	}

	public void setKioskSearch(KioskSearch kioskSearch) {
		this.kioskSearch = kioskSearch;
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

	public List<Kiosk> getKioskList() {
		return kioskList;
	}

	public void setKioskList(List<Kiosk> kioskList) {
		this.kioskList = kioskList;
	}

	public Kiosk getKiosk() {
		return kiosk;
	}

	public void setKiosk(Kiosk kiosk) {
		this.kiosk = kiosk;
	}

}
