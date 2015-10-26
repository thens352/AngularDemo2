package com.kayiyazilim.budaksanisakis.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.budaksanisakis.dao.MalzemeDao;
import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.type.MalzemeTipi;

@ManagedBean(name = "stokMalzemeService")
@ViewScoped
public class StokMalzemeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6878193503581422079L;

	@EJB
	private MalzemeDao malzemeDao;

	private Malzeme malzeme = new Malzeme();

	private List<Malzeme> malzemeList = new ArrayList<Malzeme>();

	public void tipeGoreGetir() {
		malzemeList = malzemeDao.malzemeGetirByTip(malzeme.getMalzemeTipi());
	}

	@PostConstruct
	public void init() {
		malzemeList = malzemeDao.findAll();
	}

	public MalzemeTipi[] getMalzemeTipleri() {
		return MalzemeTipi.values();
	}

	public void kaydet() {
		malzemeDao.persist(malzeme);
		malzemeList.add(malzeme);
		FacesMessage mesaj2 = new FacesMessage(
				"Malzeme Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		malzeme = new Malzeme();

	}

	public void sil() {
		if (malzeme != null)
			malzemeDao.remove(malzeme);
		malzemeList.remove(malzeme);
		FacesMessage mesaj2 = new FacesMessage(
				"Malzeme Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		malzeme = new Malzeme();
	}

	public void malzemeGuncelle(RowEditEvent event) {
		malzeme = ((Malzeme) event.getObject());
		malzemeDao.merge(malzeme);
		FacesMessage mesaj2 = new FacesMessage(
				"Malzeme Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		malzeme = new Malzeme();
	}

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}

	public List<Malzeme> getMalzemeList() {
		return malzemeList;
	}

	public void setMalzemeList(List<Malzeme> malzemeList) {
		this.malzemeList = malzemeList;
	}

}
