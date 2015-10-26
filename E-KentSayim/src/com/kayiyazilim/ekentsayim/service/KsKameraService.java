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
import com.kayiyazilim.ekentsayim.dao.KsKameraDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.KsKamera;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.KsKameraSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;
import com.kayiyazilim.ekentsayim.model.type.KameraninYeri;

@ManagedBean(name = "ksKameraService")
@ViewScoped
public class KsKameraService implements Serializable {

	private static final long serialVersionUID = 8696203957192527903L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private KsKameraDao ksKameraDao;

	private KsKamera ksKamera = new KsKamera();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<KsKamera> ksKameraList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public KameraninYeri[] getKameraYerleri() {
		return KameraninYeri.values();
	}

	public boolean getDurumStatu() {
		return ksKamera.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private KsKameraSearch ksKameraSearch = new KsKameraSearch();

	@PostConstruct
	public void init() {
		ksKamera.setDurum(Durum.SAHADA);
		ksKameraList = ksKameraDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e5,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		ksKameraList = ksKameraDao.search(ksKameraSearch);
	}

	public void ekle() {
		KsKamera aranan = null;
		try {
			aranan = ksKameraDao.find(ksKamera.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			ksKamera.setGununTarihi(new Date());
			ksKameraDao.persist(ksKamera);
			if (ksKameraList == null)
				ksKameraList = new ArrayList<KsKamera>();
			ksKameraList.add(ksKamera);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			ksKamera = new KsKamera();
		}
	}

	public void guncelle(RowEditEvent event) {
		ksKamera = ((KsKamera) event.getObject());
		ksKameraDao.merge(ksKamera);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksKamera = new KsKamera();
	}

	public void sil() {
		if (ksKamera != null)
			ksKameraDao.remove(ksKamera);
		ksKameraList.remove(ksKamera);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksKamera = new KsKamera();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public KsKamera getKsKamera() {
		return ksKamera;
	}

	public void setKsKamera(KsKamera ksKamera) {
		this.ksKamera = ksKamera;
	}

	public List<KsKamera> getksKameraList() {
		return ksKameraList;
	}

	public void setksKameraList(List<KsKamera> ksKameraList) {
		this.ksKameraList = ksKameraList;
	}

	public KsKameraSearch getKsKameraSearch() {
		return ksKameraSearch;
	}

	public void setKsKameraSearch(KsKameraSearch ksKameraSearch) {
		this.ksKameraSearch = ksKameraSearch;
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
