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
import com.kayiyazilim.ekentsayim.dao.ValidatorTurnikeTipiDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.entity.ValidatorTurnikeTipi;
import com.kayiyazilim.ekentsayim.model.search.ValidatorTurnikeTipiSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "validatorTurnikeTipiService")
@ViewScoped
public class ValidatorTurnikeTipiService implements Serializable {

	private static final long serialVersionUID = -2791305031226734413L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private ValidatorTurnikeTipiDao validatorTurnikeTipiDao;

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<ValidatorTurnikeTipi> validatorTurnikeTipiList;

	private ValidatorTurnikeTipi validatorTurnikeTipi = new ValidatorTurnikeTipi();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return validatorTurnikeTipi.getDurum() == Durum.SAHADA;
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> markaList = new ArrayList<SelectItem>();

	private List<SelectItem> modelList = new ArrayList<SelectItem>();

	private List<SelectItem> ozellikList = new ArrayList<SelectItem>();

	private List<SelectItem> altTipiList = new ArrayList<SelectItem>();

	private List<SelectItem> kullanimSekliList = new ArrayList<SelectItem>();

	private ValidatorTurnikeTipiSearch validatorTurnikeTipiSearch = new ValidatorTurnikeTipiSearch();

	@PostConstruct
	public void init() {
		validatorTurnikeTipi.setDurum(Durum.SAHADA);
		validatorTurnikeTipiList = validatorTurnikeTipiDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e2,
				Alan.MARKA)) {
			markaList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e2,
				Alan.MODEL)) {
			modelList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e2,
				Alan.OZELLIK)) {
			ozellikList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e2,
				Alan.ALTTIPI)) {
			altTipiList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e2,
				Alan.KULLANIMSEKLI)) {
			kullanimSekliList.add(new SelectItem(parametre.getDeger(),
					parametre.getDeger()));
		}
	}

	public void ara() {
		validatorTurnikeTipiList = validatorTurnikeTipiDao
				.search(validatorTurnikeTipiSearch);
	}

	public void ekle() {
		ValidatorTurnikeTipi aranan = null;
		try {
			aranan = validatorTurnikeTipiDao.find(validatorTurnikeTipi
					.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			validatorTurnikeTipi.setGununTarihi(new Date());
			validatorTurnikeTipiDao.persist(validatorTurnikeTipi);
			if (validatorTurnikeTipiList == null)
				validatorTurnikeTipiList = new ArrayList<ValidatorTurnikeTipi>();
			validatorTurnikeTipiList.add(validatorTurnikeTipi);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			validatorTurnikeTipi = new ValidatorTurnikeTipi();
		}
	}

	public void guncelle(RowEditEvent event) {
		validatorTurnikeTipi = ((ValidatorTurnikeTipi) event.getObject());
		validatorTurnikeTipiDao.merge(validatorTurnikeTipi);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		validatorTurnikeTipi = new ValidatorTurnikeTipi();
	}

	public void sil() {
		if (validatorTurnikeTipi != null)
			validatorTurnikeTipiDao.remove(validatorTurnikeTipi);
		validatorTurnikeTipiList.remove(validatorTurnikeTipi);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		validatorTurnikeTipi = new ValidatorTurnikeTipi();
	}

	public List<SelectItem> getAltTipiList() {
		return altTipiList;
	}

	public List<SelectItem> getOzellikList() {
		return ozellikList;
	}

	public void setOzellikList(List<SelectItem> ozellikList) {
		this.ozellikList = ozellikList;
	}

	public List<SelectItem> kullanimSekli() {
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

	public List<SelectItem> getModelList() {
		return modelList;
	}

	public void setModelList(List<SelectItem> modelList) {
		this.modelList = modelList;
	}

	public List<SelectItem> getMarkaList() {
		return markaList;
	}

	public void setMarkaList(List<SelectItem> markaList) {
		this.markaList = markaList;
	}

	public ValidatorTurnikeTipiSearch getValidatorTurnikeTipiSearch() {
		return validatorTurnikeTipiSearch;
	}

	public void setValidatorTurnikeTipiSearch(
			ValidatorTurnikeTipiSearch validatorTurnikeTipiSearch) {
		this.validatorTurnikeTipiSearch = validatorTurnikeTipiSearch;
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

	public List<ValidatorTurnikeTipi> getValidatorTurnikeTipiList() {
		return validatorTurnikeTipiList;
	}

	public void setValidatorTurnikeTipiList(
			List<ValidatorTurnikeTipi> validatorTurnikeTipiList) {
		this.validatorTurnikeTipiList = validatorTurnikeTipiList;
	}

	public ValidatorTurnikeTipi getValidatorTurnikeTipi() {
		return validatorTurnikeTipi;
	}

	public void setValidatorTurnikeTipi(
			ValidatorTurnikeTipi validatorTurnikeTipi) {
		this.validatorTurnikeTipi = validatorTurnikeTipi;
	}

}
