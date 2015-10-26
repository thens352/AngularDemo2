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
import com.kayiyazilim.ekentsayim.dao.OtobusGuzergahPanosuDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.OtobusGuzergahPanosu;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.OtobusGuzergahPanosuSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "otobusGuzergahPanosuService")
@ViewScoped
public class OtobusGuzergahPanosuService implements Serializable {

	private static final long serialVersionUID = -6022807605096956194L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private OtobusGuzergahPanosuDao otobusGuzergahPanosuDao;

	private OtobusGuzergahPanosu otobusGuzergahPanosu = new OtobusGuzergahPanosu();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<OtobusGuzergahPanosu> otobusGuzergahPanosuList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return otobusGuzergahPanosu.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private OtobusGuzergahPanosuSearch otobusGuzergahPanosuSearch = new OtobusGuzergahPanosuSearch();

	@PostConstruct
	public void init() {
		otobusGuzergahPanosu.setDurum(Durum.SAHADA);
		otobusGuzergahPanosuList = otobusGuzergahPanosuDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e9,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		otobusGuzergahPanosuList = otobusGuzergahPanosuDao
				.search(otobusGuzergahPanosuSearch);
	}

	public void ekle() {
		OtobusGuzergahPanosu aranan = null;
		try {
			aranan = otobusGuzergahPanosuDao.find(otobusGuzergahPanosu
					.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			otobusGuzergahPanosu.setGununTarihi(new Date());
			otobusGuzergahPanosuDao.persist(otobusGuzergahPanosu);
			if (otobusGuzergahPanosuList == null)
				otobusGuzergahPanosuList = new ArrayList<OtobusGuzergahPanosu>();
			otobusGuzergahPanosuList.add(otobusGuzergahPanosu);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			otobusGuzergahPanosu = new OtobusGuzergahPanosu();
		}
	}

	public void guncelle(RowEditEvent event) {
		otobusGuzergahPanosu = ((OtobusGuzergahPanosu) event.getObject());
		otobusGuzergahPanosuDao.merge(otobusGuzergahPanosu);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		otobusGuzergahPanosu = new OtobusGuzergahPanosu();
	}

	public void sil() {
		if (otobusGuzergahPanosu != null)
			otobusGuzergahPanosuDao.remove(otobusGuzergahPanosu);
		otobusGuzergahPanosuList.remove(otobusGuzergahPanosu);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		otobusGuzergahPanosu = new OtobusGuzergahPanosu();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public OtobusGuzergahPanosuSearch getOtobusGuzergahPanosuSearch() {
		return otobusGuzergahPanosuSearch;
	}

	public void setOtobusGuzergahPanosuSearch(
			OtobusGuzergahPanosuSearch otobusGuzergahPanosuSearch) {
		this.otobusGuzergahPanosuSearch = otobusGuzergahPanosuSearch;
	}

	public OtobusGuzergahPanosu getOtobusGuzergahPanosu() {
		return otobusGuzergahPanosu;
	}

	public void setOtobusGuzergahPanosu(
			OtobusGuzergahPanosu otobusGuzergahPanosu) {
		this.otobusGuzergahPanosu = otobusGuzergahPanosu;
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

	public List<OtobusGuzergahPanosu> getOtobusGuzergahPanosuList() {
		return otobusGuzergahPanosuList;
	}

	public void setOtobusGuzergahPanosuList(
			List<OtobusGuzergahPanosu> otobusGuzergahPanosuList) {
		this.otobusGuzergahPanosuList = otobusGuzergahPanosuList;
	}

}
