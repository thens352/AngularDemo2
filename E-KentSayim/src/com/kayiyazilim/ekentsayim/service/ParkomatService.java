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

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParkomatDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parkomat;
import com.kayiyazilim.ekentsayim.model.search.ParkomatSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "parkomatService")
@ViewScoped
public class ParkomatService implements Serializable {

	private static final long serialVersionUID = 8417171633303398141L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private ParkomatDao parkomatDao;

	private Parkomat parkomat = new Parkomat();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Parkomat> parkomatList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return parkomat.getDurum() == Durum.SAHADA;
	}

	private ParkomatSearch parkomatSearch = new ParkomatSearch();

	@PostConstruct
	public void init() {
		parkomat.setDurum(Durum.SAHADA);
		parkomatList = parkomatDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

	}

	public void ara() {
		parkomatList = parkomatDao.search(parkomatSearch);
	}

	public void ekle() {
		Parkomat aranan = null;
		try {
			aranan = parkomatDao.find(parkomat.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			parkomat.setGununTarihi(new Date());
			parkomatDao.persist(parkomat);
			if (parkomatList == null)
				parkomatList = new ArrayList<Parkomat>();
			parkomatList.add(parkomat);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			parkomat = new Parkomat();
		}
	}

	public void guncelle(RowEditEvent event) {
		parkomat = ((Parkomat) event.getObject());
		parkomatDao.merge(parkomat);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		parkomat = new Parkomat();
	}

	public void sil() {
		if (parkomat != null)
			parkomatDao.remove(parkomat);
		parkomatList.remove(parkomat);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		parkomat = new Parkomat();

	}

	public ParkomatSearch getParkomatSearch() {
		return parkomatSearch;
	}

	public void setParkomatSearch(ParkomatSearch parkomatSearch) {
		this.parkomatSearch = parkomatSearch;
	}

	public Parkomat getParkomat() {
		return parkomat;
	}

	public void setParkomat(Parkomat parkomat) {
		this.parkomat = parkomat;
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

	public List<Parkomat> getParkomatList() {
		return parkomatList;
	}

	public void setParkomatList(List<Parkomat> parkomatList) {
		this.parkomatList = parkomatList;
	}

}
