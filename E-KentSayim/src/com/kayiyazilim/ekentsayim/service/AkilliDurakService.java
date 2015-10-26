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

import com.kayiyazilim.ekentsayim.dao.AkilliDurakDao;
import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.AkilliDurak;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.AkilliDurakSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "akilliDurakService")
@ViewScoped
public class AkilliDurakService implements Serializable {

	private static final long serialVersionUID = -2093544409754487912L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private AkilliDurakDao akilliDurakDao;

	private AkilliDurak akilliDurak = new AkilliDurak();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<AkilliDurak> akilliDurakList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return akilliDurak.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private AkilliDurakSearch akilliDurakSearch = new AkilliDurakSearch();

	@PostConstruct
	public void init() {
		akilliDurak.setDurum(Durum.SAHADA);
		akilliDurakList = akilliDurakDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e11,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e11,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		akilliDurakList = akilliDurakDao.search(akilliDurakSearch);
	}

	public void ekle() {
		AkilliDurak aranan = null;
		try {
			aranan = akilliDurakDao.find(akilliDurak.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			akilliDurak.setGununTarihi(new Date());
			akilliDurakDao.persist(akilliDurak);
			if (akilliDurakList == null)
				akilliDurakList = new ArrayList<AkilliDurak>();
			akilliDurakList.add(akilliDurak);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			akilliDurak = new AkilliDurak();
		}
	}

	public void guncelle(RowEditEvent event) {
		akilliDurak = ((AkilliDurak) event.getObject());
		akilliDurakDao.merge(akilliDurak);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		akilliDurak = new AkilliDurak();
	}

	public void sil() {
		if (akilliDurak != null)
			akilliDurakDao.remove(akilliDurak);
		akilliDurakList.remove(akilliDurak);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		akilliDurak = new AkilliDurak();

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

	public AkilliDurakSearch getAkilliDurakSearch() {
		return akilliDurakSearch;
	}

	public void setAkilliDurakSearch(AkilliDurakSearch akilliDurakSearch) {
		this.akilliDurakSearch = akilliDurakSearch;
	}

	public AkilliDurak getAkilliDurak() {
		return akilliDurak;
	}

	public void setAkilliDurak(AkilliDurak akilliDurak) {
		this.akilliDurak = akilliDurak;
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

	public List<AkilliDurak> getAkilliDurakList() {
		return akilliDurakList;
	}

	public void setAkilliDurakList(List<AkilliDurak> akilliDurakList) {
		this.akilliDurakList = akilliDurakList;
	}

}
