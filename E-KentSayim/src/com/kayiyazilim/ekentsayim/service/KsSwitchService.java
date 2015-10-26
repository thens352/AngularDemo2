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
import com.kayiyazilim.ekentsayim.dao.KsSwitchDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.KsSwitch;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.KsSwitchSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "ksSwitchService")
@ViewScoped
public class KsSwitchService implements Serializable {

	private static final long serialVersionUID = 8696203957192527903L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private KsSwitchDao ksSwitchDao;

	private KsSwitch ksSwitch = new KsSwitch();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<KsSwitch> ksSwitchList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return ksSwitch.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private KsSwitchSearch ksSwitchSearch = new KsSwitchSearch();

	@PostConstruct
	public void init() {
		ksSwitch.setDurum(Durum.SAHADA);
		ksSwitchList = ksSwitchDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e8,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		ksSwitchList = ksSwitchDao.search(ksSwitchSearch);
	}

	public void ekle() {
		KsSwitch aranan = null;
		try {
			aranan = ksSwitchDao.find(ksSwitch.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			ksSwitch.setGununTarihi(new Date());
			ksSwitchDao.persist(ksSwitch);
			if (ksSwitchList == null)
				ksSwitchList = new ArrayList<KsSwitch>();
			ksSwitchList.add(ksSwitch);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			ksSwitch = new KsSwitch();
		}
	}

	public void guncelle(RowEditEvent event) {
		ksSwitch = ((KsSwitch) event.getObject());
		ksSwitchDao.merge(ksSwitch);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksSwitch = new KsSwitch();
	}

	public void sil() {
		if (ksSwitch != null)
			ksSwitchDao.remove(ksSwitch);
		ksSwitchList.remove(ksSwitch);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		ksSwitch = new KsSwitch();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public KsSwitchSearch getKsSwitchSearch() {
		return ksSwitchSearch;
	}

	public void setKsSwitchSearch(KsSwitchSearch ksSwitchSearch) {
		this.ksSwitchSearch = ksSwitchSearch;
	}

	public KsSwitch getKsSwitch() {
		return ksSwitch;
	}

	public void setKsSwitch(KsSwitch ksSwitch) {
		this.ksSwitch = ksSwitch;
	}

	public List<KsSwitch> getKsSwitchList() {
		return ksSwitchList;
	}

	public void setKsSwitchList(List<KsSwitch> ksSwitchList) {
		this.ksSwitchList = ksSwitchList;
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
