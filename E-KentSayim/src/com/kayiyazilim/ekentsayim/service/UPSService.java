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
import com.kayiyazilim.ekentsayim.dao.UPSDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.entity.UPS;
import com.kayiyazilim.ekentsayim.model.search.UPSSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "upsService")
@ViewScoped
public class UPSService implements Serializable {

	private static final long serialVersionUID = -1954799641671800252L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private UPSDao upsDao;

	private UPS ups = new UPS();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<UPS> upsList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private UPSSearch upsSearch = new UPSSearch();

	@PostConstruct
	public void init() {
		upsList = upsDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e16,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e16,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		upsList = upsDao.search(upsSearch);
	}

	public void ekle() {
		ups.setGununTarihi(new Date());
		upsDao.persist(ups);
		if (upsList == null)
			upsList = new ArrayList<UPS>();
		upsList.add(ups);
		FacesMessage mesaj1 = new FacesMessage("Kaydetme İşlemi Tamamlandı", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ups = new UPS();

	}

	public void guncelle(RowEditEvent event) {
		ups = ((UPS) event.getObject());
		upsDao.merge(ups);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ups = new UPS();
	}

	public void sil() {
		if (ups != null)
			upsDao.remove(ups);
		upsList.remove(ups);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ups = new UPS();

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

	public UPSSearch getUpsSearch() {
		return upsSearch;
	}

	public void setUpsSearch(UPSSearch upsSearch) {
		this.upsSearch = upsSearch;
	}

	public UPS getUps() {
		return ups;
	}

	public void setUps(UPS ups) {
		this.ups = ups;
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

	public List<UPS> getUpsList() {
		return upsList;
	}

	public void setUpsList(List<UPS> upsList) {
		this.upsList = upsList;
	}

}
