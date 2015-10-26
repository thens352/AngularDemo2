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

import com.kayiyazilim.budaksanisakis.dao.SoforDao;
import com.kayiyazilim.budaksanisakis.model.entity.Sofor;

@ManagedBean(name = "soforService")
@ViewScoped
public class SoforService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7193809063889507692L;

	@EJB
	private SoforDao soforDao;

	private List<Sofor> soforList;

	private List<Sofor> filtrelenenSoforList;

	private Sofor sofor = new Sofor();

	@PostConstruct
	public void init() {
		soforList = soforDao.findAll();
	}

	public void ekle() {
		soforDao.persist(sofor);
		if (soforList == null)
			soforList = new ArrayList<Sofor>();
		soforList.add(sofor);
		FacesMessage mesaj2 = new FacesMessage(
				"Þoför Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		sofor = new Sofor();
	}

	public void guncelle(RowEditEvent event) {
		sofor = ((Sofor) event.getObject());
		soforDao.merge(sofor);
		FacesMessage mesaj2 = new FacesMessage(
				"Araç Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		sofor = new Sofor();
	}

	public void sil() {
		if (sofor != null)
			soforDao.remove(sofor);
		soforList.remove(sofor);
		FacesMessage mesaj2 = new FacesMessage("Araç Silme Ýþlemi Tamamlandý.",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		sofor = new Sofor();
	}

	public List<Sofor> getSoforList() {
		return soforList;
	}

	public void setSoforList(List<Sofor> soforList) {
		this.soforList = soforList;
	}

	public Sofor getSofor() {
		return sofor;
	}

	public void setSofor(Sofor sofor) {
		this.sofor = sofor;
	}

	public List<Sofor> getFiltrelenenSoforList() {
		return filtrelenenSoforList;
	}

	public void setFiltrelenenSoforList(List<Sofor> filtrelenenSoforList) {
		this.filtrelenenSoforList = filtrelenenSoforList;
	}
}
