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

import com.kayiyazilim.budaksanisakis.dao.AracDao;
import com.kayiyazilim.budaksanisakis.model.entity.Arac;

@ManagedBean(name = "aracService")
@ViewScoped
public class AracService implements Serializable {

	private static final long serialVersionUID = 5559395448527336245L;

	@EJB
	private AracDao aracDao;

	private List<Arac> aracList;

	private List<Arac> filtrelenenAracList;

	private Arac arac = new Arac();

	@PostConstruct
	public void init() {
		aracList = aracDao.findAll();
	}

	public void refresh() {
		aracList = aracDao.findAll();
	}

	public void ekle() {
		aracDao.persist(arac);
		if (aracList == null)
			aracList = new ArrayList<Arac>();
		aracList.add(arac);
		FacesMessage mesaj2 = new FacesMessage(
				"Araç Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		arac = new Arac();
	}

	public void guncelle(RowEditEvent event) {
		arac = ((Arac) event.getObject());
		aracDao.merge(arac);
		FacesMessage mesaj2 = new FacesMessage(
				"Araç Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		arac = new Arac();
	}

	public void sil() {
		if (arac != null)
			aracDao.remove(arac);
		aracList.remove(arac);
		FacesMessage mesaj2 = new FacesMessage("Araç Silme Ýþlemi Tamamlandý.",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		arac = new Arac();
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public List<Arac> getFiltrelenenAracList() {
		return filtrelenenAracList;
	}

	public void setFiltrelenenAracList(List<Arac> filtrelenenAracList) {
		this.filtrelenenAracList = filtrelenenAracList;
	}
}
