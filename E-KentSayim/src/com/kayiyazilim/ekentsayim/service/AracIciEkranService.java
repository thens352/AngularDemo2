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

import com.kayiyazilim.ekentsayim.dao.AracIciEkranDao;
import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.AracIciEkran;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.AracIciEkranSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "aracIciEkranService")
@ViewScoped
public class AracIciEkranService implements Serializable {

	private static final long serialVersionUID = 3949206047932481012L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private AracIciEkranDao aracIciEkranDao;

	private AracIciEkran aracIciEkran = new AracIciEkran();

	private List<AracIciEkran> aracIciEkranList;

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return aracIciEkran.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private AracIciEkranSearch aracIciEkranSearch = new AracIciEkranSearch();

	@PostConstruct
	public void init() {
		aracIciEkran.setDurum(Durum.SAHADA);
		aracIciEkranList = aracIciEkranDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e4,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void ara() {
		aracIciEkranList = aracIciEkranDao.search(aracIciEkranSearch);
	}

	public void kaydet() {
		AracIciEkran aranan = null;
		try {
			aranan = aracIciEkranDao.find(aracIciEkran.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			aracIciEkran.setGununTarihi(new Date());
			aracIciEkranDao.persist(aracIciEkran);
			if (aracIciEkranList == null)
				aracIciEkranList = new ArrayList<AracIciEkran>();
			aracIciEkranList.add(aracIciEkran);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			aracIciEkran = new AracIciEkran();
		}
	}

	public void guncelle(RowEditEvent event) {
		aracIciEkran = ((AracIciEkran) event.getObject());
		aracIciEkranDao.merge(aracIciEkran);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		aracIciEkran = new AracIciEkran();
	}

	public void sil() {
		if (aracIciEkran != null)
			aracIciEkranDao.remove(aracIciEkran);
		aracIciEkranList.remove(aracIciEkran);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		aracIciEkran = new AracIciEkran();

	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public AracIciEkranSearch getAracIciEkranSearch() {
		return aracIciEkranSearch;
	}

	public void setAracIciEkranSearch(AracIciEkranSearch aracIciEkranSearch) {
		this.aracIciEkranSearch = aracIciEkranSearch;
	}

	public AracIciEkran getAracIciEkran() {
		return aracIciEkran;
	}

	public void setAracIciEkran(AracIciEkran aracIciEkran) {
		this.aracIciEkran = aracIciEkran;
	}

	public List<AracIciEkran> getAracIciEkranList() {
		return aracIciEkranList;
	}

	public void setAracIciEkranList(List<AracIciEkran> aracIciEkranList) {
		this.aracIciEkranList = aracIciEkranList;
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
