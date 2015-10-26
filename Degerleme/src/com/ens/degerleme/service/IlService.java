package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.model.entity.Il;



@ManagedBean(name = "ilService")
@ViewScoped
public class IlService implements Serializable {

	private static final long serialVersionUID = -6093393727814589662L;
	@EJB
	private IlDao ilDao;

	private List<Il> ilListesi = new ArrayList<Il>();

	private Il il = new Il();
	

	@PostConstruct
	public void init() {
		ilListele();
	}
	
	public void ilSil(Il il_s) {
		il = il_s;
		ilDao.ilSil(il);
		ilListele();
		il = new Il();
	}

	public void ilKaydetGuncelle() {
		if (il.getId() == null) {
			ilKaydet();
		} else {
			ilGuncelle();
		}
	}

	public void ilKaydet() {
		try {
			ilDao.ilKaydet(il);
			ilListele();
			FacesMessage mesaj2 = new FacesMessage("Kaydetme iþlemi baþarýlý",
					"Ýl: " + il.getAdi() + " baþarýyla kaydedildi.");
			FacesContext.getCurrentInstance().addMessage(null, mesaj2);
			il = new Il();
		} catch (Exception e) {
			FacesMessage mesaj2 = new FacesMessage("hata");
			FacesContext.getCurrentInstance().addMessage(null, mesaj2);

		}
	}

	public void guncellemeIcinHazirla(Il secilenIl) {
		il = secilenIl;
	}

	public void ilGuncelle() {
		il = ilDao.ilGuncelle(il);
		ilListele();
		il = new Il();
	}

	public void ilListele() {
		ilListesi = ilDao.hepsiniGetir();
	}

	public Il getIl() {
		return il;
	}

	public void setIl(Il il) {
		this.il = il;
	}

	public List<Il> getIlListesi() {
		return ilListesi;
	}

	public void setIlListesi(List<Il> ilListesi) {
		this.ilListesi = ilListesi;
	}

	public IlDao getIlDao() {
		return ilDao;
	}

	public void setIlDao(IlDao ilDao) {
		this.ilDao = ilDao;
	}

}