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
import com.kayiyazilim.ekentsayim.dao.KsGucUnitesiDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.KsGucUnitesi;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.KsGucUnitesiSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "ksGucUnitesiService")
@ViewScoped
public class KsGucUnitesiService implements Serializable {

	private static final long serialVersionUID = 8696203957192527903L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private KsGucUnitesiDao ksGucUnitesiDao;

	private KsGucUnitesi ksGucUnitesi = new KsGucUnitesi();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<KsGucUnitesi> ksGucUnitesiList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return ksGucUnitesi.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private KsGucUnitesiSearch ksGucUnitesiSearch = new KsGucUnitesiSearch();

	@PostConstruct
	public void init() {
		ksGucUnitesi.setDurum(Durum.SAHADA);
		ksGucUnitesiList = ksGucUnitesiDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e7,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		ksGucUnitesiList = ksGucUnitesiDao.search(ksGucUnitesiSearch);
	}

	public void ekle() {
		KsGucUnitesi aranan = null;
		try {
			aranan = ksGucUnitesiDao.find(ksGucUnitesi.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			ksGucUnitesi.setGununTarihi(new Date());
			ksGucUnitesiDao.persist(ksGucUnitesi);
			if (ksGucUnitesiList == null)
				ksGucUnitesiList = new ArrayList<KsGucUnitesi>();
			ksGucUnitesiList.add(ksGucUnitesi);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			ksGucUnitesi = new KsGucUnitesi();
		}
	}

	public void guncelle(RowEditEvent event) {
		ksGucUnitesi = ((KsGucUnitesi) event.getObject());
		ksGucUnitesiDao.merge(ksGucUnitesi);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksGucUnitesi = new KsGucUnitesi();
	}

	public void sil() {
		if (ksGucUnitesi != null)
			ksGucUnitesiDao.remove(ksGucUnitesi);
		ksGucUnitesiList.remove(ksGucUnitesi);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksGucUnitesi = new KsGucUnitesi();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public KsGucUnitesi getKsGucUnitesi() {
		return ksGucUnitesi;
	}

	public void setKsGucUnitesi(KsGucUnitesi ksGucUnitesi) {
		this.ksGucUnitesi = ksGucUnitesi;
	}

	public List<KsGucUnitesi> getKsGucUnitesiList() {
		return ksGucUnitesiList;
	}

	public void setKsGucUnitesiList(List<KsGucUnitesi> ksGucUnitesiList) {
		this.ksGucUnitesiList = ksGucUnitesiList;
	}

	public KsGucUnitesiSearch getKsGucUnitesiSearch() {
		return ksGucUnitesiSearch;
	}

	public void setKsGucUnitesiSearch(KsGucUnitesiSearch ksGucUnitesiSearch) {
		this.ksGucUnitesiSearch = ksGucUnitesiSearch;
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
