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
import com.kayiyazilim.ekentsayim.dao.KsNVRDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.KsNVR;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.KsNVRSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "ksNVRService")
@ViewScoped
public class KsNVRService implements Serializable {

	private static final long serialVersionUID = 8696203957192527903L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private KsNVRDao ksNVRDao;

	private KsNVR ksNVR = new KsNVR();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<KsNVR> ksNVRSList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return ksNVR.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private KsNVRSearch ksNVRSearch = new KsNVRSearch();

	@PostConstruct
	public void init() {
		ksNVR.setDurum(Durum.SAHADA);
		ksNVRSList = ksNVRDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e6,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		ksNVRSList = ksNVRDao.search(ksNVRSearch);
	}

	public void ekle() {
		KsNVR aranan = null;
		try {
			aranan = ksNVRDao.find(ksNVR.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			ksNVR.setGununTarihi(new Date());
			ksNVRDao.persist(ksNVR);
			if (ksNVRSList == null)
				ksNVRSList = new ArrayList<KsNVR>();
			ksNVRSList.add(ksNVR);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			ksNVR = new KsNVR();
		}
	}

	public void guncelle(RowEditEvent event) {
		ksNVR = ((KsNVR) event.getObject());
		ksNVRDao.merge(ksNVR);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksNVR = new KsNVR();
	}

	public void sil() {
		if (ksNVR != null)
			ksNVRDao.remove(ksNVR);
		ksNVRSList.remove(ksNVR);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksNVR = new KsNVR();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public KsNVRSearch getKsNVRSearch() {
		return ksNVRSearch;
	}

	public void setKsNVRSearch(KsNVRSearch ksNVRSearch) {
		this.ksNVRSearch = ksNVRSearch;
	}

	public KsNVR getKsNVR() {
		return ksNVR;
	}

	public void setKsNVR(KsNVR ksNVR) {
		this.ksNVR = ksNVR;
	}

	public List<KsNVR> getKsNVRSList() {
		return ksNVRSList;
	}

	public void setKsNVRSList(List<KsNVR> ksNVRSList) {
		this.ksNVRSList = ksNVRSList;
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
