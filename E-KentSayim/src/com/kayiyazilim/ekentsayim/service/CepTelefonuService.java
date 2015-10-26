package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.CepTelefonuDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.CepTelefonu;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.search.CepTelefonuSearch;
import com.kayiyazilim.ekentsayim.model.type.Durum;

@ManagedBean(name = "cepTelefonuService")
@ViewScoped
public class CepTelefonuService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private CepTelefonuDao dao;

	private CepTelefonu cepTelefonu = new CepTelefonu();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<CepTelefonu> cepTelefonuList;

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	private CepTelefonuSearch cepTelefonuSearch = new CepTelefonuSearch();

	@PostConstruct
	public void init() {
		cepTelefonuList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		cepTelefonuList = dao.search(cepTelefonuSearch);
	}

	public void kaydet() {
		cepTelefonu.setGununTarihi(new Date());
		dao.persist(cepTelefonu);
		if (cepTelefonuList == null)
			cepTelefonuList = new ArrayList<CepTelefonu>();
		cepTelefonuList.add(cepTelefonu);
		cepTelefonu = new CepTelefonu();
	}

	public void guncelle(RowEditEvent event) {
		cepTelefonu = ((CepTelefonu) event.getObject());
		dao.merge(cepTelefonu);
		cepTelefonu = new CepTelefonu();
	}

	public void sil() {
		if (cepTelefonu != null)
			dao.remove(cepTelefonu);
		cepTelefonuList.remove(cepTelefonu);
		cepTelefonu = new CepTelefonu();
	}

	public CepTelefonu getLcd() {
		return cepTelefonu;
	}

	public void setLcd(CepTelefonu cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
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

	public List<CepTelefonu> getLcdList() {
		return cepTelefonuList;
	}

	public void setLcdList(List<CepTelefonu> cepTelefonuList) {
		this.cepTelefonuList = cepTelefonuList;
	}

	public CepTelefonu getCepTelefonu() {
		return cepTelefonu;
	}

	public void setCepTelefonu(CepTelefonu cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
	}

	public List<CepTelefonu> getCepTelefonuList() {
		return cepTelefonuList;
	}

	public void setCepTelefonuList(List<CepTelefonu> cepTelefonuList) {
		this.cepTelefonuList = cepTelefonuList;
	}

	public CepTelefonuSearch getCepTelefonuSearch() {
		return cepTelefonuSearch;
	}

	public void setCepTelefonuSearch(CepTelefonuSearch cepTelefonuSearch) {
		this.cepTelefonuSearch = cepTelefonuSearch;
	}

}
