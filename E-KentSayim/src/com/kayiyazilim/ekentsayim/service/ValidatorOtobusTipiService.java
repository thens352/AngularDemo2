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
import com.kayiyazilim.ekentsayim.dao.ValidatorOtobusTipiDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.entity.ValidatorOtobusTipi;
import com.kayiyazilim.ekentsayim.model.search.ValidatorOtobusTipiSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "validatorOtobusTipiService")
@ViewScoped
public class ValidatorOtobusTipiService implements Serializable {

	private static final long serialVersionUID = -1350658304883249374L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private ValidatorOtobusTipiDao validatorOtobusTipiDao;

	private List<ValidatorOtobusTipi> validatorOtobusTipiList;

	private ValidatorOtobusTipi validatorOtobusTipi = new ValidatorOtobusTipi();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return validatorOtobusTipi.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private List<SelectItem> ozellikList = new ArrayList<SelectItem>();

	private List<SelectItem> altTipiList = new ArrayList<SelectItem>();

	private List<SelectItem> kullanimSekliList = new ArrayList<SelectItem>();

	private ValidatorOtobusTipiSearch validatorOtobusTipiSearch = new ValidatorOtobusTipiSearch();

	@PostConstruct
	public void init() {
		validatorOtobusTipi.setDurum(Durum.SAHADA);
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
		validatorOtobusTipiList = validatorOtobusTipiDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e1,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e1,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e1,
				Alan.OZELLIK)) {
			ozellikList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e1,
				Alan.ALTTIPI)) {
			altTipiList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e1,
				Alan.KULLANIMSEKLI)) {
			kullanimSekliList.add(new SelectItem(parametre.getDeger(),
					parametre.getDeger()));
		}
	}

	public void ara() {
		validatorOtobusTipiList = validatorOtobusTipiDao
				.search(validatorOtobusTipiSearch);
	}

	public void ekle() {
		ValidatorOtobusTipi aranan = null;
		try {
			aranan = validatorOtobusTipiDao.find(validatorOtobusTipi
					.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			validatorOtobusTipi.setGununTarihi(new Date());
			validatorOtobusTipiDao.persist(validatorOtobusTipi);
			if (validatorOtobusTipiList == null)
				validatorOtobusTipiList = new ArrayList<ValidatorOtobusTipi>();
			validatorOtobusTipiList.add(validatorOtobusTipi);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			validatorOtobusTipi = new ValidatorOtobusTipi();
		}

	}

	public void guncelle(RowEditEvent event) {
		validatorOtobusTipi = ((ValidatorOtobusTipi) event.getObject());
		validatorOtobusTipiDao.merge(validatorOtobusTipi);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme Tamamlandı", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		validatorOtobusTipi = new ValidatorOtobusTipi();
	}

	public void sil() {
		if (validatorOtobusTipi != null)
			validatorOtobusTipiDao.remove(validatorOtobusTipi);
		validatorOtobusTipiList.remove(validatorOtobusTipi);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		validatorOtobusTipi = new ValidatorOtobusTipi();

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

	public List<SelectItem> getOzellikList() {
		return ozellikList;
	}

	public void setOzellikList(List<SelectItem> ozellikList) {
		this.ozellikList = ozellikList;
	}

	public List<SelectItem> getAltTipiList() {
		return altTipiList;
	}

	public void setAltTipiList(List<SelectItem> altTipiList) {
		this.altTipiList = altTipiList;
	}

	public List<SelectItem> getKullanimSekliList() {
		return kullanimSekliList;
	}

	public void setKullanimSekliList(List<SelectItem> kullanimSekliList) {
		this.kullanimSekliList = kullanimSekliList;
	}

	public ValidatorOtobusTipiSearch getValidatorOtobusTipiSearch() {
		return validatorOtobusTipiSearch;
	}

	public void setValidatorOtobusTipiSearch(
			ValidatorOtobusTipiSearch validatorOtobusTipiSearch) {
		this.validatorOtobusTipiSearch = validatorOtobusTipiSearch;
	}

	public List<ValidatorOtobusTipi> getValidatorOtobusTipiList() {
		return validatorOtobusTipiList;
	}

	public void setValidatorOtobusTipiList(
			List<ValidatorOtobusTipi> validatorOtobusTipiList) {
		this.validatorOtobusTipiList = validatorOtobusTipiList;
	}

	public ValidatorOtobusTipi getValidatorOtobusTipi() {
		return validatorOtobusTipi;
	}

	public void setValidatorOtobusTipi(ValidatorOtobusTipi validatorOtobusTipi) {
		this.validatorOtobusTipi = validatorOtobusTipi;
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
