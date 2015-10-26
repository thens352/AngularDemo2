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
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.dao.TurnikeDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.entity.Turnike;
import com.kayiyazilim.ekentsayim.model.search.TurnikeSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "turnikeService")
@ViewScoped
public class TurnikeService implements Serializable {

	private static final long serialVersionUID = 8904969202297547525L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private TurnikeDao turnikeDao;

	private Turnike turnike = new Turnike();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Turnike> turnikeList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private TurnikeSearch turnikeSearch = new TurnikeSearch();

	@PostConstruct
	public void init() {
		turnikeList = turnikeDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e14,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e14,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

	}

	public void ara() {
		turnikeList = turnikeDao.search(turnikeSearch);
	}

	public void ekle() {
		turnike.setGununTarihi(new Date());
		turnikeDao.persist(turnike);
		if (turnikeList == null)
			turnikeList = new ArrayList<Turnike>();
		turnikeList.add(turnike);
		FacesMessage mesaj1 = new FacesMessage("Kaydetme İşlemi Tamamlandı", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		turnike = new Turnike();
	}

	public void guncelle(RowEditEvent event) {
		turnike = ((Turnike) event.getObject());
		turnikeDao.merge(turnike);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		turnike = new Turnike();
	}

	public void sil() {
		if (turnike != null)
			turnikeDao.remove(turnike);
		turnikeList.remove(turnike);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		turnike = new Turnike();
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

	public TurnikeSearch getTurnikeSearch() {
		return turnikeSearch;
	}

	public void setTurnikeSearch(TurnikeSearch turnikeSearch) {
		this.turnikeSearch = turnikeSearch;
	}

	public Turnike getTurnike() {
		return turnike;
	}

	public void setTurnike(Turnike turnike) {
		this.turnike = turnike;
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

	public List<Turnike> getTurnikeList() {
		return turnikeList;
	}

	public void setTurnikeList(List<Turnike> turnikeList) {
		this.turnikeList = turnikeList;
	}

}
