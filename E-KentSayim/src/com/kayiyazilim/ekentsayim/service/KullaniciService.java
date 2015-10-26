package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.search.KullaniciSearch;
import com.kayiyazilim.ekentsayim.model.type.Statu;

@ManagedBean(name = "kullaniciService")
@ViewScoped
public class KullaniciService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6063662884323821401L;

	@EJB
	private KullaniciDao dao;

	private Kullanici kullanici = new Kullanici();

	private List<Kullanici> kullaniciList;

	private KullaniciSearch kullaniciSearch = new KullaniciSearch();
	
	public Statu[] getStatus(){
		return Statu.values();
	}

	@PostConstruct
	public void init() {
		kullaniciList = dao.findAll();
	}

	public void ara() {
		kullaniciList = dao.search(kullaniciSearch);
	}

	public void kaydet() {
		dao.persist(kullanici);
		if (kullaniciList == null)
			kullaniciList = new ArrayList<Kullanici>();
		kullaniciList.add(kullanici);
		kullanici = new Kullanici();
	}

	public void guncelle(RowEditEvent event) {
		kullanici = ((Kullanici) event.getObject());
		dao.merge(kullanici);
		kullanici = new Kullanici();
	}

	public void sil() {
		if (kullanici != null)
			dao.remove(kullanici);
		kullaniciList.remove(kullanici);
		kullanici = new Kullanici();
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public KullaniciSearch getKullaniciSearch() {
		return kullaniciSearch;
	}

	public void setKullaniciSearch(KullaniciSearch kullaniciSearch) {
		this.kullaniciSearch = kullaniciSearch;
	}
}
