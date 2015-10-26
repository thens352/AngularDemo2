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
import com.kayiyazilim.ekentsayim.dao.MufettisElTerminaliDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.MufettisElTerminali;
import com.kayiyazilim.ekentsayim.model.search.MufettisElTerminaliSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "mufettisElTerminaliService")
@ViewScoped
public class MufettisElTerminaliService implements Serializable {

	private static final long serialVersionUID = 5741526401739052383L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private MufettisElTerminaliDao mufettisElTerminaliDao;

	private MufettisElTerminali mufettisElTerminali = new MufettisElTerminali();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<MufettisElTerminali> mufettisElTerminaliList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	public boolean getDurumStatu() {
		return mufettisElTerminali.getDurum() == Durum.SAHADA;
	}

	private MufettisElTerminaliSearch mufettisElTerminaliSearch = new MufettisElTerminaliSearch();

	@PostConstruct
	public void init() {
		mufettisElTerminali.setDurum(Durum.SAHADA);
		mufettisElTerminaliList = mufettisElTerminaliDao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

	}

	public void ara() {
		mufettisElTerminaliList = mufettisElTerminaliDao
				.search(mufettisElTerminaliSearch);
	}

	public void ekle() {
		MufettisElTerminali aranan = null;
		try {
			aranan = mufettisElTerminaliDao.find(mufettisElTerminali
					.getBarkod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (aranan != null) {
			FacesMessage mesaj1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Girilen barkoda ait kayıt zaten var.", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
		} else {
			mufettisElTerminali.setGununTarihi(new Date());
			mufettisElTerminaliDao.persist(mufettisElTerminali);
			if (mufettisElTerminaliList == null)
				mufettisElTerminaliList = new ArrayList<MufettisElTerminali>();
			mufettisElTerminaliList.add(mufettisElTerminali);
			FacesMessage mesaj1 = new FacesMessage(
					"Kaydetme İşlemi Tamamlandı", "");
			FacesContext.getCurrentInstance().addMessage("", mesaj1);
			mufettisElTerminali = new MufettisElTerminali();
		}
	}

	public void guncelle(RowEditEvent event) {
		mufettisElTerminali = ((MufettisElTerminali) event.getObject());
		mufettisElTerminaliDao.merge(mufettisElTerminali);
		FacesMessage mesaj1 = new FacesMessage("Güncelleme İşlemi Tamamlandı",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		mufettisElTerminali = new MufettisElTerminali();
	}

	public void sil() {
		if (mufettisElTerminali != null)
			mufettisElTerminaliDao.remove(mufettisElTerminali);
		mufettisElTerminaliList.remove(mufettisElTerminali);
		FacesMessage mesaj1 = new FacesMessage("Silme işlemi Tamamlandı");
		FacesContext.getCurrentInstance().addMessage("", mesaj1);
		mufettisElTerminali = new MufettisElTerminali();

	}

	public MufettisElTerminaliSearch getMufettisElTerminaliSearch() {
		return mufettisElTerminaliSearch;
	}

	public void setMufettisElTerminaliSearch(
			MufettisElTerminaliSearch mufettisElTerminaliSearch) {
		this.mufettisElTerminaliSearch = mufettisElTerminaliSearch;
	}

	public MufettisElTerminali getMufettisElTerminali() {
		return mufettisElTerminali;
	}

	public void setMufettisElTerminali(MufettisElTerminali mufettisElTerminali) {
		this.mufettisElTerminali = mufettisElTerminali;
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

	public List<MufettisElTerminali> getMufettisElTerminaliList() {
		return mufettisElTerminaliList;
	}

	public void setMufettisElTerminaliList(
			List<MufettisElTerminali> mufettisElTerminaliList) {
		this.mufettisElTerminaliList = mufettisElTerminaliList;
	}

}
