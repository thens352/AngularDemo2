package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;

@ManagedBean(name = "girisService")
@ViewScoped
public class GirisService implements Serializable {

	private static final long serialVersionUID = 493195581793545881L;

	@EJB
	private KullaniciDao kullaniciDao = new KullaniciDao();

	private Kullanici kullanici;

	private String kullaniciAdi;

	private String sifre;

	
	
	
	
	

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

}
